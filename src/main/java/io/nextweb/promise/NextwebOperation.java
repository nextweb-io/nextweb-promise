package io.nextweb.promise;

import io.nextweb.promise.callbacks.Callback;

public interface NextwebOperation<ResultType> {

    public void get(Callback<ResultType> callback);

}
