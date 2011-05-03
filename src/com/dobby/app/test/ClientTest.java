package com.dobby.app.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 9092);
			final BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			Thread t = new Thread() {
				public void run() {
					while (true) {
						try {
							System.out.println(">> " + reader.readLine());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			};
			t.start();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
