package com.vendetta.vms;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.UUID;

public class Matchs {
	
	public static ArrayList<Match> matchs = new ArrayList<Match>();
	
	private static final int MAX_SERVER_PORT = 10000;
	
	private static Match currentMatch = null;
	private static int currentPort = 2000;
	
	public synchronized static Match getMatch(UUID id) {
		
		if(currentMatch == null)
			return spawnMatch().incPlayers();
		
		if(currentMatch.players == 10)
			return spawnMatch().incPlayers();
		
		return currentMatch.incPlayers();	
	}

	private synchronized static Match spawnMatch() {

		currentPort ++;
		
		if(currentPort > MAX_SERVER_PORT)
			return null;
		
		//Create and start gs on server
		
		//TODO: check for validation packet before continue
		
		try {
			currentMatch = new Match(InetAddress.getLocalHost().getHostAddress(), currentPort);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		matchs.add(currentMatch);
		
		return currentMatch;
	}
	
}
