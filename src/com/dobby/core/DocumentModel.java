package com.dobby.core;

import java.util.Vector;

import org.jgrapht.DirectedGraph;

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

class Edge<N,E>{
	private N source;
	private N target;
	private E request;
	
	public Edge(N source, N target, E request) {
		this.source = source;
		this.target = target;
		this.request = request;
	}
	public N getSource() {
		return source;
	}
	public void setSource(N source) {
		this.source = source;
	}
	public N getTarget() {
		return target;
	}
	public void setTarget(N target) {
		this.target = target;
	}
	public E getRequest() {
		return request;
	}
	public void setRequest(E request) {
		this.request = request;
	}
}

public class DocumentModel {
	private DirectedGraph<Node<Vector<Integer>, Request>, Edge<Vector<Integer>, Request>> interactionModel;

	public DocumentModel(DirectedGraph<Node<Vector<Integer>,Request>,Edge<Vector<Integer>,Request>> interactionModel) {
		super();
		this.interactionModel = interactionModel;
	}

	public DirectedGraph<Node<Vector<Integer>, Request>, Edge<Vector<Integer>, Request>> getInteractionModel() {
		return interactionModel;
	}

	public void setInteractionModel(
			DirectedGraph<Node<Vector<Integer>, Request>, Edge<Vector<Integer>, Request>> interactionModel) {
		this.interactionModel = interactionModel;
	}
	
}
