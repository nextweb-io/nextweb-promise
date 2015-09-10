package io.nextweb.promise;

import io.nextweb.promise.callbacks.DataCallback;

public interface DataOperation<ResultType> {

    public void apply(DataCallback<ResultType> callback);

}
