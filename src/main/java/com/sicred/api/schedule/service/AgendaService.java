package com.sicred.api.schedule.service;

import com.sicred.api.schedule.controller.dto.AgendaDTO;
import com.sicred.api.schedule.model.Agenda;
import com.sicred.api.schedule.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class AgendaService {

    @Autowired
    AgendaRepository agendaRepository;

    public void save(AgendaDTO agendaDTO) {
        Calendar dateCurrente = Calendar.getInstance();
        Agenda agenda = new Agenda();

        dateCurrente.add(Calendar.SECOND,agendaDTO.getClosure());
        agenda.setClosure(dateCurrente);
        agenda.setName(agendaDTO.getName());
        agenda.setActive(Boolean.TRUE);

        agendaRepository.findAgendaByName(agendaDTO.getName());

        agendaRepository.save(agenda);
    }

}
