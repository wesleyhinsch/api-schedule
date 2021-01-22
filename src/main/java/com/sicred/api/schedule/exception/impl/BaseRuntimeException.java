package com.sicred.api.schedule.exception.impl;


import com.sicred.api.schedule.exception.MessageException;

import java.util.Map;

public abstract class BaseRuntimeException extends RuntimeException implements MessageException {
    private final Map<String, Object> mapDetails;

    public BaseRuntimeException() {
        mapDetails = null;
    }

    public BaseRuntimeException(final Map<String, Object> mapDetails) {
        this.mapDetails = mapDetails;
    }

    public abstract String getExceptionKey();

    public Map<String, Object> getMapDetails() {
        return this.mapDetails;
    }

}