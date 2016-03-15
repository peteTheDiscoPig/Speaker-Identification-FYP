package com.peteroconnor.fyp.SpeakerAuthentication.DB;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.peteroconnor.fyp.SpeakerAuthentication.GaussianMixtureModel;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.User;

public class UserDAOImpl implements IUserDAO{

	private final String COLLECTION_NAME = "users";
	private DBCollection collection;
	
	public UserDAOImpl(DB database){
		collection = database.getCollection(COLLECTION_NAME);
	}

	public void save(User u) {
		BasicDBObject user = getUserBasicDBObject(u);
		collection.insert(user);
	}

	public User find(Long id) {
		BasicDBObject dbObject = new BasicDBObject("id", id);
		DBObject userObj = collection.findOne(dbObject);
		String name = (String) userObj.get("name");
		String phoneNumber = (String) userObj.get("phoneNumber");
		String email = (String) userObj.get("email");
		double[][] mfcc = (double[][]) userObj.get("mfcc");
		User user = new User(name, phoneNumber, email);
		user.setMFCCs(mfcc);
		return user;
	}

	public void update(User u) {
		BasicDBObject oldDBObject = new BasicDBObject("id", u.getId());
		BasicDBObject userDbObject = getUserBasicDBObject(u);
		BasicDBObject newDBObject = new BasicDBObject("$set", userDbObject);
		
		collection.update(oldDBObject, newDBObject);
	}

	public void delete(Long id) {
		BasicDBObject dbObject = new BasicDBObject("id", id);
		collection.remove(dbObject);
		
	}
	
	private BasicDBObject getUserBasicDBObject(User u){
		BasicDBObject user = new BasicDBObject("id", u.getId())
				.append("name", u.getName())
				.append("phoneNumber", u.getPhoneNumber())
				.append("email", u.getEmail())
				.append("mfcc", u.getMFCCs());
		return user;
	}
	
	
}
