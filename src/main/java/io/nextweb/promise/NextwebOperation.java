package io.nextweb.promise;

import io.nextweb.promise.callbacks.NextwebCallback;

public interface NextwebOperation<ResultType> {

    public void apply(NextwebCallback<ResultType> callback);

}
