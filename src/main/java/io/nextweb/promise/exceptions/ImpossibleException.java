package io.nextweb.promise.exceptions;

/**
 * <p>
 * When a modifying statement cannot be executed on the network.
 * 
 * @author <a href="http://www.mxro.de">Max Rohde</a>
 *
 */
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

    @Override
    public String getMessage() {
        return result.message();
    }

}
