package io.nextweb.promise.exceptions;

public class UnauthorizedException extends Throwable {

    private static final long serialVersionUID = 1L;

    private final UnauthorizedResult result;

    @Override
    public String getMessage() {

        return result.getMessage();
    }

    public UnauthorizedException(final UnauthorizedResult result) {
        super();
        this.result = result;
    }

    public UnauthorizedResult getResult() {
        return result;
    }

}
