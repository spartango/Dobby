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

	@Test
	public void testEquals() {
		StateVector alpha = new StateVector();
		StateVector beta = new StateVector();
		assertTrue(alpha.equals(beta));
		alpha.put("TestA", 1);
		alpha.put("TestB", 2);
		beta.put("TestA", 1);
		beta.put("TestB", 2);
		assertTrue(alpha.equals(beta));
		alpha.put("TestC", 2);
		assertTrue(!alpha.equals(beta));
		assertTrue(!beta.equals(alpha));
	}
	
	@Test
	public void testClone() {
		StateVector alpha = new StateVector();
		StateVector beta = new StateVector();
		StateVector clone = beta.clone();
		assertTrue(alpha.equals(clone));
		assertTrue(beta != clone && beta.equals(clone));
	}
}
