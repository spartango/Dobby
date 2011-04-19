package com.dobby.tests.core;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dobby.core.StateVector;

public class StateVectorTest {

	@Test
	public void testIncrement() {
		StateVector testVector = new StateVector();
		testVector.put("Test", 0);
		testVector.incrementUser("Test");
		testVector.incrementUser("Test");
		testVector.incrementUser("Testx");
		assertTrue(testVector.getUser("Test") == 2
				&& testVector.getUser("Testx") == 1);
	}

	@Test
	public void testDecrement() {
		StateVector testVector = new StateVector();
		testVector.put("Test", 2);
		testVector.decrementUser("Test");
		assertTrue(testVector.getUser("Test") == 1
				&& testVector.getUser("Testx") == 0);
		testVector.decrementUser("Test");
		testVector.decrementUser("Testx");
		assertTrue(testVector.getUser("Test") == 0
				&& testVector.getUser("Testx") == 0);
	}
}
