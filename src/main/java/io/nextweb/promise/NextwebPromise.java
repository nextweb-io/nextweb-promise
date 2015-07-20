package io.nextweb.promise;

import delight.promise.Promise;

import io.nextweb.promise.exceptions.AllInterceptor;

public interface NextwebPromise<ReturnType>
        extends BasicPromise<ReturnType>, AllInterceptor<NextwebPromise<ReturnType>> {

    public Promise<ReturnType> getOriginalPromise();

}
