package io.nextweb.promise.exceptions;

public interface ImpossibleInterceptor<ReturnType> {

	public ReturnType catchImpossible(ImpossibleListener listener);

}
