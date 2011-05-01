package com.dobby.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.dobby.app.comm.BroadcastListener;
import com.dobby.core.Request;
import com.dobby.core.Session;
import com.spartango.network.AsyncServerEvent;
import com.spartango.network.AsyncServerListener;
import com.spartango.network.AsyncServerSocket;

public class DobbyServer implements AsyncServerListener {

	private AsyncServerSocket server;
	private Session serverSession;
	private Map<String, BroadcastListener> clients;

	public DobbyServer(String documentName, int port) {
		try {
			serverSession = new Session("Server", documentName);
			server = new AsyncServerSocket(port);
			clients = new HashMap<String, BroadcastListener>();
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onServerClosed() {
		// Close clients gracefully
		for (BroadcastListener listener : clients.values()) {
			listener.onProviderClosed();
		}
	}

	@Override
	public void onNewClient(AsyncServerEvent e) {
		try {
			DobbyClient newClient = new DobbyClient(this, e.getClient(),
					serverSession.getDocName());
			Session newSession = serverSession.clone();
			newSession.setUserName(e.getClient().getInetAddress().toString());
			newClient.syncState(newSession);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void onServerFailure(AsyncServerEvent e) {
	}

	public synchronized void registerClient(String userName,
			BroadcastListener client) {
		clients.put(userName, client);
	}

	public synchronized void releaseClient(String userName) {
		clients.remove(userName);
	}

	public synchronized void broadcastRequest(Request r) {
		// TODO parallelize sending broadcasts
		for (String listener : clients.keySet()) {
			if (!r.getUser().equals(listener)) {
				clients.get(listener).onBroadcastReceived(r);
			}
		}
	}

}
