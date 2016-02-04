package io.nextweb.promise.exceptions.v01;

import java.io.Serializable;

import io.nextweb.promise.exceptions.UnauthorizedResult;

public class UnauthorizedResultData implements UnauthorizedResult, Serializable {

    private static final long serialVersionUID = 1L;

    public String message;
    public transient Object origin;
    public String originClass;

    @Override
    public Object origin() {
        if (origin != null) {
            return origin;
        }
        return null;
    }

    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getType() {
        // TODO Auto-generated method stub
        return null;
    }

}
