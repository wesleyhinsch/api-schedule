package com.sicred.api.schedule.service.vote;

import com.sicred.api.schedule.controller.vote.dto.VoteDTO;
import com.sicred.api.schedule.model.Agenda;
import com.sicred.api.schedule.model.Vote;
import com.sicred.api.schedule.repository.AgendaRepository;
import com.sicred.api.schedule.repository.VoteRepository;
import com.sicred.api.schedule.service.agenda.exception.ClosedAgendaException;
import com.sicred.api.schedule.service.agenda.exception.NotInexistentAgendaException;
import com.sicred.api.schedule.service.vote.exception.InvalidCpfException;
import com.sicred.api.schedule.service.vote.exception.UnableToVoteException;
import com.sicred.api.schedule.service.vote.exception.VoteDuplicateException;
import com.sicred.api.schedule.service.vote.exception.VoteInNotStartedAgendaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class VoteService {

    private static String UNABLE_TO_VOTE = "UNABLE_TO_VOTE";

    private static final Logger logger = LoggerFactory.getLogger(VoteService.class);

    @Value("${vote.url.validation.cpf}")
    private String urlValidationCpf;


    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    AgendaRepository agendaRepository;

    public void vote(VoteDTO voteDTO) {
        try{
            Vote vote = new Vote();

            vote.setCpf(voteDTO.getCpf().replaceAll("[./-]", ""));
            vote.setEnumOption(voteDTO.getEnumOption());

            validateVote(voteDTO,vote);

            voteRepository.save(vote);
            logger.info("save vote :"+vote.getEnumOption()+" in agenda :"+vote.getAgenda());
        }catch (Exception e){
            logger.error("error vote :"+e.getMessage());
            throw e;
        }
    }

    private void validateVote(VoteDTO voteDTO, Vote vote) {

        Agenda agenda = agendaRepository.findAgendaByName(voteDTO.getNameAgenda()).orElseThrow(NotInexistentAgendaException::new);

        vote.setAgenda(agenda);

        if (!agenda.getActive()){
            logger.error("agenda not active : "+ agenda.getName()); ;
            throw new ClosedAgendaException();
        }

        if (!agenda.getStarted()){
            logger.error("agenda not started :"+ agenda.getName());
            throw new VoteInNotStartedAgendaException();
        }

        if (agenda.getVotes().contains(vote)){
            logger.error("agenda :"+agenda.getName()+" contains vote :"+ vote.getCpf());
            throw new VoteDuplicateException();
        }

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(urlValidationCpf + "/" + voteDTO.getCpf(), String.class);
            logger.info("cpf valid :"+vote.getCpf());
            if (response.getBody().contains(UNABLE_TO_VOTE)){
                logger.error("cpf invalid :"+vote.getCpf());
                throw new UnableToVoteException();
            }
        } catch (HttpClientErrorException h) {
            throw new InvalidCpfException();
        }
    }

}
