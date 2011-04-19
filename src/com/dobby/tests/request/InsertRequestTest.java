package com.dobby.tests.request;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dobby.core.Request;
import com.dobby.core.StateVector;
import com.dobby.core.requests.DeleteRequest;
import com.dobby.core.requests.InsertRequest;

public class InsertRequestTest {

	@Test
	public void testInsertTransform() {
		String testString = "0123456789abcdef";
		Request r1 = new InsertRequest("Cow", new StateVector(), 0, 0, 'x');
		Request r2 = new InsertRequest("Bessie", new StateVector(), 1, 0, 'y');
		Request tf = r2.transform(r1);
		String product = tf.apply(r1.apply(testString));
		System.out.println(testString + " Double Insert at beginning produced "
				+ product);
		assertTrue(product.equals("xy0123456789abcdef"));

		r1 = new InsertRequest("Cow", new StateVector(), 0, 4, 'x');
		r2 = new InsertRequest("Bessie", new StateVector(), 1, 2, 'y');
		tf = r2.transform(r1);
		product = tf.apply(r1.apply(testString));
		System.out.println(testString + " Double Insert 2 produced " + product);
		assertTrue(product.equals("01y23x456789abcdef"));
	}

	@Test
	public void testDeleteTransform() {
		String testString = "0123456789abcdef";
		Request r1 = new InsertRequest("Cow", new StateVector(), 0, 4, 'x');
		Request r2 = new DeleteRequest("Bessie", new StateVector(), 1, 1, '1');
		Request tf = r1.transform(r2);
		String product = tf.apply(r2.apply(testString));
		System.out.println(testString + " Insert-Del produced " + product);
		assertTrue(product.equals("023x456789abcdef"));

		r1 = new InsertRequest("Cow", new StateVector(), 0, 4, 'x');
		r2 = new DeleteRequest("Bessie", new StateVector(), 1, 6, '6');
		tf = r1.transform(r2);
		product = tf.apply(r2.apply(testString));
		System.out.println(testString + " Insert-Del 2 produced " + product);
		assertTrue(product.equals("0123x45789abcdef"));
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
