package com.dobby.core.requests;

import org.json.JSONException;
import org.json.JSONObject;

import com.dobby.core.Request;
import com.dobby.core.StateVector;

public class IdentityRequest extends Request {

	private static final int SEED = 31;
	private static final int START_SEED = 23;
	/**
	 * Creates a new Identity Request with the supplied state Note that the
	 * position and character fields are populated as invalid/nul
	 * 
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

	public IdentityRequest clone() {
		return new IdentityRequest(user, stateVector, serialNumber);
	}

	public int hashCode() {
		int hash = START_SEED;
		hash = hash * SEED + this.user.hashCode();
		hash = hash * SEED + this.serialNumber;
		hash = hash * SEED + this.stateVector.hashCode();
		return hash;
	}
	
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("op", "Id");
			obj.put("user", user);
			obj.put("serial", serialNumber);
			obj.put("state", stateVector.toJSON());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
