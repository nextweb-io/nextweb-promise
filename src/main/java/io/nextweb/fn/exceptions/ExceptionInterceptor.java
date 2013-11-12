package io.nextweb.fn.exceptions;

public interface ExceptionInterceptor<ForType> {

	public ForType catchExceptions(ExceptionListener listener);

}
