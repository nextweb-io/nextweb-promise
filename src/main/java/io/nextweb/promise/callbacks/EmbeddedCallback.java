package io.nextweb.promise.callbacks;

import delight.functional.Closure;

import io.nextweb.promise.exceptions.ExceptionResult;
import io.nextweb.promise.exceptions.ImpossibleResult;
import io.nextweb.promise.exceptions.NextwebExceptionManager;
import io.nextweb.promise.exceptions.NextwebExceptionUtils;
import io.nextweb.promise.exceptions.UnauthorizedResult;
import io.nextweb.promise.exceptions.UndefinedResult;
import io.nextweb.promise.utils.AsyncUtils;

public class EmbeddedCallback<ResultType> implements NextwebCallback<ResultType> {

    private final NextwebCallback<Object> embeddedIn;
    private final NextwebExceptionManager exceptionManager;

    @Override
    public void onUndefined(final UndefinedResult r) {

        // System.out.println("embedded recieved: " + r);

        if (hasEagerUndefinedListener()) {
            embeddedIn.onUndefined(r);

            return;
        }

        if (exceptionManager.canCatchUndefinedExceptions()) {

            exceptionManager.onUndefined(r);
            return;
        }

        embeddedIn.onUndefined(r);

    }

    @Override
    public void onFailure(final ExceptionResult r) {
        if (NextwebExceptionUtils.nextwebCallbackRequired(r, this)) {
            return;
        }

        if (hasEagerFailureListener()) {
            embeddedIn.onFailure(r);
            return;
        }

        if (exceptionManager.canCatchExceptions()) {
            exceptionManager.onFailure(r);
            return;
        }

        embeddedIn.onFailure(r);
    }

    @Override
    public void onImpossible(final ImpossibleResult ir) {
        if (hasEagerImpossibleListener()) {
            embeddedIn.onImpossible(ir);
            return;
        }

        if (exceptionManager.canCatchImpossibe()) {
            exceptionManager.onImpossible(ir);
            return;
        }

        embeddedIn.onImpossible(ir);
    }

    @Override
    public void onUnauthorized(final UnauthorizedResult r) {
        if (hasEagerUnauthorizedListener()) {
            embeddedIn.onUnauthorized(r);
            return;
        }

        if (exceptionManager.canCatchAuthorizationExceptions()) {
            exceptionManager.onUnauthorized(r);
            return;
        }

        embeddedIn.onUnauthorized(r);
    }

    @Override
    public void onSuccess(final ResultType result) {
        embeddedIn.onSuccess(result);
    }

    @Override
    public boolean hasEagerImpossibleListener() {

        return embeddedIn.hasEagerImpossibleListener();
    }

    @Override
    public boolean hasEagerFailureListener() {
        return embeddedIn.hasEagerFailureListener();
    }

    @Override
    public boolean hasEagerUndefinedListener() {
        return embeddedIn.hasEagerUndefinedListener();
    }

    @Override
    public boolean hasEagerUnauthorizedListener() {
        return embeddedIn.hasEagerUnauthorizedListener();
    }

    public EmbeddedCallback(final NextwebCallback<Object> embeddedIn, final NextwebExceptionManager exceptionManager) {
        super();
        this.embeddedIn = embeddedIn;
        this.exceptionManager = exceptionManager;
    }

    @Override
    public boolean hasEagerListeners() {

        return hasEagerFailureListener() || hasEagerImpossibleListener() || hasEagerUnauthorizedListener()
                || hasEagerUndefinedListener();
    }

    @Override
    public NextwebExceptionManager getExceptionManager() {

        return exceptionManager;
    }

    @Override
    public NextwebCallback<ResultType> chain(final Closure<ResultType> onSuccess) {

        return AsyncUtils.embeddedCallback(exceptionManager, this, onSuccess);
    }

}
