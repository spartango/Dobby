package com.dobby.core;

import java.util.Vector;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;

public class DocumentModel {
	private DirectedGraph<Vector<Integer>, Request> interactionModel;

	/**
	 * Creates a new document model, initializing the DirectedGraph internally
	 */
	public DocumentModel(){
		this.interactionModel = 
			new DefaultDirectedGraph<Vector<Integer>, Request>(Request.class);
	}
	
	//TODO implement operations on the document model (addition of state, requests)
	//TODO implement operations from document model (current state, etc)
}
