package io.nextweb.promise.utils;

import delight.async.callbacks.ValueCallback;
import delight.functional.Closure;

import com.appjangle.api.Client;
import com.appjangle.api.Entity;
import com.appjangle.api.EntityList;
import com.appjangle.api.LinkListQuery;
import com.appjangle.api.engine.AppjangleGlobal;
import com.appjangle.api.operations.callbacks.EagerCallback;
import com.appjangle.api.operations.callbacks.LazyCallback;

import io.nextweb.promise.callbacks.EmbeddedCallback;
import io.nextweb.promise.callbacks.NextwebCallback;
import io.nextweb.promise.exceptions.ExceptionResult;
import io.nextweb.promise.exceptions.ImpossibleException;
import io.nextweb.promise.exceptions.ImpossibleResult;
import io.nextweb.promise.exceptions.NextwebExceptionManager;
import io.nextweb.promise.exceptions.UnauthorizedException;
import io.nextweb.promise.exceptions.UnauthorizedResult;
import io.nextweb.promise.exceptions.UndefinedException;
import io.nextweb.promise.exceptions.UndefinedResult;

public class CallbackFactory {

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

    public static <ResultType> NextwebCallback<ResultType> lazyCallback(final Entity entity,
            final Closure<ResultType> closure) {
        return lazyCallback(entity.getSession(), entity.getExceptionManager(), closure);
    }

    public static <ResultType> NextwebCallback<ResultType> lazyCallback(final LinkListQuery entity,
            final Closure<ResultType> closure) {
        return lazyCallback(entity.getSession(), entity.getExceptionManager(), closure);
    }

    public static <ResultType> NextwebCallback<ResultType> lazyCallback(final EntityList entity,
            final Closure<ResultType> closure) {

        return lazyCallback(entity.getSession(), entity.getExceptionManager(), closure);

    }

    public static <ResultType> NextwebCallback<ResultType> lazyCallback(final Client session,
            final NextwebExceptionManager exceptionManager, final Closure<ResultType> closure) {

        return new LazyCallback<ResultType>(exceptionManager, session) {

            @Override
            public void onSuccess(final ResultType result) {
                closure.apply(result);
            }
        };

    }

    public static <ResultType> EagerCallback<ResultType> eagerCallback(final Client session,
            final NextwebExceptionManager exceptionManager, final Closure<ResultType> closure) {
        return new EagerCallback<ResultType>(session, exceptionManager, null) {

            @Override
            public void onSuccess(final ResultType result) {
                closure.apply(result);
            }
        };
    }

    /**
     * Call eager op first but immideately call fallback callback.
     * 
     * @param session
     * @param fallbackCallback
     * @param exceptionManager
     * @param closure
     * @return
     */
    public static <ResultType> EagerCallback<ResultType> eagerCallback(final Client session,
            final NextwebCallback<?> fallbackCallback, final NextwebExceptionManager exceptionManager,
            final Closure<ResultType> closure) {
        return new EagerCallback<ResultType>(session, exceptionManager, fallbackCallback) {

            @Override
            public void onSuccess(final ResultType result) {
                closure.apply(result);
            }
        };
    }

}
