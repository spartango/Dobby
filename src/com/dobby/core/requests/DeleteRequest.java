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
		if(position < r.getPosition())
			return this;
		else if(position > r.getPosition())
			return new DeleteRequest(this.user, this.stateVector,
					this.serialNumber, this.position - 1,
					this.character);
		else
			return new IdentityRequest(this.user, this.stateVector, this.serialNumber);
	}

	/**
	 * Applies this delete operation to the desired string
	 * @param target
	 */
	@Override
	public String apply(String target) {
		String firsthalf = target.substring(0, position);
		String secondhalf = target.substring(position+1);
		return firsthalf+secondhalf;
	}
	

}
