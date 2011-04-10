package com.dobby.core;

import java.util.Vector;

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
	
	public abstract Request transform(Request r);
	
}
