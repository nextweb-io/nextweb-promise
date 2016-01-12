package io.nextweb.promise.utils;

import delight.async.Operation;
import delight.async.callbacks.ValueCallback;
import delight.functional.Closure;

import java.util.ArrayList;
import java.util.List;

import io.nextweb.promise.DataOperation;
import io.nextweb.promise.DataPromise;
import io.nextweb.promise.Fn;
import io.nextweb.promise.callbacks.DataCallback;
import io.nextweb.promise.callbacks.DataFailureCallback;
import io.nextweb.promise.callbacks.EmbeddedCallback;
import io.nextweb.promise.exceptions.DataExceptionManager;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import io.nextweb.promise.exceptions.ImpossibleException;
import io.nextweb.promise.exceptions.ImpossibleListener;
import io.nextweb.promise.exceptions.ImpossibleResult;
import io.nextweb.promise.exceptions.UnauthorizedException;
import io.nextweb.promise.exceptions.UnauthorizedListener;
import io.nextweb.promise.exceptions.UnauthorizedResult;
import io.nextweb.promise.exceptions.UndefinedException;
import io.nextweb.promise.exceptions.UndefinedListener;
import io.nextweb.promise.exceptions.UndefinedResult;

public final class CallbackUtils {

    public static final <T> ExceptionListener asExceptionListener(final ValueCallback<T> callback) {
        return new ExceptionListener() {

            @Override
            public void onFailure(final ExceptionResult r) {
                callback.onFailure(r.exception());
            }
        };
    }

    public static <ResultType> DataCallback<ResultType> embed(final DataCallback<?> callback,
            final Closure<ResultType> onSuccess) {
        return embeddedCallback(callback.getExceptionManager(), callback, onSuccess);

    }

    public static <ResultType> ValueCallback<ResultType> embedAsValueCallback(final DataCallback<?> callback,
            final Closure<ResultType> onSuccess) {
        return asValueCallback(embeddedCallback(callback.getExceptionManager(), callback, onSuccess));

    }

    @SuppressWarnings("unchecked")
    public static <ResultType> DataCallback<ResultType> embeddedCallback(final DataExceptionManager exceptionManager,
            final DataCallback<ResultType> embeddedIn) {
        return new EmbeddedCallback<ResultType>((DataCallback<Object>) embeddedIn, exceptionManager);
    }

    @SuppressWarnings("unchecked")
    public static <ResultType> DataCallback<ResultType> embeddedCallback(final DataExceptionManager exceptionManager,
            final DataCallback<?> embeddedIn, final Closure<ResultType> p_onSuccess) {
        return new EmbeddedCallback<ResultType>((DataCallback<Object>) embeddedIn, exceptionManager) {

            @Override
            public void onSuccess(final ResultType result) {
                p_onSuccess.apply(result);
            }

        };
    }

    public static <T, OP> List<Operation<T>> asOperations(final DataExceptionManager fallbackManager,
            final OP... operations) {
        final ArrayList<Operation<T>> res = new ArrayList<Operation<T>>(operations.length);

        for (final Object rawOperation : operations) {

            @SuppressWarnings("unchecked")
            final DataOperation<T> operation = (DataOperation<T>) rawOperation;

            res.add(new Operation<T>() {

                @Override
                public void apply(final ValueCallback<T> callback) {

                    DataExceptionManager manager;
                    if (operation instanceof DataPromise) {
                        final DataPromise<T> dataPromise = (DataPromise<T>) operation;
                        manager = dataPromise.getExceptionManager();
                    } else {
                        manager = fallbackManager;
                    }

                    operation.apply(CallbackUtils.asDataCallback(manager, callback));
                }
            });
        }

        return res;
    }

    public static final void triggerDataFailureCallback(final Throwable t, final DataFailureCallback callback) {

        if (t instanceof UnauthorizedException) {
            final UnauthorizedException unauthorizedException = (UnauthorizedException) t;
            callback.onUnauthorized(unauthorizedException.getResult());
            return;
        }

        if (t instanceof UndefinedException) {

            final UndefinedException undefinedException = (UndefinedException) t;
            callback.onUndefined(undefinedException.getResult());
            return;
        }

        if (t instanceof ImpossibleException) {

            final ImpossibleException impossibleException = (ImpossibleException) t;
            callback.onImpossible(impossibleException.getResult());
            return;
        }

        callback.onFailure(Fn.exception(CallbackUtils.class, t));
    }

    public static final <T> ValueCallback<T> asValueCallback(final DataCallback<T> callback) {
        return new ValueCallback<T>() {

            @Override
            public void onFailure(final Throwable t) {

                triggerDataFailureCallback(t, callback);

            }

            @Override
            public void onSuccess(final T value) {

                callback.onSuccess(value);
            }
        };
    }

    public static <ResultType> DataCallback<ResultType> asDataCallback(final DataExceptionManager manager,
            final ValueCallback<ResultType> callback) {

        final DataExceptionManager exceptionManager = new DataExceptionManager(manager);
        exceptionManager.catchExceptions(new ExceptionListener() {

            @Override
            public void onFailure(final ExceptionResult r) {
                callback.onFailure(r.exception());
            }
        });
        exceptionManager.catchUnauthorized(new UnauthorizedListener() {

            @Override
            public void onUnauthorized(final UnauthorizedResult r) {
                callback.onFailure(new UnauthorizedException(r));
            }
        });

        exceptionManager.catchUndefined(new UndefinedListener() {

            @Override
            public void onUndefined(final UndefinedResult r) {
                callback.onFailure(new UndefinedException(r));
            }
        });

        exceptionManager.catchImpossible(new ImpossibleListener() {

            @Override
            public void onImpossible(final ImpossibleResult ir) {
                callback.onFailure(new ImpossibleException(ir));
            }
        });

        return new DataCallback<ResultType>() {

            @Override
            public void onFailure(final ExceptionResult r) {
                exceptionManager.onFailure(r);
            }

            @Override
            public void onUnauthorized(final UnauthorizedResult r) {
                exceptionManager.onUnauthorized(r);
            }

            @Override
            public void onUndefined(final UndefinedResult r) {
                exceptionManager.onUndefined(r);
            }

            @Override
            public void onImpossible(final ImpossibleResult ir) {
                exceptionManager.onImpossible(ir);
            }

            @Override
            public void onSuccess(final ResultType result) {
                callback.onSuccess(result);
            }

            @Override
            public boolean hasEagerFailureListener() {
                return true;
            }

            @Override
            public boolean hasEagerUndefinedListener() {
                return true;
            }

            @Override
            public boolean hasEagerUnauthorizedListener() {
                return true;
            }

            @Override
            public boolean hasEagerImpossibleListener() {
                return true;
            }

            @Override
            public boolean hasEagerListeners() {
                return true;
            }

            @Override
            public DataExceptionManager getExceptionManager() {

                return exceptionManager;

            }

            @Override
            public <R> DataCallback<R> chain(final Closure<R> onSuccess) {

                return CallbackUtils.embed(this, onSuccess);
            }

        };
    }

}
