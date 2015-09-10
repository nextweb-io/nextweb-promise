package io.nextweb.promise;

import java.util.List;

public class Promises {

    public <T> DataPromise<T> sequential(final List<DataOperation<T>> operations) {
        
        
        DataPromise<T> chain = null;
        
        for (final DataOperation<T> op: operations) {
            if (chain == null) {
                chain = op
            }
            
        }
        
    }

}
