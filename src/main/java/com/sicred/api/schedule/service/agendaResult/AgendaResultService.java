package com.sicred.api.schedule.service.agendaResult;

import com.sicred.api.schedule.model.AgendaResult;
import com.sicred.api.schedule.repository.AgendaResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaResultService {

    private static final Logger logger = LoggerFactory.getLogger(AgendaResultService.class);

    @Autowired
    AgendaResultRepository agendaResultRepository;

    public List<AgendaResult> findAll() {
        try{
            logger.info("Find all agenda result");
            return agendaResultRepository.findAll();
        }catch (Exception e){
            logger.error("Error find all agenda result");
            throw  e;
        }
    }
}