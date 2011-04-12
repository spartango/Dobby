package com.dobby.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StateVector implements Cloneable{

	private Map<String, Integer> vector;

	/**
	 * Creates a new, empty StateVector
	 */
	public StateVector(){	
		this.vector = new HashMap<String, Integer>();
	}

	public void clear() {
		vector.clear();
	}

	public boolean containsUser(String arg0) {
		return vector.containsKey(arg0);
	}

	public Integer getUser(String arg0) {
		return vector.get(arg0);
	}

	public boolean isEmpty() {
		return vector.isEmpty();
	}

	public Set<String> getUsers() {
		return vector.keySet();
	}

	public Integer put(String arg0, Integer arg1) {
		return vector.put(arg0, arg1);
	}

	public void putAll(Map<? extends String, ? extends Integer> arg0) {
		vector.putAll(arg0);
	}

	public Integer remove(String arg0) {
		return vector.remove(arg0);
	}
	
	public void incrementUser(String user){
		vector.put(user, getUser(user) + 1);
	}

	public void decrementUser(String user){
		Integer current = getUser(user);
		vector.put(user, (current < 1)? 0 : (current - 1));
	}
	
	public void resetUser(String user){
		vector.put(user, 0);
	}
	
	public int size() {
		return vector.size();
	}	
	
	public StateVector clone(){
		StateVector copy = new StateVector();
		for(String s : this.getUsers()){
			copy.put(s, this.getUser(s));
		}
		return copy;
	}
}
