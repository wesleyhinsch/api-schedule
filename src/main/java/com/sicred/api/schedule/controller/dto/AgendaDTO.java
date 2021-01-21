package com.sicred.api.schedule.controller.dto;

import lombok.Builder;

import javax.validation.constraints.NotEmpty;

@Builder(toBuilder = true)
public class AgendaDTO {
    @NotEmpty(message = "{required.validation.segundos}")
    String closure;
    @NotEmpty(message = "{required.validation.nomeata}")
    String name;
}