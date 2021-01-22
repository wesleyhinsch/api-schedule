package com.sicred.api.schedule.controller.agenda;

import com.sicred.api.schedule.controller.abst.Controller;
import com.sicred.api.schedule.controller.agenda.dto.AgendaDTO;
import com.sicred.api.schedule.model.AgendaResult;
import com.sicred.api.schedule.repository.AgendaResultRepository;
import com.sicred.api.schedule.service.agenda.AgendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(value="/api/agenda")
@Api(value="API - Agenda")
public class AgendaController extends Controller {

    @Autowired
    AgendaService agendaService;

    @Autowired
    AgendaResultRepository agendaResultRepository;

    @ApiOperation(value="Create Agenda")
    @PostMapping(value = {"/v1.0"},consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createAgenda(@Validated @RequestBody AgendaDTO agendaDTO){
       agendaService.save(agendaDTO);
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value="View closed agendas")
    @GetMapping(value = {"/v2.0"},produces = MediaType.APPLICATION_JSON_VALUE)
    public Stream<ResponseEntity<AgendaResult>> viewAgendas(){
        return agendaResultRepository.findAll().stream().map(
            a -> ResponseEntity.ok().body(a)
        );
    }
}
