package io.nextweb.promise.jre;

import delight.async.Operation;
import delight.async.callbacks.ValueCallback;
import delight.functional.Closure;
import delight.promise.Promise;
import delight.promise.jre.Promises;

import io.nextweb.promise.DataOperation;
import io.nextweb.promise.DataPromise;
import io.nextweb.promise.DataPromiseImpl;
import io.nextweb.promise.Fn;
import io.nextweb.promise.callbacks.DataCallback;
import io.nextweb.promise.exceptions.DataExceptionManager;
import io.nextweb.promise.utils.CallbackUtils;

public final class DataPromises {

    public static <ResultType> DataPromise<ResultType> create(final DataOperation<ResultType> operation) {
        return create(new DataExceptionManager(null), operation);
    }

    public static <ResultType> DataPromise<ResultType> create(final DataExceptionManager exceptionManager,
            final DataOperation<ResultType> operation) {

        final Promise<ResultType> promise = Promises.create(new Operation<ResultType>() {

            @Override
            public String toString() {
                return "[(" + operation + ") wrapped by (" + super.toString() + ")]";
            }

            @Override
            public void apply(final ValueCallback<ResultType> callback) {
                final DataCallback<ResultType> dataCallback = CallbackUtils.asDataCallback(exceptionManager, callback);

                operation.apply(dataCallback);

            }
        });

        promise.addExceptionFallback(new Closure<Throwable>() {

            @Override
            public void apply(final Throwable o) {
                exceptionManager.onFailure(Fn.exception(this, o));
            }
        });

        return new DataPromiseImpl<ResultType>(operation, promise, new DataExceptionManager(null));

    }

}
