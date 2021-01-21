package com.sicred.api.schedule.service;

import com.sicred.api.schedule.controller.dto.VoteDTO;
import com.sicred.api.schedule.model.Agenda;
import com.sicred.api.schedule.model.Vote;
import com.sicred.api.schedule.model.enums.EnumOption;
import com.sicred.api.schedule.repository.AgendaRepository;
import com.sicred.api.schedule.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VoteService {

    private static String URL_VALIDATION_CPF = "https://user-info.herokuapp.com/users";
    private static String UNABLE_TO_VOTE = "UNABLE_TO_VOTE";

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    AgendaRepository agendaRepository;

    public void save(VoteDTO voteDTO) {
        Vote vote = new Vote();

        vote.setCpf(voteDTO.getCpf());
        vote.setEnumOption(voteDTO.getEnumOption());

        validateVote(voteDTO,vote);

        voteRepository.save(vote);
    }

    private void validateVote(VoteDTO voteDTO, Vote vote) {
        Agenda agenda = agendaRepository.findAgendaByName(voteDTO.getNameAgenda());

        /*if(agenda.getVotes().contains(vote)){
            //Exception
        }

        ResponseEntity<String> response
            = restTemplate.getForEntity(URL_VALIDATION_CPF + "/"+voteDTO.getCpf(), String.class);

       if(response.getBody().contains(UNABLE_TO_VOTE)){
            //Exception
       }*/

        vote.setAgenda(agenda);
    }

}
