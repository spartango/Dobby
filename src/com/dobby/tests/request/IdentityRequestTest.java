package com.dobby.tests.request;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dobby.core.Request;
import com.dobby.core.StateVector;
import com.dobby.core.requests.DeleteRequest;
import com.dobby.core.requests.IdentityRequest;
import com.dobby.core.requests.InsertRequest;

public class IdentityRequestTest {

	@Test
	public void testTransform() {
		Request r1 = new InsertRequest("Cow", new StateVector(), 0, 0, 'a');
		Request r2 = new DeleteRequest("Cow", new StateVector(), 0, 0, 'a');
		Request r3 = new IdentityRequest("Cow", new StateVector(), 0);
		//This is somewhat silly, in as much as the identity shouldn't 
		// really do anything. even so, we expect NO changes no matter what.
		assertTrue(r3.getPosition() == r3.transform(r1).getPosition());
		assertTrue(r3.getPosition() == r3.transform(r2).getPosition());
	}

	@Test
	public void testApply() {
		IdentityRequest request = new IdentityRequest("Test",
				new StateVector(), 0);
		String testString = "abc123";
		assertTrue(testString.equals(request.apply(testString)));
	}
}
