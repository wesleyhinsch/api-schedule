package com.sicred.api.schedule.agenda;

import com.sicred.api.schedule.ApiScheduleApplicationTests;
import com.sicred.api.schedule.TestUtils;
import com.sicred.api.schedule.controller.agenda.dto.AgendaDTO;
import com.sicred.api.schedule.model.Agenda;
import com.sicred.api.schedule.repository.AgendaRepository;
import com.sicred.api.schedule.service.agenda.AgendaService;
import com.sicred.api.schedule.service.agenda.exception.AgendaExistException;
import com.sicred.api.schedule.service.agenda.exception.AgendaStartedException;
import com.sicred.api.schedule.service.agenda.exception.ClosedAgendaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AgendaTests extends ApiScheduleApplicationTests {


    private static final Logger logger = LoggerFactory.getLogger(AgendaTests.class);

    @Autowired
    AgendaRepository agendaRepository;

    @Autowired
    AgendaService agendaService;

    @Before
    public void init() {
        logger.error("Clear Table agenda before test");
        agendaRepository.deleteAll();
    }

    @Test
    public void shouldAllBeConsulted(){
        for (Agenda agenda : TestUtils.createAgendas()) {
            agendaRepository.save(agenda);
        }
        List<Agenda> agendaResults = agendaRepository.findAll();
        Assert.assertEquals(agendaResults.size(), TestUtils.createAgendas().size());
    }

    @Test
    public void shouldAgendaExistException(){
        AgendaDTO agendaDTO1 =  TestUtils.buildAgendaDto("agendaDTO1");
        AgendaDTO agendaDTO2 =  TestUtils.buildAgendaDto("agendaDTO1");

        agendaService.createAgenda(agendaDTO1);

        Assertions.assertThrows(AgendaExistException.class, () -> {
            agendaService.createAgenda(agendaDTO2);
        });
    }

    @Test
    public void shouldAgendaStartedException(){

        AgendaDTO agendaDTO1 = TestUtils.buildAgendaDto(null);

        agendaService.createAgenda(agendaDTO1);

        Optional<Agenda> agendaOptional = agendaRepository.findAgendaByName(agendaDTO1.getName());

        if(agendaOptional.isPresent()){
            agendaOptional.get().setStarted(Boolean.TRUE);
            agendaRepository.save(agendaOptional.get());
        }

        Assertions.assertThrows(AgendaStartedException.class, () -> {
            agendaService.startAgenda(agendaDTO1.getName());
        });
    }

    @Test
    public void shouldClosedAgendaException(){

        AgendaDTO agendaDTO1 = TestUtils.buildAgendaDto(null);

        agendaService.createAgenda(agendaDTO1);

        Optional<Agenda> agendaOptional = agendaRepository.findAgendaByName(agendaDTO1.getName());

        if(agendaOptional.isPresent()){
            agendaOptional.get().setActive(Boolean.FALSE);
            agendaRepository.save(agendaOptional.get());
        }

        Assertions.assertThrows(ClosedAgendaException.class, () -> {
            agendaService.startAgenda(agendaDTO1.getName());
        });
    }

}
