package com.sicred.api.schedule.controller;

import com.sicred.api.schedule.controller.abst.Controller;
import com.sicred.api.schedule.controller.dto.VoteDTO;
import com.sicred.api.schedule.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/api/vote")
@Api(value="API Vote v.1")
public class VotoController extends Controller {

    @Autowired
    VoteService voteService;

    @ApiOperation(value="Vote")
    @PostMapping(value = {"/v1.0"},consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> votar(@Valid @RequestBody VoteDTO voteDTO) {
        voteService.save(voteDTO);
        return ResponseEntity.ok(SUCCESS);
    }
}
