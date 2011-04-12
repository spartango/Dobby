package com.dobby.core;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;

/**
 * 
 * @author anand
 *
 */
public class DocumentModel {
	private DirectedGraph<StateVector, Request> interactionModel;

	/**
	 * Creates a new document model, initializing the DirectedGraph internally
	 */
	public DocumentModel() {
		this.interactionModel = new DefaultDirectedGraph<StateVector, Request>(
				Request.class);
	}

	public String getCurrentText() {
		// TODO get current text from document
		return null;
	}

	// TODO implement operations on the document model (addition of state,
	// requests)
	// TODO implement operations from document model (current state, etc)
}
