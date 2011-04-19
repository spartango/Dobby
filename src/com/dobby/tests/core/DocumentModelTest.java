package com.dobby.tests.core;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.dobby.core.DocumentModel;
import com.dobby.core.Request;
import com.dobby.core.StateVector;
import com.dobby.core.requests.InsertRequest;

public class DocumentModelTest {

	@Test
	public void testAddRequest() {
		DocumentModel doc = new DocumentModel();
		Request testReq = new InsertRequest("Test", new StateVector(), 0, 0,
				'a');
		StateVector newState = testReq.getStateVector().incrementedUser(
				testReq.getUser());
		doc.addRequest(testReq.getStateVector(), newState, testReq);

		testReq = new InsertRequest("Test", newState.clone(), 1, 1, 'b');
		StateVector nextState = testReq.getStateVector().incrementedUser(
				testReq.getUser());
		doc.addRequest(testReq.getStateVector(), nextState, testReq);
		
		// TODO check if we really did add..
		fail("Ops performed, not validated");
	}

	@Test
	public void testContainsRequest() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testContainsState() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testApplyRequestToText() {
		fail("Not yet implemented"); // TODO
	}

}
