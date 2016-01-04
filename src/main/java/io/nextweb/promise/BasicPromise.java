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
     * This method will attempt to get the result of this promise.
     * <p>
     * If the promise has already been resolved, the last result obtained is
     * returned (e.g. the promise is not resolved anew).
     * <p>
     * Note: get() will ignore all defined exception interceptors to assure
     * termination of the statement.
     * 
     * @return The result that can be obtained by resolving this promise.
     */
    public ResultType get();

    /**
     * <p>
     * The method will attempt to resolve this promise and call the specified
     * callback once the promise is resolved.
     * 
     * @param callback
     */
    public void get(Closure<ResultType> callback);

    public DataExceptionManager getExceptionManager();

    @Override
    public void apply(ValueCallback<ResultType> callback);

    @Override
    public void apply(DataCallback<ResultType> callback);

    public DataOperation<ResultType> getOriginalOperation();

    public Promise<ResultType> getOriginalPromise();

}
