package io.nextweb.promise.exceptions;

import io.nextweb.promise.Fn;
import io.nextweb.promise.callbacks.DataFailureCallback;

public class DataExceptionManager
        implements ExceptionInterceptor<DataExceptionManager>, UnauthorizedInterceptor<DataExceptionManager>,
        ImpossibleInterceptor<DataExceptionManager>, UndefinedInterceptor<DataExceptionManager>, DataFailureCallback {

    public static DataExceptionManager fallbackExceptionManager;

    private UnauthorizedListener authExceptionListener;
    private ExceptionListener exceptionListener;
    private UndefinedListener undefinedExceptionListener;
    private ImpossibleListener impossibleListener;

    private final DataExceptionManager parentExceptionManager;

    @Override
    public DataExceptionManager catchUnauthorized(final UnauthorizedListener authExceptionListener) {
        this.authExceptionListener = authExceptionListener;
        return this;
    }

    @Override
    public DataExceptionManager catchExceptions(final ExceptionListener exceptionListener) {
        this.exceptionListener = exceptionListener;
        return this;
    }

    public boolean canCatchExceptions() {
        return this.exceptionListener != null
                || (this.parentExceptionManager != null && this.parentExceptionManager.canCatchExceptions());

    }

    public boolean canCatchUndefinedExceptions() {
        return this.undefinedExceptionListener != null || canCatchExceptions()
                || (this.parentExceptionManager != null && this.parentExceptionManager.canCatchUndefinedExceptions());

    }

    public boolean canCatchAuthorizationExceptions() {
        return this.authExceptionListener != null || canCatchExceptions() || (this.parentExceptionManager != null
                && this.parentExceptionManager.canCatchAuthorizationExceptions());

    }

    public boolean canCatchImpossibe() {
        return this.impossibleListener != null || canCatchExceptions()
                || (this.parentExceptionManager != null && this.parentExceptionManager.canCatchImpossibe());
    }

    @Override
    public void onFailure(final ExceptionResult r) {

        if (NextwebExceptionUtils.nextwebCallbackRequired(r, this)) {
            return;
        }

        if (this.exceptionListener != null) {
            this.exceptionListener.onFailure(r);
            return;
        }

        if (this.parentExceptionManager != null) {
            if (this.parentExceptionManager.canCatchExceptions()) {
                this.parentExceptionManager.onFailure(r);
                return;
            }
        }
        fallbackExceptionManager.onFailure(r);

    }

    @Override
    public void onUnauthorized(final UnauthorizedResult r) {
        System.out.println("HERE " + r + " " + this.authExceptionListener);
        // assert canCatchAuthorizationExceptions() || canCatchExceptions();

        if (this.authExceptionListener != null) {
            this.authExceptionListener.onUnauthorized(r);
            return;
        }

        System.out.println("HERE2 " + this.exceptionListener);

        if (this.exceptionListener != null) {
            this.exceptionListener
                    .onFailure(Fn.exception(r.origin(), new Exception("Unauthorized: " + r.getMessage())));
            return;
        }

        if (this.parentExceptionManager != null) {
            if (this.parentExceptionManager.canCatchAuthorizationExceptions()) {
                this.parentExceptionManager.onUnauthorized(r);
                return;
            }
        }

        onFailure(Fn.exception(r.origin(), new Exception("Unauthorized: " + r.getMessage())));
    }

    @Override
    public void onImpossible(final ImpossibleResult ir) {
        // assert canCatchImpossibe() || canCatchExceptions();

        if (this.impossibleListener != null) {
            this.impossibleListener.onImpossible(ir);
            return;
        }

        if (this.exceptionListener != null) {
            this.exceptionListener.onFailure(
                    Fn.exception(ir.origin(), new Exception("Operation impossible: [" + ir.message() + "]")));
            return;
        }

        if (this.parentExceptionManager != null) {
            if (this.parentExceptionManager.canCatchImpossibe()) {
                this.parentExceptionManager.onImpossible(ir);
                return;
            }
        }

        onFailure(Fn.exception(ir.origin(), new Exception("Operation impossible: [" + ir.message() + "]")));
    }

    @Override
    public DataExceptionManager catchImpossible(final ImpossibleListener listener) {

        this.impossibleListener = listener;
        return this;
    }

    @Override
    public DataExceptionManager catchUndefined(final UndefinedListener undefinedExceptionListener) {
        this.undefinedExceptionListener = undefinedExceptionListener;
        return this;
    }

    @Override
    public void onUndefined(final UndefinedResult r) {
        // assert canCatchUndefinedExceptions() || canCatchExceptions();

        if (this.undefinedExceptionListener != null) {
            this.undefinedExceptionListener.onUndefined(r);
            return;
        }

        if (this.exceptionListener != null) {
            this.exceptionListener.onFailure(Fn.exception(r.origin(), new Exception("Undefined: " + r.message())));
            return;
        }

        if (this.parentExceptionManager != null) {
            if (this.parentExceptionManager.canCatchUndefinedExceptions()) {
                this.parentExceptionManager.onUndefined(r);
                return;
            }
        }

        onFailure(Fn.exception(r.origin(), new Exception("Undefined: " + r.message())));
    }

    public DataExceptionManager(final DataExceptionManager parentExceptionManager) {
        super();
        this.parentExceptionManager = parentExceptionManager;

    }

}
