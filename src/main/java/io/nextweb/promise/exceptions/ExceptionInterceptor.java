package io.nextweb.promise.exceptions;

public interface ExceptionInterceptor<ForType> {

	public ForType catchExceptions(ExceptionListener listener);

}
