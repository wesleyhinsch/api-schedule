package com.sicred.api.schedule.agenda;

import com.sicred.api.schedule.BuildCpf;
import com.sicred.api.schedule.model.Agenda;
import com.sicred.api.schedule.model.Vote;
import com.sicred.api.schedule.model.enums.EnumOption;
import com.sicred.api.schedule.repository.AgendaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AgendaTests {


    private static final Logger logger = LoggerFactory.getLogger(AgendaTests.class);


    @Autowired
    AgendaRepository agendaRepository;

    @Before
    public void init() {
        logger.error("Clear Table agenda before test");
        agendaRepository.deleteAll();
    }

    @Test
    public void shouldAllBeConsulted(){
        for (Agenda agenda : createAgendas()) {
            agendaRepository.save(agenda);
        }
        List<Agenda> agendaResults = agendaRepository.findAll();
        Assert.assertEquals(agendaResults.size(), createAgendas().size());
    }


    private ArrayList<Agenda> createAgendas() {
        ArrayList<Agenda> agendas = new ArrayList<>();

        Agenda agenda1 = new Agenda();
        agenda1.setName("agenda "+Math.random());
        agenda1.setActive(Boolean.FALSE);
        agenda1.setClosure(Calendar.getInstance());
        agenda1.setVotes(createVotes(agenda1));

        Agenda agenda2 = new Agenda();
        agenda2.setName("agenda "+Math.random());
        agenda2.setActive(Boolean.TRUE);
        agenda2.setClosure(Calendar.getInstance());
        agenda2.setVotes(createVotes(agenda2));

        Agenda agenda3 = new Agenda();
        agenda3.setName("agenda "+Math.random());
        agenda3.setActive(Boolean.FALSE);
        agenda3.setClosure(Calendar.getInstance());
        agenda3.setVotes(createVotes(agenda3));

        Agenda agenda4 = new Agenda();
        agenda4.setName("agenda "+Math.random());
        agenda4.setActive(Boolean.TRUE);
        agenda4.setClosure(Calendar.getInstance());
        agenda4.setVotes(createVotes(agenda4));

        return agendas;
    }

    private Set<Vote> createVotes(Agenda agenda) {
        Set<Vote> votes = new HashSet<>();

        Vote vote1 = new Vote();
        vote1.setAgenda(agenda);
        vote1.setCpf(BuildCpf.build());
        vote1.setEnumOption(EnumOption.NAO);

        Vote vote2 = new Vote();
        vote2.setAgenda(agenda);
        vote2.setCpf(BuildCpf.build());
        vote2.setEnumOption(EnumOption.NAO);

        Vote vote3 = new Vote();
        vote3.setAgenda(agenda);
        vote3.setCpf(BuildCpf.build());
        vote3.setEnumOption(EnumOption.SIM);

        Vote vote4 = new Vote();
        vote4.setAgenda(agenda);
        vote4.setCpf(BuildCpf.build());
        vote4.setEnumOption(EnumOption.SIM);

        return votes;
    }

}
