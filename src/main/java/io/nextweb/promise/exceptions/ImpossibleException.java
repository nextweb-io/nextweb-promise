package io.nextweb.promise.exceptions;

import io.nextweb.promise.exceptions.v01.ImpossibleResultData;

/**
 * <p>
 * When a modifying statement cannot be executed on the network.
 * 
 * @author <a href="http://www.mxro.de">Max Rohde</a>
 *
 */
public class ImpossibleException extends Throwable {

    private static final long serialVersionUID = 1L;

    public ImpossibleResultData result;

    public ImpossibleResult getResult() {
        return result;
    }

    @Override
    public String getMessage() {
        return result.message();
    }

    public ImpossibleException(final ImpossibleResult result) {
        super();
        assert result != null;

        this.result = new ImpossibleResultData(result);
    }

    /**
     * Use only for serialization.
     */
    @Deprecated
    public ImpossibleException() {
        super();
        result = null;
    }

}
