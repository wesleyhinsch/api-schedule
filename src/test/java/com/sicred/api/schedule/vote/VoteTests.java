package com.sicred.api.schedule.vote;

import com.sicred.api.schedule.TestUtils;
import com.sicred.api.schedule.model.Agenda;
import com.sicred.api.schedule.model.Vote;
import com.sicred.api.schedule.repository.VoteRepository;
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
public class VoteTests {

    private static final Logger logger = LoggerFactory.getLogger(VoteTests.class);

    @Autowired
    VoteRepository voteRepository;

    @Before
    public void init() {
        logger.error("Clear Table vote before test");
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


    private ArrayList<Vote> createVotes() {
        ArrayList<Vote> votes = new ArrayList<>();

        Vote vote1 = new Vote();
        vote1.setAgenda(createAgenda(vote1,true));
        vote1.setCpf(TestUtils.build());

        Vote vote2 = new Vote();
        vote2.setAgenda(createAgenda(vote1,false));
        vote2.setCpf(TestUtils.build());

        Vote vote3 = new Vote();
        vote3.setAgenda(createAgenda(vote1,true));
        vote3.setCpf(TestUtils.build());

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
