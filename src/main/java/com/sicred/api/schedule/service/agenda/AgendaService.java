package com.sicred.api.schedule.service.agenda;

import com.sicred.api.schedule.controller.agenda.dto.AgendaDTO;
import com.sicred.api.schedule.model.Agenda;
import com.sicred.api.schedule.repository.AgendaRepository;
import com.sicred.api.schedule.service.agenda.exception.AgendaExistException;
import com.sicred.api.schedule.service.agenda.exception.AgendaStartedException;
import com.sicred.api.schedule.service.agenda.exception.ClosedAgendaException;
import com.sicred.api.schedule.service.agenda.exception.DuplicateRegisteredAgendaException;
import com.sicred.api.schedule.service.agenda.exception.NotPossibleStartInexistentAgendaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class AgendaService {

    private static final Logger logger = LoggerFactory.getLogger(AgendaService.class);

    @Value("${agenda.default.closure}")
    private int defaulClosure;

    @Autowired
    AgendaRepository agendaRepository;

    public void createAgenda(AgendaDTO agendaDTO) {
        try{
            Agenda agenda = new Agenda();

            if(agendaRepository.existsByName(agendaDTO.getName())){
                logger.error("Name agenda exist :"+agenda.getName());
                throw new AgendaExistException();
            }

            if(agendaDTO.getClosure()<=0) {
                agendaDTO.setClosure(defaulClosure);
                logger.warn("Invalid closing value assuming one minute as closing value");
            }

            agenda.setClosure(agendaDTO.getClosure());
            agenda.setName(agendaDTO.getName());
            agenda.setActive(Boolean.TRUE);
            agenda.setStarted(Boolean.FALSE);

            agendaRepository.save(agenda);
            logger.info("save agenda :"+agenda.getName());
        }catch (IncorrectResultSizeDataAccessException i){
            throw new DuplicateRegisteredAgendaException();
        }
    }

    public void startAgenda(String name) {
        try{
            Agenda agenda = agendaRepository.findAgendaByName(name).orElseThrow(
                NotPossibleStartInexistentAgendaException::new);

            if(isStarted(agenda)){
                logger.info("started agenda :"+agenda.getName());
                throw new AgendaStartedException();
            }

            if(!isActive(agenda)){
                logger.info("started not active :"+agenda.getName());
                throw new ClosedAgendaException();
            }

            Calendar current = Calendar.getInstance();
            current.add(Calendar.SECOND,agenda.getClosure());
            Date date = new Date(current.getTimeInMillis());
            agenda.setClosing(date);

            agenda.setStarted(Boolean.TRUE);
            agendaRepository.saveAndFlush(agenda);
            logger.info("start agenda :"+agenda.getName());
        }catch (Exception e){
            logger.error("error start agenda error :"+e.getMessage());
            throw e;
        }
    }

    public List<Agenda> findAllOpen() {
        return agendaRepository.findByActive(Boolean.TRUE);
    }


    public Boolean isActive(Agenda agenda){
        return agenda.getActive().equals(Boolean.TRUE)?Boolean.TRUE:Boolean.FALSE;
    }

    public Boolean isStarted(Agenda agenda){
        return agenda.getStarted().equals(Boolean.TRUE)?Boolean.TRUE:Boolean.FALSE;
    }

    public Boolean enabledToClose(Agenda agenda){
        Calendar current = Calendar.getInstance();
        Date date = new Date(current.getTimeInMillis());
        if(agenda.getClosing()!= null){
            if(agenda.getClosing().before(date) && this.isActive(agenda) && this.isStarted(agenda)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}