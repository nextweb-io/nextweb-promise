package io.nextweb.fn;

public interface ResultFactory<ResultType> {

	public Result<ResultType> createResult(Deferred<ResultType> asyncResult);

}
