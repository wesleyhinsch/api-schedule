package com.sicred.api.schedule.controller.agenda;

import com.sicred.api.schedule.controller.agenda.dto.AgendaDTO;
import com.sicred.api.schedule.model.Agenda;
import com.sicred.api.schedule.model.AgendaResult;
import com.sicred.api.schedule.service.agenda.AgendaService;
import com.sicred.api.schedule.service.agendaResult.AgendaResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/agenda")
@Api(value="API - Agenda")
public class AgendaController {

    @Autowired
    AgendaService agendaService;

    @Autowired
    AgendaResultService agendaResultService;

    @ApiOperation(value="Create Agenda")
    @PostMapping(value = {"/v1.0"},consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createAgenda(@Validated @RequestBody AgendaDTO agendaDTO){
       agendaService.createAgenda(agendaDTO);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value="Started Agenda")
    @PutMapping(value={"/v1.0/{name}"},consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> startedAgenda(@PathVariable String name){
        agendaService.startAgenda(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value="View closed agendas")
    @GetMapping(value = {"/v1.0"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String>  viewOpenAgendas(){
        List<Agenda> agendas = agendaService.findAllOpen();

        List<JSONObject> entities = new ArrayList<JSONObject>();
        for (Agenda agenda : agendas) {
            JSONObject entity = new JSONObject();
            entity.put("name", agenda.getName());
            entities.add(entity);
        }
        return ResponseEntity.status(HttpStatus.OK).body(entities.toString());
    }

    @ApiOperation(value="View closed agendas")
    @GetMapping(value = {"/v2.0"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> viewClosedAgendas(){
        List<AgendaResult> agendaResults = agendaResultService.findAll();

        List<JSONObject> entities = new ArrayList<JSONObject>();
        for (AgendaResult  agendaResult: agendaResults) {
            JSONObject entity = new JSONObject();
            entity.put("Agenda", agendaResult.getAgendaName());
            entity.put("VotosNao,", agendaResult.getVoteNo());
            entity.put("VotosSim", agendaResult.getVoteYes());
            entities.add(entity);
        }
        return ResponseEntity.status(HttpStatus.OK).body(entities.toString());
    }
}
