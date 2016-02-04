package io.nextweb.promise.exceptions;

import io.nextweb.promise.exceptions.v01.UndefinedResultData;

public class UndefinedException extends Throwable {

    private static final long serialVersionUID = 1L;

    public UndefinedResultData result;

    @Override
    public String getMessage() {
        return result.message();
    }

    public UndefinedResult getResult() {
        return result;
    };

    public UndefinedException(final UndefinedResult result) {
        super();
        if (result == null) {
            throw new NullPointerException("Result should not be null.");
        }
        this.result = new UndefinedResultData(result);
    }

    /**
     * Use only for serialization.
     */
    @Deprecated
    public UndefinedException() {
        super();
        this.result = null;
    }

}
