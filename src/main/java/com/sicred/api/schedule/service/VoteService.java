package com.sicred.api.schedule.service;

import com.sicred.api.schedule.model.Agenda;
import com.sicred.api.schedule.model.Vote;
import com.sicred.api.schedule.repository.AgendaRepository;
import com.sicred.api.schedule.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    AgendaRepository agendaRepository;

    public void save() {
        Vote vote = new Vote();

        validateVote(vote);

        voteRepository.save(vote);
    }

    private void validateVote(Vote vote) {
        String nomeata = "nomeata";
        Optional<Agenda> agenda = agendaRepository.findAgendaByName(nomeata);

        if(!agenda.isPresent())
            vote.setAgenda(agenda.get());
    }

}
