package io.nextweb.promise.exceptions.v01;

import java.io.Serializable;

import io.nextweb.promise.exceptions.ImpossibleResult;

public class ImpossibleResultData implements Serializable, ImpossibleResult {

    private static final long serialVersionUID = 1L;

    public transient Object origin;
    public String originClass;
    public String message;
    public transient Object cause;
    public String causeString;

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

    @Override
    public Object cause() {
        if (cause != null) {
            return cause;
        }
        return causeString;
    }

    public ImpossibleResultData(final Object origin, final String message, final Object cause) {
        super();
        this.origin = origin;
        this.originClass = origin.getClass().toString() + "->" + origin.toString();
        this.message = message;
        this.cause = cause;
        this.causeString = cause.toString();
    }

    public ImpossibleResultData(final ImpossibleResult result) {
        this(result.origin(), result.message(), result.cause());
    }

    /**
     * Use for deserialization only.
     */
    @Deprecated
    public ImpossibleResultData() {
        super();
    }

}
