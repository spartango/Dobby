package com.dobby.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


/**
 * 
 * @author anand
 *
 */
public class Session {
	private Queue<Request> requestQueue;
	private List<Request> requestLog;
	private Map<Request, StateVector> dictVectors;
	private DocumentModel docMod;
	private String docName;
	
	/**
	 * Creates a new editing session for a document
	 * @param docName
	 */
	public Session(String docName) {
		this.requestQueue = new LinkedList<Request>();
		this.requestLog = new LinkedList<Request>();
		this.dictVectors = new HashMap<Request, StateVector>();
		this.docMod = new DocumentModel();
		this.docName = docName;
	}

	public Queue<Request> getRequestQueue() {
		return requestQueue;
	}

	public List<Request> getRequestLog() {
		return requestLog;
	}
	
	public Map<Request, StateVector> getDictVectors() {
		return dictVectors;
	}

	public DocumentModel getDocMod() {
		return docMod;
	}
	
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocName() {
		return docName;
	}
	
	
}
