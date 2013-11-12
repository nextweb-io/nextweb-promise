package io.nextweb.fn;

import io.nextweb.fn.exceptions.AllInterceptor;



public interface Result<ReturnType> extends BasicResult<ReturnType>,
		AllInterceptor<Result<ReturnType>> {

}
