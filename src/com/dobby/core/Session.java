package com.dobby.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Vector;

/**
 * 
 * @author anand
 * 
 */
public class Session implements Requestable {
	private Queue<Request> requestQueue;
	private Map<String, List<Request>> requestLog;
	private StateVector currentState;
	private DocumentModel docMod;
	private String docName;

	/**
	 * Creates a new editing session for a document
	 * 
	 * @param docName
	 */
	public Session(String docName) {
		this.requestQueue = new LinkedList<Request>();
		this.requestLog = new HashMap<String, List<Request>>();
		this.docMod = new DocumentModel();
		this.docName = docName;
		this.currentState = docMod.getRoot();
	}

	/**
	 * Receives a request, and enters it into the Request queue
	 * 
	 * @param r
	 */
	@Override
	public synchronized void receiveRequest(Request r) {
		requestQueue.add(r);
		putRequestInLog(r);
	}

	/**
	 * Executes a request by dequeueing it, translating it, and then applying
	 * the transformations
	 * 
	 * @param r
	 */
	public synchronized void executeRequest() {
		if (!isRequestQueueEmpty()) {
			Request target = requestQueue.remove();
			Request translated = translateRequest(target);
			docMod.applyRequestToText(translated);
			translated.getStateVector().incrementUser(translated.getUser());
			currentState = translated.getStateVector();
		}
	}

	/**
	 * Checks if the requestQueue is empty
	 * 
	 * @return
	 */
	public boolean isRequestQueueEmpty() {
		return requestQueue.isEmpty();
	}

	/**
	 * Provides the number of pending operations on this session
	 * 
	 * @return
	 */
	public int getRequestQueueLength() {
		return requestQueue.size();
	}

	/**
	 * Translates a request into an appropriate space for execution
	 * 
	 * @param target
	 * @return translated request
	 */
	protected Request translateRequest(Request target) {
		// TODO implement translateRequest
		return null;
	}

	public StateVector getCurrentState() {
		return currentState;
	}

	public void setCurrentState(StateVector currentState) {
		this.currentState = currentState;
	}

	/**
	 * Gets the text body of the document
	 * 
	 * @return
	 */
	public String getCurrentText() {
		return docMod.getText();
	}

	/**
	 * Sets the document name
	 * 
	 * @param docName
	 */
	public void setDocName(String docName) {
		this.docName = docName;
	}

	/**
	 * Gets the document name
	 * 
	 * @return
	 */
	public String getDocName() {
		return docName;
	}

	/**
	 * Gets the number of requests recieved from a user
	 * 
	 * @param user
	 * @return
	 */
	public int getUserState(String user) {
		return currentState.getUser(user);
	}

	/**
	 * Gets a request from the log, based on user and the request ordering
	 * 
	 * @param user
	 * @param index
	 * @return
	 */
	public Request getRequest(String user, int index) {
		Request desired = null;
		if (requestLog.containsKey(user)) {
			List<Request> userLog = requestLog.get(user);
			if (userLog.size() > index) {
				desired = userLog.get(index);
			}
		}
		return desired;
	}

	/**
	 * 
	 * @param target
	 * @return
	 */
	public boolean Reachable(Request target) {
		return docMod.containsRequest(target);
	}

	/**
	 * Inserts a request into the log of requests
	 * 
	 * @param r
	 */
	protected void putRequestInLog(Request r) {
		if (requestLog.containsKey(r.user)) {
			List<Request> userLog = requestLog.get(r.user);
			userLog.add(r);
		} else {
			List<Request> userLog = new Vector<Request>();
			userLog.add(r);
			requestLog.put(r.getUser(), userLog);
		}

	}

}
