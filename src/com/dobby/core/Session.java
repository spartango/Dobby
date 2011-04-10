package com.dobby.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Vector;



public class Session {
	private Queue<Request> requestQueue;
	private List<Request> requestLog;
	private Map<Request, Vector<Integer>> dictVectors;
	private DocumentModel docMod;
	private String docName;
	
	
	public Session(String docName) {
		this.requestQueue = new LinkedList<Request>();
		this.requestLog = new LinkedList<Request>();
		this.dictVectors = new HashMap<Request, Vector<Integer>>();
		this.docMod = null; //TODO add constructor once we get DocumentModel up
		this.docName = docName;
	}
	public void setRequestQueue(Queue<Request> requestQueue) {
		this.requestQueue = requestQueue;
	}
	public Queue<Request> getRequestQueue() {
		return requestQueue;
	}
	public void setRequestLog(List<Request> requestLog) {
		this.requestLog = requestLog;
	}
	public List<Request> getRequestLog() {
		return requestLog;
	}
	public void setDictVectors(Map<Request, Vector<Integer>> dictVectors) {
		this.dictVectors = dictVectors;
	}
	public Map<Request, Vector<Integer>> getDictVectors() {
		return dictVectors;
	}
	public void setDocMod(DocumentModel docMod) {
		this.docMod = docMod;
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
