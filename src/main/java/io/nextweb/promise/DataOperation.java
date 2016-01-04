package io.nextweb.promise;

import io.nextweb.promise.callbacks.DataCallback;

/**
 * <p>
 * Abstraction for a basic operation on data.
 * <p>
 * In difference to a {@link BasicPromise}, this operation supports additional
 * callback methods specified in {@link DataCallback}.
 * 
 * @author <a href="http://www.mxro.de">Max Rohde</a>
 *
 * @param <ResultType>
 */
public interface DataOperation<ResultType> {

    /**
     * <p>
     * Called to perform this operation with the specified callback.
     * 
     * @param callback
     *            The callback to be called once the operation has been
     *            completed.
     */
    public void apply(DataCallback<ResultType> callback);

}
