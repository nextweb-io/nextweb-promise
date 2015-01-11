package io.nextweb.promise.callbacks;

import io.nextweb.promise.exceptions.NextwebExceptionManager;
import io.nextweb.promise.exceptions.ExceptionResult;
import io.nextweb.promise.exceptions.ImpossibleResult;
import io.nextweb.promise.exceptions.UnauthorizedResult;
import io.nextweb.promise.exceptions.UndefinedResult;

public class EmbeddedCallback<ResultType> implements Callback<ResultType> {

	private final Callback<Object> embeddedIn;
	private final NextwebExceptionManager exceptionManager;

	@Override
	public void onUndefined(UndefinedResult r) {

		if (hasEagerUndefinedListener()) {
			embeddedIn.onUndefined(r);
			return;
		}

		if (exceptionManager.canCatchUndefinedExceptions()) {
			exceptionManager.onUndefined(r);
			return;
		}

		embeddedIn.onUndefined(r);

	}

	@Override
	public void onFailure(ExceptionResult r) {
		if (hasEagerFailureListener()) {
			embeddedIn.onFailure(r);
			return;
		}

		if (exceptionManager.canCatchExceptions()) {
			exceptionManager.onFailure(r);
			return;
		}

		embeddedIn.onFailure(r);
	}

	@Override
	public void onImpossible(ImpossibleResult ir) {
		if (hasEagerImpossibleListener()) {
			embeddedIn.onImpossible(ir);
			return;
		}

		if (exceptionManager.canCatchImpossibe()) {
			exceptionManager.onImpossible(ir);
			return;
		}

		embeddedIn.onImpossible(ir);
	}

	@Override
	public void onUnauthorized(UnauthorizedResult r) {
		if (hasEagerUnauthorizedListener()) {
			embeddedIn.onUnauthorized(r);
			return;
		}

		if (exceptionManager.canCatchAuthorizationExceptions()) {
			exceptionManager.onUnauthorized(r);
			return;
		}

		embeddedIn.onUnauthorized(r);
	}

	@Override
	public void onSuccess(ResultType result) {
		embeddedIn.onSuccess(result);
	}

	@Override
	public boolean hasEagerImpossibleListener() {

		return embeddedIn.hasEagerImpossibleListener();
	}

	@Override
	public boolean hasEagerFailureListener() {
		return embeddedIn.hasEagerFailureListener();
	}

	@Override
	public boolean hasEagerUndefinedListener() {
		return embeddedIn.hasEagerUndefinedListener();
	}

	@Override
	public boolean hasEagerUnauthorizedListener() {
		return embeddedIn.hasEagerUnauthorizedListener();
	}

	public EmbeddedCallback(Callback<Object> embeddedIn,
			NextwebExceptionManager exceptionManager) {
		super();
		this.embeddedIn = embeddedIn;
		this.exceptionManager = exceptionManager;
	}

}
