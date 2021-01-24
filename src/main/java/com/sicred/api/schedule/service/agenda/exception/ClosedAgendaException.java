package com.sicred.api.schedule.service.agenda.exception;

import com.sicred.api.schedule.exception.impl.BaseRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ClosedAgendaException extends BaseRuntimeException {
    private static final String KEY = "rulles.bussines.agenda.closed.start";

    public ClosedAgendaException() {
        super();
    }

    @Override
    public String getExceptionKey() {
        return KEY;
    }
}