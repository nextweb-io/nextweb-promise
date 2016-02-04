package io.nextweb.promise.exceptions;

public class UnauthorizedException extends Throwable {

    private static final long serialVersionUID = 1L;

    private final UnauthorizedResult result;

    @Override
    public String getMessage() {

        return result.getMessage();
    }

    public UnauthorizedResult getResult() {
        return result;
    }

    public UnauthorizedException(final UnauthorizedResult result) {
        super();
        assert result != null;

        this.result = result;
    }

    /**
     * Use only for serialization.
     */
    @Deprecated
    public UnauthorizedException() {
        super();
        this.result = null;
    }

}
