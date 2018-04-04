package com.vendetta.vms;

import java.util.HashMap;
import java.util.UUID;

import org.bson.Document;

public class User {
	
	private static HashMap<UUID, User> users = new HashMap<UUID, User>();
	
	private UUID id;
	private String name;
	private double money;
	
	public synchronized static HashMap<UUID, User> getUsers() {
		return users;
	}
	
	public synchronized static User getUser(UUID id) {
		return users.get(id);
	}

	public synchronized static User addUser(User user) {
		users.put(user.getID(), user);
		return user;
	}
	
	public User(String username, UUID id, double money) {
		
		System.out.println(money);
		
		this.name = username;
		this.id = id;
		this.money = money;
		
		System.out.println(money);
	}
	
	public User(String user) {
		
		this(user, UUID.randomUUID(), 0.0d);
		
	}
	
	public User(Document d) {
		
		this(d.getString("name"), UUID.fromString(d.getString("_id")), d.getDouble("money"));
		
	}

	public UUID getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public Document toDoc() {
		return new Document("_id", id.toString())
				.append("name", name)
				.append("money", money);
	}
	
}
