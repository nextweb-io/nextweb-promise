package io.nextweb.promise;

import io.nextweb.promise.callbacks.DataCallback;

/**
 * <p>
 * Abstraction for a basic operation on data.
 * 
 * @author <a href="http://www.mxro.de">Max Rohde</a>
 *
 * @param <ResultType>
 */
public interface DataOperation<ResultType> {

    public void apply(DataCallback<ResultType> callback);

}
