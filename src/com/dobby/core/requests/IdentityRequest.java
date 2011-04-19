package com.dobby.core.requests;

import com.dobby.core.Request;
import com.dobby.core.StateVector;

public class IdentityRequest extends Request {

	/**
	 * Creates a new Identity Request with the supplied state
	 * Note that the position and character fields are populated 
	 * as invalid/nul
	 * @param user
	 * @param stateVector
	 * @param serialNumber
	 */
	public IdentityRequest(String user, StateVector stateVector,
			int serialNumber) {
		super(user, stateVector, serialNumber, -1, (char) (0));
	}

	/**
	 * Transforms this request with respect to another request based on
	 * transformation rules:
	 * 
	 * @param r
	 */
	@Override
	public Request transform(Request r) {
		return this;
	}

	/**
	 * Applies the identity operation to the target string
	 * 
	 * @param target
	 */
	public String apply(String target) {
		return target;
	}

}
