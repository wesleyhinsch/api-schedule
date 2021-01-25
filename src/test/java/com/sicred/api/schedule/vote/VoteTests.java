package com.sicred.api.schedule.vote;

import com.sicred.api.schedule.ApiScheduleApplicationTests;
import com.sicred.api.schedule.TestUtils;
import com.sicred.api.schedule.controller.agenda.dto.AgendaDTO;
import com.sicred.api.schedule.controller.vote.dto.VoteDTO;
import com.sicred.api.schedule.model.Agenda;
import com.sicred.api.schedule.model.Vote;
import com.sicred.api.schedule.repository.AgendaRepository;
import com.sicred.api.schedule.repository.VoteRepository;
import com.sicred.api.schedule.service.agenda.AgendaService;
import com.sicred.api.schedule.service.agenda.exception.ClosedAgendaException;
import com.sicred.api.schedule.service.vote.VoteService;
import com.sicred.api.schedule.service.vote.exception.InvalidCpfException;
import com.sicred.api.schedule.service.vote.exception.VoteDuplicateException;
import com.sicred.api.schedule.service.vote.exception.VoteInNotStartedAgendaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VoteTests extends ApiScheduleApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(VoteTests.class);

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    VoteService voteService;

    @Autowired
    AgendaRepository agendaRepository;

    @Autowired
    AgendaService agendaService;


    @Before
    public void init() {
        logger.error("Clear Tables vote ans agenda before test");
        agendaRepository.deleteAll();;
        voteRepository.deleteAll();
    }

    @Test
    public void shouldAllBeConsulted(){
        for (Vote vote : createVotes()) {
            voteRepository.save(vote);
        }
        List<Vote> votes = voteRepository.findAll();
        Assert.assertEquals(votes.size(), createVotes().size());
    }

    @Test
    public void shouldAllBeClosedAgendaException(){
        AgendaDTO agendaDTO = TestUtils.buildAgendaDto(null);

        agendaService.createAgenda(agendaDTO);

        VoteDTO voteDTO = TestUtils.buildVoteDto(agendaDTO,null,false);

        Optional<Agenda> agendaOptional = agendaRepository.findAgendaByName(agendaDTO.getName());
        agendaOptional.get().setActive(Boolean.FALSE);
        agendaRepository.save(agendaOptional.get());

        Assertions.assertThrows(ClosedAgendaException.class, () -> {
            voteService.vote(voteDTO);
        });
    }

    @Test
    public void shouldAllBeVoteInNotStartedAgendaException(){
        AgendaDTO agendaDTO = TestUtils.buildAgendaDto(null);

        agendaService.createAgenda(agendaDTO);

        VoteDTO voteDTO = TestUtils.buildVoteDto(agendaDTO, null,false);

        Optional<Agenda> agendaOptional = agendaRepository.findAgendaByName(agendaDTO.getName());
        agendaOptional.get().setStarted(Boolean.FALSE);
        agendaRepository.save(agendaOptional.get());

        Assertions.assertThrows(VoteInNotStartedAgendaException.class, () -> {
            voteService.vote(voteDTO);
        });
    }

    @Test
    public void shouldAllBeVoteDuplicateException(){
        AgendaDTO agendaDTO = TestUtils.buildAgendaDto(null);
        agendaService.createAgenda(agendaDTO);

        String cpf = TestUtils.buildCpf();

        agendaService.startAgenda(agendaDTO.getName());

        VoteDTO voteDTO1 = TestUtils.buildVoteDto(agendaDTO,cpf,false);
        voteService.vote(voteDTO1);
        VoteDTO voteDTO2 = TestUtils.buildVoteDto(agendaDTO,cpf,true);

        Assertions.assertThrows(VoteDuplicateException.class, () -> {
            voteService.vote(voteDTO2);
        });
    }


    @Test
    public void shouldAllBeInvalidCpfException(){
        AgendaDTO agendaDTO = TestUtils.buildAgendaDto(null);
        agendaService.createAgenda(agendaDTO);

        String cpf = TestUtils.INVALID_CPF;

        agendaService.startAgenda(agendaDTO.getName());

        VoteDTO voteDTO = TestUtils.buildVoteDto(agendaDTO,cpf,false);

        Assertions.assertThrows(InvalidCpfException.class, () -> {
            voteService.vote(voteDTO);
        });

    }

    private ArrayList<Vote> createVotes() {
        ArrayList<Vote> votes = new ArrayList<>();

        Vote vote1 = new Vote();
        vote1.setAgenda(createAgenda(vote1,true));
        vote1.setCpf(TestUtils.buildCpf());

        Vote vote2 = new Vote();
        vote2.setAgenda(createAgenda(vote1,false));
        vote2.setCpf(TestUtils.buildCpf());

        Vote vote3 = new Vote();
        vote3.setAgenda(createAgenda(vote1,true));
        vote3.setCpf(TestUtils.buildCpf());

        return votes;
    }

    private Agenda createAgenda(Vote vote, Boolean closure) {
        Agenda agenda = new Agenda();
        agenda.setName("agenda "+Math.random());
        agenda.setActive(closure);
        agenda.setClosure(TestUtils.buildClosure());
        agenda.getVotes().add(vote);
        return agenda;
    }

}
