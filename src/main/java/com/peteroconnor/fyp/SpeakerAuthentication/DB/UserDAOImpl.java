package com.peteroconnor.fyp.SpeakerAuthentication.DB;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.peteroconnor.fyp.SpeakerAuthentication.GaussianMixtureModel;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.User;
import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.DCT;

public class UserDAOImpl implements IUserDAO {

	private final String COLLECTION_NAME = "users";
	private DBCollection collection;

	public UserDAOImpl(DB database) {
		collection = database.getCollection(COLLECTION_NAME);
	}

	public void save(User u) {
		Long maxId = getMaxId();
		u.setId(maxId + 1); 
		BasicDBObject user = getUserBasicDBObject(u);
		collection.insert(user);
	}

	public User find(Long id) {
		BasicDBObject dbObject = new BasicDBObject("id", id);
		DBObject userObj = collection.findOne(dbObject);
		String name = (String) userObj.get("name");
		String phoneNumber = (String) userObj.get("phoneNumber");
		String email = (String) userObj.get("email");
		//double[][] mfcc = (double[][]) userObj.get("mfcc");
		
		
		BasicDBList list = (BasicDBList) userObj.get("mfcc");
		int listSize = list.size();
		double[][] mfcc = new double[listSize][DCT.NUMBER_OF_MFCC];
		for(int i = 0; i < list.size(); i++){
			BasicDBList subList = (BasicDBList) list.get(i);
			Object[] objArr = subList.toArray();
			
			//double d = (double) objArr[0];
			int objArrSize = objArr.length;
			for(int j = 0; j < objArr.length; j++){
				mfcc[i][j] = (double) objArr[j];
			}
		}
		
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

	private BasicDBObject getUserBasicDBObject(User u) {
		BasicDBObject user = new BasicDBObject("id", u.getId()).append("name", u.getName())
				.append("phoneNumber", u.getPhoneNumber()).append("email", u.getEmail()).append("mfcc", u.getMFCCs());
		return user;
	}

	private Long getMaxId() {
		// go to database, find max id, id = max + 1
		Long maximum;
		BasicDBObject max = new BasicDBObject();
		max.put("id", -1);
		DBCursor cursor = collection.find().sort(max).limit(1);
		
		if(cursor.hasNext()){
			DBObject maxVal = cursor.next();
			maximum = (Long) maxVal.get("id");
		}
		else{
			maximum = (long) 0;
		}
		
		return maximum;
	}
}
