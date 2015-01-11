package io.nextweb.promise;

import io.nextweb.promise.callbacks.Callback;

public interface Deferred<ResultType> {

    public void apply(Callback<ResultType> callback);

}
