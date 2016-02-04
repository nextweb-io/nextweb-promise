package io.nextweb.promise.exceptions;

import io.nextweb.promise.exceptions.v01.UnauthorizedResultData;

public class UnauthorizedException extends Throwable {

    private static final long serialVersionUID = 1L;

    public UnauthorizedResultData result;

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

        this.result = new UnauthorizedResultData(result);
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
