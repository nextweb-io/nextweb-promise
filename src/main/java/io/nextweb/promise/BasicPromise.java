package io.nextweb.promise;

import delight.async.Operation;
import delight.async.callbacks.ValueCallback;
import delight.functional.Closure;
import delight.promise.Promise;

import io.nextweb.promise.callbacks.DataCallback;
import io.nextweb.promise.exceptions.NextwebExceptionManager;

/**
 *
 * 
 * @author Max Rohde
 * 
 * @param <ResultType>
 */
public interface BasicPromise<ResultType> extends DataOperation<ResultType>, Operation<ResultType> {

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
    public void apply(ValueCallback<ResultType> callback);

    @Override
    public void apply(DataCallback<ResultType> callback);

    public DataOperation<ResultType> getOriginalOperation();

    public Promise<ResultType> getOriginalPromise();

}
