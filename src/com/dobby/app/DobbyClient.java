package com.dobby.app;

import java.io.IOException;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import com.dobby.app.comm.BroadcastListener;
import com.dobby.core.Request;
import com.dobby.core.Session;
import com.dobby.core.requests.DeleteRequest;
import com.dobby.core.requests.IdentityRequest;
import com.dobby.core.requests.InsertRequest;
import com.spartango.io.AsyncReadEvent;
import com.spartango.io.AsyncReadListener;
import com.spartango.io.AsyncWriteEvent;
import com.spartango.io.AsyncWriteSender;
import com.spartango.network.AsyncSocket;

/**
 * 
 * @author anand
 * 
 */
public class DobbyClient implements AsyncReadListener, AsyncWriteSender,
		BroadcastListener {
	private DobbyServer server;

	private Session session;
	private AsyncSocket connection;

	private Thread sessionThread;

	public DobbyClient(DobbyServer server, Socket sock, String document,
			String username) throws IOException {
		this(server, new AsyncSocket(sock), document, username);
	}

	public DobbyClient(DobbyServer server, AsyncSocket socket, String document,
			String username) {
		this.server = server;
		connection = socket;
		session = new Session(username, document);
		registerClient();
		syncState();
		startSession();
	}

	private void startSession() {
		sessionThread = new Thread(session);
		sessionThread.start();
	}

	private void syncState() {
		JSONObject payload = new JSONObject();
		try {
			payload.put("op", "sync");
			payload.put("text", session.getCurrentText());
			connection.send(payload.toString(), this);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	private void registerClient() {
		server.registerClient(session.getUserName(), this);
	}

	@Override
	public void onWriteSuccess(AsyncWriteEvent e) { /* Successful send */
	}

	@Override
	public void onWriteFailure(AsyncWriteEvent e) {
		// TODO Decide if a resend is necessary
	}

	@Override
	public void onWriterClosed(AsyncWriteEvent e) { // Disconnected
		// TODO try and reconnect

		// Exit safely
		stop();
	}

	private void stop() {
		server.releaseClient(session.getUserName());
		connection.close();
		session.stop();
	}

	@Override
	public void onDataReceived(AsyncReadEvent e) {
		try {
			JSONObject message = new JSONObject(e.getData());
			if (message.has("op"))
				handleMessage(message);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

	}

	private void handleMessage(JSONObject message) throws JSONException {
		if (message.getString("op").equals("Ins")) {
			InsertRequest request = InsertRequest.fromJSON(message);
			handleRequest(request);
		} else if (message.getString("op").equals("Del")) {
			DeleteRequest request = DeleteRequest.fromJSON(message);
			handleRequest(request);
		} else if (message.getString("op").equals("Id")) {
			IdentityRequest request = IdentityRequest.fromJSON(message);
			handleRequest(request);
		} else if (message.getString("op").equals("sync")) {
			// This is a request for the document text
			syncState();
		} else if (message.getString("op").equals("register")) {
			// This is a broadcast signup
			registerClient();
		}
	}

	private void handleRequest(Request request) {
		// Associate a statevector with this request if it doesnt have one
		// Add this request to the queue
	}

	@Override
	public void onReceiveFailed(AsyncReadEvent e) { }

	@Override
	public void onReaderClosed(AsyncReadEvent e) {
		this.stop();
	}

	private void broadcastRequest(Request r) {
		server.broadcastRequest(r);
	}

	@Override
	public void onBroadcastReceived(Request r) {
		session.receiveRequest(r);
	}

	@Override
	public void onProviderClosed() {
		this.stop();
	}

}
