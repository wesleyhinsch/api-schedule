package com.sicred.api.schedule.service.agenda.exception;

import com.sicred.api.schedule.exception.impl.BaseRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AgendaExistException extends BaseRuntimeException {
    private static final String KEY = "rulles.bussines.agenda.exist";

    public AgendaExistException() {
        super();
    }

    @Override
    public String getExceptionKey() {
        return KEY;
    }
}