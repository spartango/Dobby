package com.dobby.core;

public interface Requestable {
	public void receiveRequest(Request r);

	public void executeRequest();
}
