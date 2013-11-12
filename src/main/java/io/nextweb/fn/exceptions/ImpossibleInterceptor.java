package io.nextweb.fn.exceptions;

public interface ImpossibleInterceptor<ReturnType> {

	public ReturnType catchImpossible(ImpossibleListener listener);

}
