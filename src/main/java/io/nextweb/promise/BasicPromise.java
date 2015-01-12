package io.nextweb.promise;

import io.nextweb.promise.callbacks.NextwebCallback;
import io.nextweb.promise.exceptions.NextwebExceptionManager;
import de.mxro.async.Operation;
import de.mxro.fn.Closure;

/**
 *
 * 
 * @author Max Rohde
 * 
 * @param <ResultType>
 */
public interface BasicPromise<ResultType> extends NextwebOperation<ResultType>, Operation<ResultType> {

    /**
     * get() will ignore all defined exception interceptors to assure
     * termination of the statement.
     * 
     * @return
     */
    public ResultType get();

    public void get(Closure<ResultType> callback);

    public NextwebExceptionManager getExceptionManager();

    @Override
    public void apply(NextwebCallback<ResultType> callback);

    public NextwebOperation<ResultType> getOriginalOperation();

}
