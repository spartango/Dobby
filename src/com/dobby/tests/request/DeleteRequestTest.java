package com.dobby.tests.request;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.dobby.core.StateVector;
import com.dobby.core.requests.DeleteRequest;

public class DeleteRequestTest {

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
		DeleteRequest delete = new DeleteRequest("Test", new StateVector(), 0,
				3, '3');
		assertTrue("012456789abcdef".equals(delete.apply(testString)));
	}

	@Test
	public void testBeginningApply() {
		String testString = "0123456789abcdef";
		DeleteRequest delete = new DeleteRequest("Test", new StateVector(), 0,
				0, '0');
		String product = delete.apply(testString);
		System.out.println(testString + " Delete at beginning produced "
				+ product);
		assertTrue("123456789abcdef".equals(product));
	}

	@Test
	public void testEndApply() {
		String testString = "0123456789abcdef";
		DeleteRequest delete = new DeleteRequest("Test", new StateVector(), 0,
				15, 'f');
		String product = delete.apply(testString);
		System.out.println(testString + " Delete at end produced " + product);
		assertTrue("0123456789abcde".equals(product));
	}

	@Test
	public void testOutOfBoundsCase() {
		String testString = "0123456789abcdef";
		DeleteRequest delete = new DeleteRequest("Test", new StateVector(), 0,
				16, 'x');
		assertTrue("0123456789abcdef".equals(delete.apply(testString)));
	}
}
