package com.dobby.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StateVector implements Cloneable {

	private Map<String, Integer> vector;

	/**
	 * Creates a new, empty StateVector
	 */
	public StateVector() {
		this.vector = new HashMap<String, Integer>();
	}

	/**
	 * Clears all users and their states
	 */
	public void clear() {
		vector.clear();
	}

	/**
	 * Checks if a user is present in the statevector
	 * 
	 * @param arg0
	 * @return
	 */
	public boolean containsUser(String arg0) {
		return vector.containsKey(arg0);
	}

	/**
	 * Gets the number of requests for a given user if the user hasnt been seen,
	 * its 0
	 * 
	 * @param arg0
	 * @return
	 */
	public Integer getUser(String arg0) {
		if (vector.containsKey(arg0))
			return vector.get(arg0);
		else
			return 0;
	}

	/**
	 * Checks if the statevector is empty (no users)
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return vector.isEmpty();
	}

	/**
	 * Gets the set of users from this statevector
	 * 
	 * @return
	 */
	public Set<String> getUsers() {
		return vector.keySet();
	}

	/**
	 * Inserts a user along with the number requests recv'd into the statevector
	 * 
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	public Integer put(String arg0, Integer arg1) {
		return vector.put(arg0, arg1);
	}

	/**
	 * Given an existing mapping between users and requests recv'd, copy into
	 * this.
	 * 
	 * @param arg0
	 */
	public void putAll(Map<? extends String, ? extends Integer> arg0) {
		vector.putAll(arg0);
	}

	/**
	 * Remove a user from the statevector
	 * 
	 * @param arg0
	 * @return
	 */
	public Integer remove(String arg0) {
		return vector.remove(arg0);
	}

	/**
	 * Increase the number of requests received from a user by 1
	 * 
	 * @param user
	 */
	public void incrementUser(String user) {
		if (vector.containsKey(user))
			vector.put(user, getUser(user) + 1);
		else
			vector.put(user, 1);
	}

	/**
	 * Decrease the number of requests received from a user by 1
	 * 
	 * @param user
	 */
	public void decrementUser(String user) {
		if (vector.containsKey(user)) {
			Integer current = getUser(user);
			vector.put(user, (current < 1) ? 0 : (current - 1));
		}
	}

	/**
	 * Resets the number of requests from a user to 0
	 * 
	 * @param user
	 */
	public void resetUser(String user) {
		vector.put(user, 0);
	}

	/**
	 * The number of users tracked by this statevector
	 * 
	 * @return
	 */
	public int size() {
		return vector.size();
	}

	/**
	 * Creates a deep copy of this statevector
	 */
	public StateVector clone() {
		StateVector copy = new StateVector();
		for (String s : this.getUsers()) {
			copy.put(s, this.getUser(s));
		}
		return copy;
	}

	/**
	 * Compares a statevector to another
	 * 
	 * @param target
	 * @return
	 */
	public boolean equals(StateVector target) {
		return this.hashCode() == target.hashCode();
	}
}
