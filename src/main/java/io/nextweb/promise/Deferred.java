package io.nextweb.promise;

import io.nextweb.promise.callbacks.Callback;
import de.mxro.async.Operation;

public interface Deferred<ResultType> extends Operation<ResultType> {

    public void get(Callback<ResultType> callback);

}
