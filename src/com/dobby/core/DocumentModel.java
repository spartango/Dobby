package com.dobby.core;

import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;

/**
 * 
 * @author anand
 *
 */
public class DocumentModel {
	private DirectedGraph<StateVector, Request> interactionModel;
	private StateVector root;
	//Should probably have caches of text state so we dont have to recalculate.
	
	/**
	 * Creates a new document model, initializing the DirectedGraph internally
	 * Starts with a single vertex
	 */
	public DocumentModel() {
		this.interactionModel = new DefaultDirectedGraph<StateVector, Request>(
				Request.class);
		root = new StateVector(); 
		interactionModel.addVertex(root);
	}
	
	public Request getRequestForSerialNumber(int serialNo){
		//TODO get a request from the graph
		return null;
	}

	public Set<Request> incomingRequestsOf(StateVector vertex) {
		return interactionModel.incomingEdgesOf(vertex);
	}

	public Set<Request> outgoingRequestsOf(StateVector vertex) {
		return interactionModel.outgoingEdgesOf(vertex);
	}

	public boolean addRequest(StateVector sourceVertex, StateVector targetVertex,
			Request e) {
		return interactionModel.addEdge(sourceVertex, targetVertex, e);
	}

	public boolean addState(StateVector v) {
		return interactionModel.addVertex(v);
	}

	public boolean containsRequest(StateVector sourceVertex,
			StateVector targetVertex) {
		return interactionModel.containsEdge(sourceVertex, targetVertex);
	}

	public boolean containsRequest(Request e) {
		return interactionModel.containsEdge(e);
	}

	public boolean containsState(StateVector v) {
		return interactionModel.containsVertex(v);
	}

	public Request removeRequest(StateVector sourceVertex, StateVector targetVertex) {
		return interactionModel.removeEdge(sourceVertex, targetVertex);
	}

	public boolean removeRequest(Request e) {
		return interactionModel.removeEdge(e);
	}

	public boolean removeState(StateVector v) {
		return interactionModel.removeVertex(v);
	}

	public String getTextAtState(StateVector v){
		//TODO accumulate text upto state
		//TODO cache this.
		return null;
	}
	
	public String getTextAtState(Request r){
		//TODO accumulate text upto state
		//TODO cache this.
		return null;
	}

	public StateVector getRoot() {
		return root;
	}
	
	
	
	// TODO implement operations on the document model (addition of state,
	// requests)
	// TODO implement operations from document model (current state, etc)
}
