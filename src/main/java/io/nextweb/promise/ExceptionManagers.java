package io.nextweb.promise;

import io.nextweb.promise.callbacks.DataCallback;
import io.nextweb.promise.exceptions.NextwebExceptionManager;

public class ExceptionManagers {

    public static NextwebExceptionManager fromCallback(final DataCallback<?> callback,
            final NextwebExceptionManager parentExceptionManager) {

        final NextwebExceptionManager em = new NextwebExceptionManager(parentExceptionManager);

        if (callback.hasEagerFailureListener()) {
            em.catchExceptions(callback);
        }

        if (callback.hasEagerImpossibleListener()) {
            em.catchImpossible(callback);
        }

        if (callback.hasEagerUnauthorizedListener()) {
            em.catchImpossible(callback);
        }

        if (callback.hasEagerUndefinedListener()) {
            em.catchUndefined(callback);
        }

        return em;

    }

    // public static ExceptionManager chain(final ExceptionManager primary,
    // final ExceptionManager secondary) {
    //
    // }

}
