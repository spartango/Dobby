package com.dobby.core.requests;

import com.dobby.core.Request;
import com.dobby.core.StateVector;

/**
 * A request for the delete of a character at a position This supplies the rules
 * relevant to a transform of this operation
 * 
 * @author anand
 * @see Request
 */
public class DeleteRequest extends Request {

	private static final int SEED = 17;
	private static final int START_SEED = 31;

	/**
	 * Creates a new DeleteRequest with the supplied state
	 * 
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
	 * Transforms this request with respect to another request based on
	 * transformation rules from CA Ellis 1989 "Concurrency control in groupware
	 * systems." if position is before the target, then no transform needed
	 * else. if target is a delete, return the identity if at same pos, or a
	 * left-shifted copy of this if this follows the target. if target is an
	 * insert, then always shift this right by one
	 * 
	 * @param r
	 */
	@Override
	public Request transform(Request r) {
		Request desired = null;
		if (position < r.getPosition()) {
			desired = this.clone();
		} else if (r instanceof DeleteRequest) {
			if (position > r.getPosition())
				desired = new DeleteRequest(this.user, this.stateVector,
						this.serialNumber, this.position - 1, this.character);
			else if (position == r.getPosition())
				desired = new IdentityRequest(this.user, this.stateVector,
						this.serialNumber);
		} else if (r instanceof InsertRequest) {
			desired = new DeleteRequest(this.user, this.stateVector,
					this.serialNumber, this.position + 1, this.character);
		} else if (r instanceof IdentityRequest) {
			desired = this.clone();
		}
		return desired;
	}

	/**
	 * Applies this delete operation to the desired string if we're outside of
	 * the target bounds, return the target, unmodified
	 * 
	 * @param target
	 */
	@Override
	public String apply(String target) {
		if (position < target.length()) {
			String firsthalf = target.substring(0, position);
			String secondhalf = target.substring(position + 1);
			return firsthalf + secondhalf;
		} else
			return target;
	}

	public DeleteRequest clone() {
		return new DeleteRequest(user, stateVector, serialNumber, position,
				character);
	}
	
	public int hashCode(){
		int hash = START_SEED;
		hash = hash * SEED + this.user.hashCode();
		hash = hash * SEED + this.serialNumber;
		hash = hash * SEED + this.stateVector.hashCode();
		hash = hash * SEED + this.character;
		hash = hash * SEED + this.position;
		return hash;
	}
}
