package com.dobby.app.comm;

import com.dobby.core.Request;
import com.dobby.core.Session;

public interface BroadcastListener {
	public void syncState(Session newSession);

	public void onBroadcastReceived(Request r);

	public void onProviderClosed();
}
