package io.nextweb.promise;

import io.nextweb.promise.exceptions.AllInterceptor;

/**
 * 
 * @author <a href="http://www.mxro.de">Max Rohde</a>
 *
 * @param <ReturnType>
 */
public interface DataPromise<ReturnType> extends BasicPromise<ReturnType>, AllInterceptor<DataPromise<ReturnType>> {

}
