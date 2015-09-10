package io.nextweb.promise.callbacks;

import delight.functional.Closure;

import io.nextweb.promise.exceptions.DataExceptionManager;

public interface DataCallback<ResultType> extends DataFailureCallback {

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

    public DataExceptionManager getExceptionManager();

    public <R> DataCallback<R> chain(Closure<R> onSuccess);

}
