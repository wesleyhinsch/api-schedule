package com.sicred.api.schedule.controller.dto;

import com.sicred.api.schedule.model.enums.EnumOption;
import lombok.Builder;

import javax.validation.constraints.NotEmpty;

@Builder(toBuilder = true)
public class VoteDTO {

    @NotEmpty(message = "{required.validation.nomeata}")
    String nomeAta;

    String cpf;

    EnumOption enumOption;
}
