package com.sicred.api.schedule.jms.scheduled;

import com.sicred.api.schedule.jms.AgendaAMQPConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicred.api.schedule.jms.payload.AgendaPayload;
import com.sicred.api.schedule.model.Agenda;
import com.sicred.api.schedule.model.Vote;
import com.sicred.api.schedule.model.enums.EnumOption;
import com.sicred.api.schedule.repository.AgendaRepository;
import com.sicred.api.schedule.service.agenda.AgendaService;
import org.hibernate.Hibernate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
@EnableScheduling
public class ScheduledAMQP {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private AgendaService agendaService;

    @Scheduled(fixedRate = 1000)
    private void checkClosingAgenda() {
        List<Agenda> agendas =  agendaRepository.findByActive(Boolean.TRUE);

        for (Agenda agenda : agendas) {
            if (agendaService.enabledToClose(agenda)) {
                accounting(agenda);
                agenda.setActive(Boolean.FALSE);
                agendaRepository.save(agenda);
            }
        }
    }

    private void accounting(Agenda agenda) {

        ArrayList<Vote> votesNO = new ArrayList<Vote>();
        ArrayList<Vote> votesYES = new ArrayList<Vote>();

        //verificar se vai quebrar ao retirar
        Hibernate.initialize(agenda.getVotes());

        for (Vote vote: agenda.getVotes()) {
            if (vote.getEnumOption().equals(EnumOption.NAO)) {
                votesNO.add(vote);
            } else {
                votesYES.add(vote);
            }
        }

        AgendaPayload  agendaPayload= new AgendaPayload();
        agendaPayload.setAgendaName(agenda.getName());
        agendaPayload.setVoteNo(votesNO.size());
        agendaPayload.setVoteYes(votesYES.size());

        sendMachineToRabbit(agendaPayload);
    }

    public void sendMachineToRabbit(AgendaPayload agendaPayload) {
        try {
            String json = new ObjectMapper().writeValueAsString(agendaPayload);
            rabbitTemplate.convertAndSend(AgendaAMQPConfig.EXCHANGE_NAME, "", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
