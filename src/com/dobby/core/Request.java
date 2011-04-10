package com.dobby.core;

import java.util.Vector;

/**
 * A representation of a request for a change, complete with the state of the 
 * system when the request is made
 * @author anand
 */
public abstract class Request {
	protected String user;
	protected Vector<Integer> stateVector;
	protected int serialNumber;
	protected int position;
	protected char character;
	
	/**
	 * @param user
	 * @param stateVector
	 * @param serialNumber
	 * @param position
	 * @param character
	 */
	public Request(String user, Vector<Integer> stateVector, int serialNumber,
			int position, char character) {
		this.user = user;
		this.stateVector = stateVector;
		this.serialNumber = serialNumber;
		this.position = position;
		this.character = character;
	}

	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	public Vector<Integer> getStateVector() {
		return stateVector;
	}
	public void setStateVector(Vector<Integer> stateVector) {
		this.stateVector = stateVector;
	}
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public char getCharacter() {
		return character;
	}
	public void setCharacter(char character) {
		this.character = character;
	}
	
	/**
	 * Transforms this request relative to a competing request
	 * @param r
	 * @return new Request, this transformed with respect to r
	 */
	public abstract Request transform(Request r);
	
	/**
	 * Applies this change to the target string
	 * @param target
	 * @return the modified target
	 */
	public abstract String apply(String target);
}
