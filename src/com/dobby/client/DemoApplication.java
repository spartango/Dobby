package com.dobby.client;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import org.json.JSONException;
import org.json.JSONObject;

import com.dobby.client.ui.TestDisplayPanel;
import com.dobby.core.requests.DeleteRequest;
import com.dobby.core.requests.InsertRequest;
import com.spartango.io.AsyncReadEvent;
import com.spartango.io.AsyncReadListener;
import com.spartango.io.AsyncWriteEvent;
import com.spartango.io.AsyncWriteSender;
import com.spartango.network.AsyncSocket;

public class DemoApplication implements DocumentListener, AsyncReadListener,
		AsyncWriteSender {

	private AsyncSocket connection;
	private TestDisplayPanel panel;
	private JFrame frame;
	private Document document;
	private String username;

	public DemoApplication(String host, int port) {
		try {
			// Make a new socket, and connect it.
			connection = new AsyncSocket(host, port);
			// Register this as listener
			connection.addAsyncSocketListener(this);
			// Make a new panel
			TestDisplayPanel panel = new TestDisplayPanel();
			frame = new JFrame("Dobby");
			init();

			// Get the document and register this as listener
			document = panel.getDocument();
			document.addDocumentListener(this);
			username = null;

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// Chop into pieces
		// Generate event
		// Send event over socket
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// Chop into pieces
		// Generate event
		// Send event over socket
	}

	@Override
	public void onWriteSuccess(AsyncWriteEvent e) {
	}

	@Override
	public void onWriteFailure(AsyncWriteEvent e) {
	}

	@Override
	public void onWriterClosed(AsyncWriteEvent e) {
		close();
	}

	private void close() {
		connection.close();
	}

	@Override
	public void onDataReceived(AsyncReadEvent e) {
		// Parse Request from JSON
		try {
			JSONObject obj = new JSONObject(e.getData());
			if (obj.has("op")) {
				String op = obj.getString("op");
				if (op.equals("sync")) {
					username = obj.getString("assignedName");
					System.out.println("Assigned " + username);
				} // else if()
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		// handle Request
	}

	private void handleInsertRequest(InsertRequest r) {
		// call Document's insert function with our contents
	}

	private void handleDeleteRequest(DeleteRequest r) {
		// call Document's remove function with our contents
	}

	@Override
	public void onReceiveFailed(AsyncReadEvent e) {
	}

	@Override
	public void onReaderClosed(AsyncReadEvent e) {
	}

	public void init() {
		// Make a JFrame to house the panel
		// Add the Panel
		// Set JFrame props
		// Make JFrame Visible
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
