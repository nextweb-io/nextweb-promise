package io.nextweb.promise.exceptions;

public class ImpossibleException extends Throwable {

    private static final long serialVersionUID = 1L;

    private final ImpossibleResult result;

    public ImpossibleException(final ImpossibleResult result) {
        super();
        this.result = result;
    }

    public ImpossibleResult getResult() {
        return result;
    }

}
