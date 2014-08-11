package io.nextweb.promise.exceptions;

public interface UnauthorizedInterceptor<ForType> {

	public ForType catchUnauthorized(
			UnauthorizedListener listener);

}
