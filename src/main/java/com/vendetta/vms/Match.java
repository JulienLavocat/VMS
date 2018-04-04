package com.vendetta.vms;

import com.vendetta.vms.utils.Utils;

public class Match {

	public String ip;
	public String key;
	public int port;
	public long createdAt;
	public int players;
	
	public Match(String ip, int port) {
		
		this.ip = ip;
		this.port = port;
		this.createdAt = System.currentTimeMillis();
		this.players = 0;
		this.key = Utils.randomString(16);
		
	}
	
	public Match incPlayers() {
		this.players++;
		return this;
	}

}
