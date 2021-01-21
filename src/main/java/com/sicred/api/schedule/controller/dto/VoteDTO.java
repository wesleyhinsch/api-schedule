package com.sicred.api.schedule.controller.dto;

import com.sicred.api.schedule.model.enums.EnumOption;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class VoteDTO {

    @NotEmpty(message = "{required.validation.nomeata}")
    String nameAgenda;

    String cpf;

    EnumOption enumOption;
}
