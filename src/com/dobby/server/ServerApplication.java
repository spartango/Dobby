package com.dobby.server;

import com.dobby.app.DobbyServer;

public class ServerApplication {
	public static final int DEFAULT_PORT = 9092;

	public static void main(String[] args) {
		DobbyServer app = new DobbyServer("Untitled", DEFAULT_PORT);
		System.out.println("Started Server on port 9092");
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
