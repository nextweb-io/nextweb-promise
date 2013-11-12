package io.nextweb.fn.exceptions;

public interface UndefinedInterceptor<ForType> {

	public ForType catchUndefined(UndefinedListener listener);

}
