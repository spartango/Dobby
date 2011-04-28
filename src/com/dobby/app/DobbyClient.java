package com.dobby.app;

import java.io.IOException;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

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
		Runnable {

	private Session session;
	private AsyncSocket connection;

	public DobbyClient(Socket sock, String document, String username)
			throws IOException {
		this(new AsyncSocket(sock), document, username);
	}

	public DobbyClient(AsyncSocket socket, String document, String username) {
		connection = socket;
		session = new Session(username, document);
		registerClient(document);
		syncState(document);
		startSession();
		start();

	}

	private void start() {
		// TODO Auto-generated method stub

	}

	private void startSession() {
		// TODO Auto-generated method stub

	}

	private void syncState(String document) {
		// TODO send document text over the wire
	}

	private void registerClient(String document) {
		// TODO implement addition to server broadcast
	}

	@Override
	public void onWriteSuccess(AsyncWriteEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onWriteFailure(AsyncWriteEvent e) {
		// TODO Decide if a resend is necessary

	}

	@Override
	public void onWriterClosed(AsyncWriteEvent e) {
		// TODO Auto-generated method stub

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
		// TODO separate data by purpose & handle
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
			// TODO sync state
		} else if (message.getString("op").equals("register")) {
			// TODO register
		}
	}

	private void handleRequest(Request request) {
		// Associate a statevector with this request if it doesnt have one
		// Add this request to the queue
	}

	@Override
	public void onReceiveFailed(AsyncReadEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReaderClosed(AsyncReadEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	private void broadcastRequest(Request r) {
		// TODO setup a mechanism for broadcasting events.
	}
}
