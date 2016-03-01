package com.peteroconnor.fyp.SpeakerAuthentication.DB;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.Word;

public class WordDAOImpl implements IWordDAO{
	private final String COLLECTION_NAME = "words";
	private DBCollection collection;
	
	public WordDAOImpl(DB database){
		collection = database.getCollection(COLLECTION_NAME);
	}
	
	public void save(Word w) {
		BasicDBObject word = getUserBasicDBObject(w);
		collection.insert(word);
	}

	public Word find(Long id) {
		BasicDBObject dbObject = new BasicDBObject("id", id);
		DBObject wordObj = collection.findOne(dbObject);
		String singular = (String) wordObj.get("singular");
		String plural = (String) wordObj.get("plural");
		Word word = new Word(id, singular, plural);
		return word;
	}
	
	private BasicDBObject getUserBasicDBObject(Word w){
		BasicDBObject word = new BasicDBObject("id", w.getId())
				.append("singular", w.getSingular())
				.append("plural", w.getPlural());
				
		return word;
	}
}
