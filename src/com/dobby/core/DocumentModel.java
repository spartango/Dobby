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

	public Set<Request> incomingRequestsOf(StateVector vertex) {
		return interactionModel.incomingEdgesOf(vertex);
	}

	public Set<Request> outgoingRequestsOf(StateVector vertex) {
		return interactionModel.outgoingEdgesOf(vertex);
	}

	public boolean addRequest(StateVector sourceVertex,
			StateVector targetVertex, Request e) {
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
