package io.nextweb.promise.utils;

import delight.async.AsyncCommon;
import delight.async.callbacks.ValueCallback;
import delight.functional.Closure;

import io.nextweb.promise.Fn;
import io.nextweb.promise.callbacks.NextwebCallback;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;

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

}
