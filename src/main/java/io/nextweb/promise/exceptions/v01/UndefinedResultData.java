package io.nextweb.promise.exceptions.v01;

import java.io.Serializable;

import io.nextweb.promise.exceptions.UndefinedResult;

public class UndefinedResultData implements UndefinedResult, Serializable {

    private static final long serialVersionUID = 1L;

    public String message;

    public transient Object origin;

    public String originClass;

    @Override
    public Object origin() {
        if (origin != null) {
            return origin;
        }
        return originClass;
    }

    @Override
    public String message() {

        return message;
    }

    public UndefinedResultData(final String message, final Object origin) {
        super();
        this.message = message;
        this.origin = origin;
        this.originClass = origin.getClass().toString() + "->" + origin.toString();
    }

    public UndefinedResultData(final UndefinedResult result) {
        this(result.message(), result.origin());
    }

    /**
     * Use only for deserialization.
     */
    @Deprecated
    public UndefinedResultData() {
        super();
    }

}
