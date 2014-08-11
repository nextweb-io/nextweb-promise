package io.nextweb.promise;

import io.nextweb.promise.exceptions.AllInterceptor;



public interface Result<ReturnType> extends BasicResult<ReturnType>,
		AllInterceptor<Result<ReturnType>> {

}
