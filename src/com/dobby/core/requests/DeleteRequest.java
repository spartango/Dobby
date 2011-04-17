package com.dobby.core.requests;

import com.dobby.core.Request;
import com.dobby.core.StateVector;

/**
 * A request for the delete of a character at a position
 * This supplies the rules relevant to a transform of this operation
 * @author anand
 * @see Request
 */
public class DeleteRequest extends Request {

	/**
	 * Creates a new DeleteRequest with the supplied state
	 * @param user
	 * @param stateVector
	 * @param serialNumber
	 * @param position
	 * @param character
	 */
	public DeleteRequest(String user, StateVector stateVector,
			int serialNumber, int position, char character) {
		super(user, stateVector, serialNumber, position, character);
	}
	
	/**
	 * Transforms this request with respect to another request based 
	 * on transformation rules:
	 * @param r 
	 */
	@Override
	public Request transform(Request r) {
		// TODO(John) Auto-generated method stub
		return null;
	}

	/**
	 * Applies this delete operation to the desired string
	 * @param target
	 */
	@Override
	public String apply(String target) {
		// TODO(John) Auto-generated method stub
		return null;
	}
	

}
