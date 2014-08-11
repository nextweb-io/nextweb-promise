package io.nextweb.promise;

public interface NextwebPromiseFactory<ResultType> {

	public NextwebPromise<ResultType> createResult(Deferred<ResultType> asyncResult);

}
