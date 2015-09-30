package io.nextweb.promise.exceptions;

public class UndefinedException extends Throwable {

    private static final long serialVersionUID = 1L;

    private final UndefinedResult result;

    @Override
    public String getMessage() {

        return result.message();
    }

    public UndefinedException(final UndefinedResult result) {
        super();
        this.result = result;
    }

    public UndefinedResult getResult() {
        return result;
    };

}
