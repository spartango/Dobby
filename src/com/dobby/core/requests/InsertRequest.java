package com.dobby.core.requests;

import com.dobby.core.Request;
import com.dobby.core.StateVector;

/**
 * A request for the insert of a character at a position This supplies the rules
 * relevant to a transform of this operation
 * 
 * @author anand
 * @see Request
 */
public class InsertRequest extends Request {

	/**
	 * Creates a new InsertRequest with the supplied state
	 * 
	 * @param user
	 * @param stateVector
	 * @param serialNumber
	 * @param position
	 * @param character
	 */
	public InsertRequest(String user, StateVector stateVector,
			int serialNumber, int position, char character) {
		super(user, stateVector, serialNumber, position, character);
	}

	/**
	 * Note: Differs slightly from the algorithm, as users are strings in our
	 * case. This does not affect the correctness of the algorithm but merely
	 * allows user2 to always insert before user1. Transforms this request with
	 * respect to another request based on transformation rules:
	 * 
	 * @param r
	 */
	@Override
	public Request transform(Request r) {
		if (position < r.getPosition())
			return this;
		else
			return new InsertRequest(this.user, this.stateVector,
					this.serialNumber, position + 1, this.character);
	}

	/**
	 * Applies this insert operation to the target string
	 * 
	 * @param target
	 */
	@Override
	public String apply(String target) {
		return new StringBuffer(target).insert(position, character).toString();
	}

}
