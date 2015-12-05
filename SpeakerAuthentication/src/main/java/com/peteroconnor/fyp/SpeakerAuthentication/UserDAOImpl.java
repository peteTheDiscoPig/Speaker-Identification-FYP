package com.peteroconnor.fyp.SpeakerAuthentication;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

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
		String firstName = (String) userObj.get("firstName");
		String lastName = (String) userObj.get("lastName");
		String phoneNumber = (String) userObj.get("phoneNumber");
		String email = (String) userObj.get("email");
		GaussianMixtureModel speakerModel = (GaussianMixtureModel) userObj.get("speakerModel");
		User user = new User(firstName, lastName, phoneNumber, email, speakerModel);
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
				.append("firstName", u.getFirstName())
				.append("lastName", u.getLastName())
				.append("phoneNumber", u.getPhoneNumber())
				.append("email", u.getEmail())
				.append("speakerModel", u.getSpeakerModel());
		return user;
	}
	
	
}
