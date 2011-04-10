package com.dobby.core.requests;

import java.util.Vector;

import com.dobby.core.Request;

public class DeleteRequest extends Request {

	public DeleteRequest(String user, Vector<Integer> stateVector,
			int serialNumber, int position, char character) {
		super(user, stateVector, serialNumber, position, character);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Request transform(Request r) {
		// TODO Auto-generated method stub
		return null;
	}

}
