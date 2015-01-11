package io.nextweb.promise;

import io.nextweb.promise.callbacks.Callback;

public interface Deferred<ResultType> {

    public void get(Callback<ResultType> callback);

}
