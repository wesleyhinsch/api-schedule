package com.sicred.api.schedule.service.agenda;

import com.sicred.api.schedule.controller.agenda.dto.AgendaDTO;
import com.sicred.api.schedule.model.Agenda;
import com.sicred.api.schedule.repository.AgendaRepository;
import com.sicred.api.schedule.service.agenda.exception.RegisteredAgendaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class AgendaService {

    @Value("${agenda.default.closure}")
    private int defaulClosure;

    @Autowired
    AgendaRepository agendaRepository;

    public void save(AgendaDTO agendaDTO) {
        try{
            Calendar dateCurrente = Calendar.getInstance();
            Agenda agenda = new Agenda();

            if(agendaDTO.getClosure()>=0)
                agendaDTO.setClosure(defaulClosure);

            dateCurrente.add(Calendar.SECOND,agendaDTO.getClosure());
            agenda.setClosure(dateCurrente);
            agenda.setName(agendaDTO.getName());
            agenda.setActive(Boolean.TRUE);

            agendaRepository.findAgendaByName(agendaDTO.getName());

            agendaRepository.save(agenda);

        }catch (IncorrectResultSizeDataAccessException i){
            throw new RegisteredAgendaException();
        }
    }

}
