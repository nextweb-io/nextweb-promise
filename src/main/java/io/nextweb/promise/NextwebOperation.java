package io.nextweb.promise;

import io.nextweb.promise.callbacks.Callback;
import de.mxro.async.Operation;

public interface NextwebOperation<ResultType> extends Operation<ResultType> {

    public void apply(Callback<ResultType> callback);

}
