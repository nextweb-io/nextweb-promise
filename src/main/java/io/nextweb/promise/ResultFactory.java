package io.nextweb.promise;

public interface ResultFactory<ResultType> {

	public NextwebPromise<ResultType> createResult(Deferred<ResultType> asyncResult);

}
