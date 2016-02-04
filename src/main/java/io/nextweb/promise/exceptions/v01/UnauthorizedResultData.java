package io.nextweb.promise.exceptions.v01;

import java.io.Serializable;

import io.nextweb.promise.exceptions.UnauthorizedResult;

public class UnauthorizedResultData implements UnauthorizedResult, Serializable {

    private static final long serialVersionUID = 1L;

    public String message;
    public transient Object origin;
    public String originClass;

    public Serializable type;

    @Override
    public Object origin() {
        if (origin != null) {
            return origin;
        }
        return null;
    }

    @Override
    public String getMessage() {

        return message;
    }

    @Override
    public Object getType() {

        return type;
    }

    public UnauthorizedResultData(final String message, final Object origin, final Serializable type) {
        super();
        this.message = message;
        this.origin = origin;
        this.originClass = origin.getClass().toString() + "->" + origin.toString();
        this.type = type;
    }

    public UnauthorizedResultData(final UnauthorizedResult result) {

    }

    @Deprecated
    public UnauthorizedResultData() {
        super();

    }

}
