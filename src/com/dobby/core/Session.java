package com.dobby.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


/**
 * 
 * @author anand
 *
 */
public class Session implements Requestable{
	private Queue<Request> requestQueue;
	private List<Request> requestLog;
	private Map<Request, StateVector> currentVectors;
	private DocumentModel docMod;
	private String docName;
	
	/**
	 * Creates a new editing session for a document
	 * @param docName
	 */
	public Session(String docName) {
		this.requestQueue = new LinkedList<Request>();
		this.requestLog = new LinkedList<Request>();
		this.currentVectors = new HashMap<Request, StateVector>();
		this.docMod = new DocumentModel();
		this.docName = docName;
	}

	/**
	 * Receives a request, and enters it into the Request queue
	 * @param r
	 */
	@Override
	public synchronized void receiveRequest(Request r){
		requestQueue.add(r);
	}
	
	/**
	 * 
	 * @param r
	 */
	public synchronized void executeRequest(Request r){
		//TODO implement executing requests
	}

	
	
	public boolean isRequestQueueEmpty() {
		return requestQueue.isEmpty();
	}

	public Iterator<Request> requestLogIterator() {
		return requestLog.iterator();
	}

	/**
	 * Gets a particular request from the log by serial number
	 * returns null if none is found
	 * @param serialNo
	 * @return request
	 */
	public Request getRequestForSerialNumber(int serialNo){
		for(Request r: requestLog){
			if(r.getSerialNumber() == serialNo)
				return r;
		}
		return null;
	}
	
	/**
	 * Translates a request into an appropriate space for execution
	 * @param target
	 * @return translated request
	 */
	protected Request translateRequest(Request target){
		//TODO implement translateRequest
		return null;
	}
	
	public StateVector getStateForUser(String user){
		return currentVectors.get(user);
	}
	
	public String getCurrentText() {
		return docMod.getCurrentText();
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}
	
	public String getDocName() {
		return docName;
	}
	
	public boolean Reachable(Request target){
		//TODO find if somethings reachable
		return false;
	}
	
	
	
}
