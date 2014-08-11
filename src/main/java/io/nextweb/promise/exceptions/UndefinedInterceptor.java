package io.nextweb.promise.exceptions;

public interface UndefinedInterceptor<ForType> {

	public ForType catchUndefined(UndefinedListener listener);

}
