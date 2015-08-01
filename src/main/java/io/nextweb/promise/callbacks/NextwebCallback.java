package io.nextweb.promise.callbacks;

import io.nextweb.promise.exceptions.NextwebExceptionManager;

public interface NextwebCallback<ResultType> extends NextwebFailureCallback {

    public void onSuccess(ResultType result);

    public boolean hasEagerFailureListener();

    public boolean hasEagerUndefinedListener();

    public boolean hasEagerUnauthorizedListener();

    public boolean hasEagerImpossibleListener();

    /**
     * 
     * @return True if any listener defined.
     */
    public boolean hasEagerListeners();

    public NextwebExceptionManager getExceptionManager();

}
