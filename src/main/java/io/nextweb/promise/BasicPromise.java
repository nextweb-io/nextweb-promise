package io.nextweb.promise;

import delight.async.Operation;
import delight.async.callbacks.ValueCallback;
import delight.functional.Closure;
import delight.promise.Promise;

import io.nextweb.promise.callbacks.DataCallback;
import io.nextweb.promise.exceptions.DataExceptionManager;

/**
 * <p>
 * A basic resolvable promise.
 * 
 * @author Max Rohde
 * 
 * @param <ResultType>
 */
public interface BasicPromise<ResultType> extends DataOperation<ResultType>, Operation<ResultType> {

    /**
     * <p>
     * Note: get() will ignore all defined exception interceptors to assure
     * termination of the statement.
     * 
     * @return The result that can be obtained by resolving this promise.
     */
    public ResultType get();

    public void get(Closure<ResultType> callback);

    public DataExceptionManager getExceptionManager();

    @Override
    public void apply(ValueCallback<ResultType> callback);

    @Override
    public void apply(DataCallback<ResultType> callback);

    public DataOperation<ResultType> getOriginalOperation();

    public Promise<ResultType> getOriginalPromise();

}
