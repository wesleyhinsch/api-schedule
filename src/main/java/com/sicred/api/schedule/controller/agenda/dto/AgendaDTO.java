package com.sicred.api.schedule.controller.agenda.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AgendaDTO {

    @NotNull(message = "required.validation.agenda.closure")
    @Max(message = "max.validation.agenda.closure", value = 999)
    int closure;

    @NotEmpty(message = "required.validation.agenda.name")
    String name;
}