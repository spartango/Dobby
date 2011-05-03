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

	protected void populateJSON(JSONObject obj) {
		try {
			obj.put("op", "Id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static IdentityRequest fromJSON(JSONObject obj) throws JSONException {
		IdentityRequest request = null;

		String userName = null;
		StateVector state = null;
		int serial = -1;

		if (obj.has("username")) {
			userName = obj.getString("username");
		}
		if (obj.has("serial")) {
			serial = obj.getInt("serial");
		}
		if (obj.has("state")) {
			state = StateVector.fromJSON(obj.getJSONObject("state"));
		}

		request = new IdentityRequest(userName, state, serial);
		return request;
	}

}
