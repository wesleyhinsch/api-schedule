package com.sicred.api.schedule.controller.vote;

import com.sicred.api.schedule.controller.abst.Controller;
import com.sicred.api.schedule.controller.vote.dto.VoteDTO;
import com.sicred.api.schedule.service.vote.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/vote")
@Api(value="API - Vote")
public class VotoController extends Controller {

    @Autowired
    VoteService voteService;

    @ApiOperation(value="Vote")
    @PostMapping(value = {"/v1.0"},consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> votar(@Validated @RequestBody VoteDTO voteDTO) {
        voteService.save(voteDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
