package io.nextweb.promise.callbacks;

import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ImpossibleListener;
import io.nextweb.promise.exceptions.UnauthorizedListener;
import io.nextweb.promise.exceptions.UndefinedListener;


public interface Callback<ResultType> extends ExceptionListener,
		UnauthorizedListener, UndefinedListener, ImpossibleListener {

	public void onSuccess(ResultType result);

	public boolean hasEagerFailureListener();

	public boolean hasEagerUndefinedListener();

	public boolean hasEagerUnauthorizedListener();

	public boolean hasEagerImpossibleListener();

}
