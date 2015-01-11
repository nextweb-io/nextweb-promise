package io.nextweb.promise;

import io.nextweb.promise.exceptions.AllInterceptor;

public interface NextwebPromise<ReturnType> extends BasicPromise<ReturnType>,
        AllInterceptor<NextwebPromise<ReturnType>> {

}
