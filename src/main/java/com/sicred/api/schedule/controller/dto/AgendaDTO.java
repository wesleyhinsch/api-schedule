package com.sicred.api.schedule.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AgendaDTO {
    @NotEmpty(message = "{required.validation.segundos}")
    String closure;
    @NotEmpty(message = "{required.validation.nomeata}")
    String name;
}