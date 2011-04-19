package com.dobby.tests.request;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dobby.core.StateVector;
import com.dobby.core.requests.InsertRequest;

public class InsertRequestTest {

	@Test
	public void testInsertTransform() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public void testDeleteTransform() {
		fail("Not yet implemented"); // TODO
	}


	@Test
	public void testApply() {
		String testString = "0123456789abcdef";
		InsertRequest insert = new InsertRequest("Test", new StateVector(), 0,
				3, 'x');
		assertTrue("012x3456789abcdef".equals(insert.apply(testString)));
	}

	@Test
	public void testBeginningApply() {
		String testString = "0123456789abcdef";
		InsertRequest insert = new InsertRequest("Test", new StateVector(), 0,
				0, 'x');
		String product = insert.apply(testString);
		System.out.println(testString + " Insert at beginning produced "
				+ product);
		assertTrue("x0123456789abcdef".equals(product));
	}

	@Test
	public void testEndApply() {
		String testString = "0123456789abcdef";
		InsertRequest insert = new InsertRequest("Test", new StateVector(), 0,
				16, 'x');
		String product = insert.apply(testString);
		System.out.println(testString + " Insert at end produced " + product);
		assertTrue("0123456789abcdefx".equals(product));
	}

	@Test
	public void testOutOfBoundsCase() {
		String testString = "0123456789abcdef";
		InsertRequest insert = new InsertRequest("Test", new StateVector(), 0,
				18, 'x');
		assertTrue("0123456789abcdef".equals(insert.apply(testString)));
	}
}
