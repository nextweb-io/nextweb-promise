package io.nextweb.promise.exceptions;

public class UndefinedException extends Throwable {

    private static final long serialVersionUID = 1L;

    public transient UndefinedResult result;

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
        this.result = result;
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
