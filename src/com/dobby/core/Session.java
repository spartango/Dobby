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
	 * Translates a request into an appropriate space for execution.
	 * 
	 * @param target
	 * @return translated request
	 */
	protected Request translateRequest(Request target) {
		if (target.getStateVector().equals(currentState)) {
			docMod.addRequest(currentState, target.getStateVector(), target);
			return target;
			// TODO Possibly implement else if
		} else {
			// note that previous state is guaranteed by the Ressel paper
			// to return a user
			String userToDec = previousState(target);
			StateVector decVec = currentState.decrementedUser(userToDec);
			Request previousRequest = getRequest(userToDec,
					decVec.getUser(userToDec));
			Request translatedPrevReq = translateRequest(previousRequest,
					decVec);
			Request translatedThisReq = translateRequest(target, decVec);
			Request transformedPrevReq = translatedPrevReq
					.transform(translatedThisReq);
			Request transformedThisReq = translatedPrevReq
					.transform(translatedPrevReq);
			docMod.addRequest(currentState,
					transformedPrevReq.getStateVector(), transformedPrevReq);
			docMod.addRequest(currentState,
					transformedThisReq.getStateVector(), transformedThisReq);
			return transformedThisReq;
		}
	}

	/**
	 * Recursive call to translate a request into an appropriate space for
	 * execution.
	 * 
	 * @param target
	 *            , state
	 * @return translated request
	 */
	protected Request translateRequest(Request target, StateVector state) {
		if (target.getStateVector().equals(state)) {
			docMod.addRequest(state, target.getStateVector(), target);
			return target;
		} else {
			// note that previous state is guaranteed by the Ressel paper
			// to return a user
			String userToDec = previousState(target);
			StateVector decVec = state.decrementedUser(userToDec);
			Request previousRequest = getRequest(userToDec,
					decVec.getUser(userToDec));
			Request translatedPrevReq = translateRequest(previousRequest,
					decVec);
			Request translatedThisReq = translateRequest(target, decVec);
			Request transformedPrevReq = translatedPrevReq
					.transform(translatedThisReq);
			Request transformedThisReq = translatedPrevReq
					.transform(translatedPrevReq);
			docMod.addRequest(state, transformedPrevReq.getStateVector(),
					transformedPrevReq);
			docMod.addRequest(state, transformedThisReq.getStateVector(),
					transformedThisReq);
			return transformedThisReq;

		}
	}

	/**
	 * Helper function to get the first previous state such that the translated
	 * request is reachable. The User which we should decrement to achieve this
	 * state is returned.
	 * 
	 * @param target
	 * @return
	 */
	private String previousState(Request target) {
		for (String user : currentState.getUsers()) {
			if (Reachable(currentState.decrementedUser(user))
					&& target.getStateVector().getUser(user) <= currentState
							.getUser(user)) {
				return user;
			}
		}
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
	 * Same purpose as the function above, however this checks to see if a given
	 * StateVector is in the interaction model. This makes it easier to
	 * implement translate request
	 * 
	 * Note: this is overloaded, but there might not be a reason to keep the
	 * previous function around so possibly delete the Request version after
	 * implementation of translate request.
	 */
	public boolean Reachable(StateVector target) {
		return docMod.containsState(target);
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
