package io.nextweb.promise;

import delight.async.callbacks.ValueCallback;

import io.nextweb.promise.callbacks.NextwebCallback;

public interface NextwebOperation<ResultType> {

    public void apply(NextwebCallback<ResultType> callback);

    public void apply(ValueCallback<ResultType> callback);

}
