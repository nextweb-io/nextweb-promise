package io.nextweb.promise;

import delight.async.callbacks.ValueCallback;
import delight.functional.Closure;
import delight.promise.Promise;

import io.nextweb.promise.callbacks.DataCallback;
import io.nextweb.promise.exceptions.DataExceptionManager;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ImpossibleListener;
import io.nextweb.promise.exceptions.NextwebExceptionUtils;
import io.nextweb.promise.exceptions.UnauthorizedListener;
import io.nextweb.promise.exceptions.UndefinedListener;

public class DataPromiseImpl<ResultType> implements DataPromise<ResultType> {
    protected DataOperation<ResultType> operation;
    protected Promise<ResultType> promise;
    protected DataExceptionManager exceptionManager;

    @Override
    public ResultType get() {

        if (promise.cachedResult() != null) {
            return promise.cachedResult();
        }

        return promise.get();
    }

    @Override
    public void get(final Closure<ResultType> callback) {

        promise.get(callback);

    }

    @Override
    public DataExceptionManager getExceptionManager() {
        return exceptionManager;
    }

    @Override
    public void apply(final ValueCallback<ResultType> callback) {
        promise.apply(callback);
    }

    @Override
    public void apply(final DataCallback<ResultType> callback) {
        final ValueCallback<ResultType> valueCallback = new ValueCallback<ResultType>() {

            @Override
            public void onFailure(final Throwable t) {

                if (callback.hasEagerFailureListener() || callback.hasEagerImpossibleListener()
                        || callback.hasEagerUnauthorizedListener() || callback.hasEagerUndefinedListener()) {

                    if (NextwebExceptionUtils.nextwebCallbackRequired(t, callback)) {

                        return;
                    }
                    callback.onFailure(Fn.exception(this, t));
                    return;
                }

                // System.out.println("report " + t);

                exceptionManager.onFailure(Fn.exception(this, t));
            }

            @Override
            public void onSuccess(final ResultType value) {
                callback.onSuccess(value);
            }
        };

        promise.apply(valueCallback);

    }

    @Override
    public DataOperation<ResultType> getOriginalOperation() {
        return operation;
    }

    @Override
    public DataPromise<ResultType> catchExceptions(final ExceptionListener listener) {
        exceptionManager.catchExceptions(listener);
        return this;
    }

    @Override
    public DataPromise<ResultType> catchUnauthorized(final UnauthorizedListener listener) {
        exceptionManager.catchUnauthorized(listener);
        return this;
    }

    @Override
    public DataPromise<ResultType> catchUndefined(final UndefinedListener listener) {
        exceptionManager.catchUndefined(listener);
        return this;
    }

    @Override
    public DataPromise<ResultType> catchImpossible(final ImpossibleListener listener) {
        exceptionManager.catchImpossible(listener);
        return this;
    }

    public DataPromiseImpl(final DataOperation<ResultType> operation, final Promise<ResultType> promise,
            final DataExceptionManager exceptionManager) {
        super();
        this.operation = operation;
        this.promise = promise;
        this.exceptionManager = exceptionManager;

        // promise.addExceptionFallback(new Closure<Throwable>() {
        //
        // @Override
        // public void apply(final Throwable o) {
        // exceptionManager.onFailure(Fn.exception(this, o));
        // }
        // });
    }

    @Override
    public String toString() {

        return "[(" + operation + ") wrapped by (" + super.toString() + ")]";
    }

    @Override
    public Promise<ResultType> getOriginalPromise() {

        return promise;
    }
}
