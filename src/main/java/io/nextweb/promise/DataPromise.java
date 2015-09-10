package io.nextweb.promise;

import io.nextweb.promise.exceptions.AllInterceptor;

public interface DataPromise<ReturnType>
        extends BasicPromise<ReturnType>, AllInterceptor<DataPromise<ReturnType>> {

}
