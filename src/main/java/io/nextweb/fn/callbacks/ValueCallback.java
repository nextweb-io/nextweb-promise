package io.nextweb.fn.callbacks;

public interface ValueCallback<T> {

	public void onSuccess(T value);
	
	public void onFailure(Throwable t);
	
}
