package io.nextweb.fn.callbacks;

import io.nextweb.fn.exceptions.ExceptionListener;
import io.nextweb.fn.exceptions.ImpossibleListener;
import io.nextweb.fn.exceptions.UnauthorizedListener;
import io.nextweb.fn.exceptions.UndefinedListener;


public interface Callback<ResultType> extends ExceptionListener,
		UnauthorizedListener, UndefinedListener, ImpossibleListener {

	public void onSuccess(ResultType result);

	public boolean hasEagerFailureListener();

	public boolean hasEagerUndefinedListener();

	public boolean hasEagerUnauthorizedListener();

	public boolean hasEagerImpossibleListener();

}
