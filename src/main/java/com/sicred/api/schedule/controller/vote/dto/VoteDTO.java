package com.sicred.api.schedule.controller.vote.dto;

import com.sicred.api.schedule.model.enums.EnumOption;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VoteDTO {

    @NotEmpty(message = "{required.validation.nomeata}")
    String nameAgenda;

    @NotEmpty(message = "{required.validation.nomeata}")
    String cpf;

    @NotNull(message = "{required.validation.nomeata}")
    EnumOption enumOption;
}
