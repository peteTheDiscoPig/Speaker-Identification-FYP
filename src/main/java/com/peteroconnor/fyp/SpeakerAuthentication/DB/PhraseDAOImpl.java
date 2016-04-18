package com.peteroconnor.fyp.SpeakerAuthentication.DB;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.Phrase;

public class PhraseDAOImpl implements IPhraseDAO{
	private final String COLLECTION_NAME = "phrases";
	private DBCollection collection;
	
	public PhraseDAOImpl(DB database){
		collection = database.getCollection(COLLECTION_NAME);
	}
	
	@Override
	public void save(Phrase phrase) {
		BasicDBObject dbPhrase = getPhraseDBObject(phrase);
		collection.insert(dbPhrase);
	}

	private BasicDBObject getPhraseDBObject(Phrase phrase) {
		BasicDBObject dbPhrase = new BasicDBObject("id", phrase.getId())
				.append("phrase", phrase.getPhrase());
		return dbPhrase;
	}

	@Override
	public Phrase find(Long id) {
		BasicDBObject dbObject = new BasicDBObject("id", id);
		DBObject phraseObj = collection.findOne(dbObject);
		Long _id = (Long) phraseObj.get("id");
		String phrase = (String) phraseObj.get("phrase");
		Phrase dbPhrase = new Phrase(_id, phrase);
		return dbPhrase;
	}

}
