package com.dobby.client;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.json.JSONException;
import org.json.JSONObject;

import com.dobby.client.ui.TestDisplayPanel;
import com.dobby.core.requests.DeleteRequest;
import com.dobby.core.requests.InsertRequest;
import com.dobby.server.ServerApplication;
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
			// Make a new panel
			panel = new TestDisplayPanel();
			frame = new JFrame("Dobby");
			connection = new AsyncSocket(host, port);
			// Register this as listener
			connection.addAsyncSocketListener(this);
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
		int startPos = e.getOffset();
		int items = e.getLength();
		for (int i = 0; i < items; i++) {
			try {
				char text = document.getText(startPos + i, 1).charAt(0);
				InsertRequest request = new InsertRequest(username, null,
						document.hashCode(), startPos + i, text);
				sendInsertRequest(request);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}

		}
	}

	private void sendInsertRequest(InsertRequest request) {
		connection.send(request.toLightJSON().toString(), this);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		int startPos = e.getOffset();
		int items = e.getLength();
		for (int i = 0; i < items; i++) {
			char text = (char) 0;
			DeleteRequest request = new DeleteRequest(username, null,
					document.hashCode(), startPos + i, text);
			sendDeleteRequest(request);
		}
	}

	private void sendDeleteRequest(DeleteRequest request) {
		connection.send(request.toLightJSON().toString(), this);
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
					frame.setTitle("Dobby: " + username);
					// Set document text
					String text = obj.getString("text");
					panel.getjEditorPane().setText(text);
				} else if (op.equals("Ins")) {
					InsertRequest request = InsertRequest.fromJSON(obj);
					handleInsertRequest(request);
				} else if (op.equals("Del")) {
					DeleteRequest request = DeleteRequest.fromJSON(obj);
					handleDeleteRequest(request);
				}
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		// handle Request
	}

	private void handleInsertRequest(InsertRequest r) {
		// call Document's insert function with our contents
		try {
			document.insertString(r.getPosition(), r.getCharacter() + "", null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private void handleDeleteRequest(DeleteRequest r) {
		// call Document's remove function with our contents
		try {
			document.remove(r.getPosition(), 1);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onReceiveFailed(AsyncReadEvent e) {
	}

	@Override
	public void onReaderClosed(AsyncReadEvent e) {
	}

	public void init() {
		// Make a JFrame to house the panel
		frame.add(panel);
		frame.setSize(500, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Add the Panel
		// Set JFrame props
		// Make JFrame Visible
		frame.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("apple.awt.brushMetalLook", "true");
		DemoApplication demo = new DemoApplication("127.0.0.1",
				ServerApplication.DEFAULT_PORT);
		demo.init();
	}

}
