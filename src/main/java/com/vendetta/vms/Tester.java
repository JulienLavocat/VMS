package com.vendetta.vms;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Tester implements Runnable {

	public static final String base = "http://localhost:8080/";
	
	@Override
	public void run() {
		
		send("match/z");
		
		Thread.currentThread().interrupt();
		
	}
	
	public void send(String address) {
		try {
			HttpURLConnection con = (HttpURLConnection) new URL(base + address).openConnection();
			con.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
