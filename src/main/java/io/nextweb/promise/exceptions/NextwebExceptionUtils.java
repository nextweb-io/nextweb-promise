package io.nextweb.promise.exceptions;

import io.nextweb.promise.callbacks.NextwebFailureCallback;

public class NextwebExceptionUtils {

    public static <T> boolean nextwebCallbackRequired(final Throwable o, final NextwebFailureCallback callback) {
        if (o instanceof ImpossibleException) {
            final ImpossibleException ie = (ImpossibleException) o;
            callback.onImpossible(ie.getResult());
            return true;
        }

        if (o instanceof UndefinedException) {
            final UndefinedException ue = (UndefinedException) o;
            callback.onUndefined(ue.getResult());
            return true;
        }

        if (o instanceof UnauthorizedException) {
            final UnauthorizedException ue = (UnauthorizedException) o;
            callback.onUnauthorized(ue.getResult());
            return true;

        }

        return false;
    }

    public static <T> boolean nextwebCallbackRequired(final ExceptionResult er, final NextwebFailureCallback callback) {

        final Throwable o = er.exception();

        return nextwebCallbackRequired(o, callback);

    }

}
