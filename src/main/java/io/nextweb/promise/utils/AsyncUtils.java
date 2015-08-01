package io.nextweb.promise.utils;

import delight.async.AsyncCommon;
import delight.async.callbacks.ValueCallback;
import delight.functional.Closure;

import io.nextweb.promise.Fn;
import io.nextweb.promise.callbacks.EmbeddedCallback;
import io.nextweb.promise.callbacks.NextwebCallback;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import io.nextweb.promise.exceptions.ImpossibleException;
import io.nextweb.promise.exceptions.ImpossibleResult;
import io.nextweb.promise.exceptions.NextwebExceptionManager;
import io.nextweb.promise.exceptions.UnauthorizedException;
import io.nextweb.promise.exceptions.UnauthorizedResult;
import io.nextweb.promise.exceptions.UndefinedException;
import io.nextweb.promise.exceptions.UndefinedResult;

public final class AsyncUtils {

    public static final <T> ValueCallback<T> asValueCallback(final NextwebCallback<T> callback) {
        return new ValueCallback<T>() {

            @Override
            public void onFailure(final Throwable t) {
                callback.onFailure(Fn.exception(this, t));
            }

            @Override
            public void onSuccess(final T value) {
                callback.onSuccess(value);
            }
        };
    }

    public static final <T> ExceptionListener asExceptionListener(final ValueCallback<T> callback) {
        return new ExceptionListener() {

            @Override
            public void onFailure(final ExceptionResult r) {
                callback.onFailure(r.exception());
            }
        };
    }

    public static final <T, C> ValueCallback<T> embed(final NextwebCallback<C> callback, final Closure<T> onSuccess) {
        return AsyncCommon.embed(asValueCallback(callback), onSuccess);
    }

    public static <ResultType> NextwebCallback<ResultType> embed(final NextwebCallback<?> callback,
            final Closure<ResultType> onSuccess) {
        return embeddedCallback(callback.getExceptionManager(), callback, onSuccess);

    }

    @SuppressWarnings("unchecked")
    public static <ResultType> NextwebCallback<ResultType> embeddedCallback(
            final NextwebExceptionManager exceptionManager, final NextwebCallback<ResultType> embeddedIn) {
        return new EmbeddedCallback<ResultType>((NextwebCallback<Object>) embeddedIn, exceptionManager);
    }

    @SuppressWarnings("unchecked")
    public static <ResultType> NextwebCallback<ResultType> embeddedCallback(
            final NextwebExceptionManager exceptionManager, final NextwebCallback<?> embeddedIn,
            final Closure<ResultType> p_onSuccess) {
        return new EmbeddedCallback<ResultType>((NextwebCallback<Object>) embeddedIn, exceptionManager) {

            @Override
            public void onSuccess(final ResultType result) {
                p_onSuccess.apply(result);
            }

        };
    }

    public static <ResultType> NextwebCallback<ResultType> wrap(final ValueCallback<ResultType> callback) {
        return new NextwebCallback<ResultType>() {

            @Override
            public void onFailure(final ExceptionResult r) {
                callback.onFailure(r.exception());
            }

            @Override
            public void onUnauthorized(final UnauthorizedResult r) {
                callback.onFailure(new UnauthorizedException(r));
            }

            @Override
            public void onUndefined(final UndefinedResult r) {

                callback.onFailure(new UndefinedException(r));
            }

            @Override
            public void onImpossible(final ImpossibleResult ir) {
                callback.onFailure(new ImpossibleException(ir));
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
            public NextwebExceptionManager getExceptionManager() {
                return AppjangleGlobal.getEngine().getExceptionManager();
            }

        };
    }

}
