package com.sicred.api.schedule.service;

import com.sicred.api.schedule.model.Agenda;
import com.sicred.api.schedule.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AgendaService {

    @Autowired
    AgendaRepository agendaRepository;

    public void save() {
        Agenda agenda = new Agenda();
        agendaRepository.save(agenda);
    }

}
