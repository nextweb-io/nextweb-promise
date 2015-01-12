package io.nextweb.promise.callbacks;

import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ImpossibleListener;
import io.nextweb.promise.exceptions.UnauthorizedListener;
import io.nextweb.promise.exceptions.UndefinedListener;

public interface NextwebFailureCallback extends ExceptionListener, UnauthorizedListener, UndefinedListener,
        ImpossibleListener {

}
