package io.nextweb.promise;

import delight.functional.Closure;

import io.nextweb.promise.exceptions.ExceptionResult;
import io.nextweb.promise.exceptions.UndefinedResult;
import io.nextweb.promise.exceptions.v01.UndefinedResultData;

public final class Fn {

    public static final ExceptionResult exception(final Object origin, final Throwable t) {
        return new ExceptionResult() {

            @Override
            public Object origin() {

                return origin;
            }

            @Override
            public Throwable exception() {

                return t;
            }
        };
    }

    public static final UndefinedResult undefined(final Object origin, final String message) {

        // Log.print(new Exception("Undefined created with message " +
        // message));

        return new UndefinedResultData(message, origin);
    }

    public static <G> Closure<G> doNothing(final Class<G> inputType) {
        return new Closure<G>() {

            @Override
            public void apply(final G o) {
                // nada
            }
        };
    }

}
