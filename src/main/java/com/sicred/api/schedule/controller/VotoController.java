package com.sicred.api.schedule.controller;

import com.sicred.api.schedule.controller.dto.VoteDTO;
import com.sicred.api.schedule.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/api/voto")
@Api(value="API Voto")
public class VotoController {

    @Autowired
    VoteService voteService;

    @ApiOperation(value="Votar")
    @PostMapping("/v1.0")
    public ResponseEntity<String> votar(@Valid @RequestBody VoteDTO voteDTO) {
        voteService.save(voteDTO);
        return ResponseEntity.ok("Success!");
    }
}
