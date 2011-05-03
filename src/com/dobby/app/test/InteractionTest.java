package com.dobby.app.test;

import java.io.IOException;
import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;

import com.dobby.core.Request;
import com.dobby.core.requests.DeleteRequest;
import com.dobby.core.requests.IdentityRequest;
import com.dobby.core.requests.InsertRequest;
import com.dobby.server.ServerApplication;
import com.spartango.io.AsyncReadEvent;
import com.spartango.io.AsyncReadListener;
import com.spartango.io.AsyncWriteEvent;
import com.spartango.io.AsyncWriteSender;
import com.spartango.network.AsyncSocket;

public class InteractionTest implements AsyncReadListener, AsyncWriteSender {
	public static String host = "127.0.0.1";
	private AsyncSocket socket;

	public InteractionTest() {
		try {
			socket = new AsyncSocket(host, ServerApplication.DEFAULT_PORT);
			socket.addAsyncSocketListener(this);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InteractionTest test = new InteractionTest();
		System.out.println("Starting test " + test + ", Ctl-C to stop");
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void onWriteSuccess(AsyncWriteEvent e) {
		System.out.println("<");
	}

	@Override
	public void onWriteFailure(AsyncWriteEvent e) {
		System.err.println("Write fail");
	}

	@Override
	public void onWriterClosed(AsyncWriteEvent e) {
		System.out.println("Socket closed");
		socket.close();
		System.exit(0);
	}

	@Override
	public void onDataReceived(AsyncReadEvent e) {
		System.out.println(">> " + e.getData());
		try {
			JSONObject object = new JSONObject(e.getData());
			String operation = object.getString("op");
			if (operation != null) {
				Request r = null;
				if (operation.equals("Ins")) {
					r = InsertRequest.fromJSON(object);
				} else if (operation.equals("Del")) {
					r = DeleteRequest.fromJSON(object);
				} else if (operation.equals("Id")) {
					r = IdentityRequest.fromJSON(object);
				} else if (operation.equals("sync")) {
					System.out.println("user "
							+ object.getString("assignedName"));
					
				}
				System.out.println(":| " + r);
			}
		} catch (JSONException e1) {
			System.err.println("Can't parse that!");
		}

	}

	@Override
	public void onReceiveFailed(AsyncReadEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReaderClosed(AsyncReadEvent e) {
		System.out.println("Socket closed");
	}

}
