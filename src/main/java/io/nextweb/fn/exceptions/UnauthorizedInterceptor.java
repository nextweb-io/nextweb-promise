package io.nextweb.fn.exceptions;

public interface UnauthorizedInterceptor<ForType> {

	public ForType catchUnauthorized(
			UnauthorizedListener listener);

}
