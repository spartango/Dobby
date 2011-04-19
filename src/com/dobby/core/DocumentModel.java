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

	private String text;

	/**
	 * Creates a new document model, initializing the DirectedGraph internally
	 * Starts with a single vertex
	 */
	public DocumentModel() {
		this.interactionModel = new DefaultDirectedGraph<StateVector, Request>(
				Request.class);
		root = new StateVector();
		interactionModel.addVertex(root);
		text = "";
	}

	/**
	 * Provides the request(s) that created the target state from the most
	 * immediate predecessor state(s) 
	 * @param vertex
	 * @return
	 */
	public Set<Request> incomingRequestsOf(StateVector vertex) {
		return interactionModel.incomingEdgesOf(vertex);
	}

	/**
	 * Provides the request(s) diverging from this sate
	 * @param vertex
	 * @return
	 */
	public Set<Request> outgoingRequestsOf(StateVector vertex) {
		return interactionModel.outgoingEdgesOf(vertex);
	}

	/**
	 * Adds a request that goes from one state to another
	 * @param sourceVertex
	 * @param targetVertex
	 * @param e
	 * @return
	 */
	public boolean addRequest(StateVector sourceVertex,
			StateVector targetVertex, Request e) {
		return interactionModel.addEdge(sourceVertex, targetVertex, e);
	}

	/**
	 * 
	 * @param sourceVertex
	 * @param targetVertex
	 * @return
	 */
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

	public Request removeRequest(StateVector sourceVertex,
			StateVector targetVertex) {
		return interactionModel.removeEdge(sourceVertex, targetVertex);
	}

	public boolean removeRequest(Request e) {
		return interactionModel.removeEdge(e);
	}

	public boolean removeState(StateVector v) {
		return interactionModel.removeVertex(v);
	}

	public StateVector getRoot() {
		return root;
	}

	public String getText() {
		return text;
	}

	public void applyRequestToText(Request r) {
		text = r.apply(text);
	}

}
