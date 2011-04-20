package com.dobby.tests.core;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.dobby.core.Request;
import com.dobby.core.Session;
import com.dobby.core.StateVector;
import com.dobby.core.requests.InsertRequest;

public class SessionTest {

	@Test
	public void testReceiveRequest() {
		Session testSession = new Session("TestDoc");
		Request testRequest = new InsertRequest("Test", new StateVector(), 0,
				0, 'a');
		testSession.receiveRequest(testRequest);
		assertTrue(!testSession.isRequestQueueEmpty());
		assertTrue(testSession.getRequest("Test", 0).equals(testRequest));
	}

	@Test
	public void testExecuteRequest() {
		Session testSession = new Session("TestDoc");
		Request testRequest = new InsertRequest("Test", new StateVector(), 0,
				0, 'a');
		testSession.receiveRequest(testRequest);
		testSession.executeRequest();
		assertTrue(testSession.getCurrentText().equals("a"));
	}

	@Test
	public void testTranslateRequest() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetRequest() {
		Session testSession = new Session("TestDoc");
		Request testRequest = new InsertRequest("Test", new StateVector(), 0,
				0, 'a');
		testSession.receiveRequest(testRequest);
		assertTrue(testSession.getRequest("Test", 0).equals(testRequest));
	}

	@Test
	public void testReachable() {
		fail("Not yet implemented"); // TODO
	}
}
