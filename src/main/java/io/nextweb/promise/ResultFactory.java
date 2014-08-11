package io.nextweb.promise;

public interface ResultFactory<ResultType> {

	public Result<ResultType> createResult(Deferred<ResultType> asyncResult);

}
