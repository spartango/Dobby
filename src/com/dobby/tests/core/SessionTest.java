package com.dobby.tests.core;

import static org.junit.Assert.*;
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
		Session testSession = new Session("TestDoc");
		StateVector testVector = new StateVector();
		testSession.setCurrentState(testVector);
		Request testRequest = new InsertRequest("User1", testVector, 0,
				0, 'x');
		testSession.receiveRequest(testRequest);
		Request testRequest2 = new InsertRequest("User1", testVector, 1,
				1, 'b');
		Request testRequest3 = new InsertRequest("User1", testVector, 2,
				2, 'y');
		testSession.receiveRequest(testRequest2);
		testSession.receiveRequest(testRequest3);
		testSession.executeRequest();
		//stores a state vector after this request
		//will be used to simulate User2 that gets left behind
		StateVector laggyTestVector = testVector.clone();
		testSession.executeRequest();
		assertTrue(testSession.getCurrentText().equals("xb"));
		
		Request testRequest4 = new InsertRequest("User2", laggyTestVector, 3,
				1, 'w');
		testSession.receiveRequest(testRequest4);
		testSession.executeRequest();
		testSession.executeRequest();
		System.out.println(testSession.getCurrentText());
		
		
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
		Session testSession = new Session("TestDoc");
		StateVector testVector = new StateVector();
		assertTrue(testSession.Reachable(testVector));
		Request testRequest = new InsertRequest("Test", testVector, 0,
				0, 'a');
		testSession.receiveRequest(testRequest);
		assertTrue(testSession.Reachable(new StateVector()));
		assertTrue(testSession.Reachable(testSession.getCurrentState()));
		assertFalse(testSession.Reachable(testSession.getCurrentState().incrementedUser("Test")));
		Request testRequest2 = new InsertRequest("Test2", testVector, 0,
				1, 'a');
		testSession.receiveRequest(testRequest2);
		testVector.incrementUser("Test2");
		assertTrue(testSession.Reachable(testVector));
	}
}
