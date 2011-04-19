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
	 * respect to another request based on transformation rules from CA Ellis
	 * 1989 "Concurrency control in groupware systems." and M Ressel 1996 "An
	 * Integrating, Transformation-Oriented Approach to Concurrency Control and
	 * Undo in Group Editors" if this position is before the target, then no
	 * transform performed else if the target is an insert, shift this right
	 * one, else if the target is a delete, shift this left one.
	 * 
	 * @param r
	 */
	@Override
	public Request transform(Request r) {
		Request desired = null;
		if (position < r.getPosition())
			desired = this.clone();
		else if (r instanceof InsertRequest) {
			desired = new InsertRequest(this.user, this.stateVector,
					this.serialNumber, position + 1, this.character);
		} else if (r instanceof DeleteRequest) {
			desired = new InsertRequest(this.user, this.stateVector,
					this.serialNumber, position - 1, this.character);
		}
		return desired;
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

	public InsertRequest clone() {
		return new InsertRequest(user, stateVector, serialNumber, position,
				character);
	}

}
