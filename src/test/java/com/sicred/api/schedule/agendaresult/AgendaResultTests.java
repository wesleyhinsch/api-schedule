package com.sicred.api.schedule.agendaresult;

import com.sicred.api.schedule.model.AgendaResult;
import com.sicred.api.schedule.repository.AgendaResultRepository;
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
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AgendaResultTests {


    private static final Logger logger = LoggerFactory.getLogger(AgendaResultTests.class);


    @Autowired
    AgendaResultRepository agendaResultRepositoryTest;

    @Before
    public void init() {
        logger.error("Clear Table agenda_result before test");
        agendaResultRepositoryTest.deleteAll();
    }

    @Test
    public void shouldAllBeConsulted(){
        for (AgendaResult agendaResult : createAgendaResults()) {
            agendaResultRepositoryTest.save(agendaResult);
        }
        List<AgendaResult> agendaResults = agendaResultRepositoryTest.findAll();
        Assert.assertEquals(agendaResults.size(), createAgendaResults().size());
    }


    private ArrayList<AgendaResult> createAgendaResults() {
        ArrayList<AgendaResult> agendaResults = new ArrayList<>();

        AgendaResult agendaResult1 = new AgendaResult();
        agendaResult1.setAgendaName("agenda_result "+Math.random());
        agendaResult1.setVoteNo(Double.valueOf(Math.random()).longValue());
        agendaResult1.setVoteYes(Double.valueOf(Math.random()).longValue());
        agendaResults.add(agendaResult1);

        AgendaResult agendaResult2 = new AgendaResult();
        agendaResult2.setAgendaName("agenda_result "+Math.random());
        agendaResult2.setVoteNo(Double.valueOf(Math.random()).longValue());
        agendaResult2.setVoteYes(Double.valueOf(Math.random()).longValue());
        agendaResults.add(agendaResult2);

        AgendaResult agendaResult3 = new AgendaResult();
        agendaResult3.setAgendaName("agenda_result "+Math.random());
        agendaResult3.setVoteNo(Double.valueOf(Math.random()).longValue());
        agendaResult3.setVoteYes(Double.valueOf(Math.random()).longValue());
        agendaResults.add(agendaResult3);

        return agendaResults;
    }

}
