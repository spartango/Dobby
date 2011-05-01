package com.dobby.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.dobby.app.comm.BroadcastListener;
import com.dobby.core.Request;
import com.spartango.network.AsyncServerEvent;
import com.spartango.network.AsyncServerListener;
import com.spartango.network.AsyncServerSocket;

public class DobbyServer implements AsyncServerListener {
	
	private AsyncServerSocket server;
	private Map<String, BroadcastListener> clients;
	
	
	public DobbyServer(int port){
		try {
			server = new AsyncServerSocket(port);
			clients = new HashMap<String, BroadcastListener>();
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onServerClosed() {
		//Close clients gracefully
		for(BroadcastListener listener : clients.values()){
			listener.onProviderClosed();
		}
	}

	@Override
	public void onNewClient(AsyncServerEvent e) {
		
	}

	@Override
	public void onServerFailure(AsyncServerEvent e) { }

	public synchronized void registerClient(String userName, BroadcastListener client) {
		
	}
	
	public synchronized void releaseClient(String userName){
		
	}
	
	public synchronized void broadcastRequest(Request r){
		
	}
	

}
