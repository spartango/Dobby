package com.dobby.core;

class Node<N,E> {
	private N vertex;
	private E outGoingEdges;
	
	public Node(N vertex, E outGoingEdges) {
		this.vertex = vertex;
		this.outGoingEdges = outGoingEdges;
	}
	public N getVertex() {
		return vertex;
	}
	public void setVertex(N vertex) {
		this.vertex = vertex;
	}
	public E getOutGoingEdges() {
		return outGoingEdges;
	}
	public void setOutGoingEdges(E outGoingEdges) {
		this.outGoingEdges = outGoingEdges;
	}
	
} 

//TODO Add Edge class.  Then declare documentmodel to be a directed graph using the node and edges as above
public class DocumentModel {

	
}
