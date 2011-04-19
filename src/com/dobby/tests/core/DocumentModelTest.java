package com.dobby.tests.core;

import static org.junit.Assert.assertTrue;
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

		Request testReq2 = new InsertRequest("Test", newState.clone(), 1, 1, 'b');
		StateVector nextState = testReq2.getStateVector().incrementedUser(
				testReq2.getUser());
		doc.addRequest(testReq2.getStateVector(), nextState, testReq2);
		assertTrue(doc.outgoingRequestsOf(doc.getRoot()).contains(testReq));
	}

	@Test
	public void testContainsRequest() {
		DocumentModel doc = new DocumentModel();
		Request testReq = new InsertRequest("Test", new StateVector(), 0, 0,
				'a');
		assertTrue(doc.getRoot().equals(testReq.getStateVector()));
		assertTrue(doc.containsState(testReq.getStateVector()));
		StateVector newState = testReq.getStateVector().incrementedUser(
				testReq.getUser());
		doc.addRequest(testReq.getStateVector(), newState, testReq);
		assertTrue(doc.containsState(newState.clone()));

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
