package com.dobby.app;

import java.io.IOException;
import java.net.UnknownHostException;

import com.dobby.core.Session;
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

	public DobbyClient(String host, int port, String document, String username) {
		try {
			connection = new AsyncSocket(host, port);
			session = new Session(username, document);
			registerClient(document);
			syncState(document);
			startSession();
			start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void start() {
		// TODO Auto-generated method stub
		
	}

	private void startSession() {
		// TODO Auto-generated method stub

	}

	private void syncState(String document) {
		// TODO implement rapid bring up to date
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
		// TODO parse data to JSON
		// TODO separate data by purpose & handle

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

}
