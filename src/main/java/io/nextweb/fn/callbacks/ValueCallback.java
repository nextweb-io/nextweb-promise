package io.nextweb.fn.callbacks;

public interface ValueCallback<T> {
	
	/**
	 * Asynchronous operation successfully completed.
	 * @param value
	 */
	public void onSuccess(T value);
	
	public void onFailure(Throwable t);
	
}
