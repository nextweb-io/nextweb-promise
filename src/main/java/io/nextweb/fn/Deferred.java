package io.nextweb.fn;

import io.nextweb.fn.callbacks.Callback;



public interface Deferred<ResultType> {

	public void get(Callback<ResultType> callback);

}
