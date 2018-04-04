package com.vendetta.vms;

import java.util.UUID;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DB {
	
	public static MongoClient client;
	public static MongoDatabase db;
	public static MongoCollection<Document> users;
	
	public static void init() {
		client = new MongoClient("89.234.183.252:27017");
		db = client.getDatabase("vendetta");
		users = db.getCollection("users");
	}
	
	public static User createUser(User user) {
		users.insertOne(user.toDoc());
		User.addUser(user);
		return user;
	}
	
	public static User fetchUser(UUID id) {
		
		return new User(users.find(new Document("id", id)).first());
		
	}
	
	public static void close() {
		client.close();
	}

}
