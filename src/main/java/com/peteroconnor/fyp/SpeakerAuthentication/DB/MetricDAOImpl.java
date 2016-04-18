package com.peteroconnor.fyp.SpeakerAuthentication.DB;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.Metric;

public class MetricDAOImpl implements IMetricDOA{
	private final String COLLECTION_NAME = "metrics";
	private DBCollection collection;
	
	public MetricDAOImpl(DB database){
		collection = database.getCollection(COLLECTION_NAME);
	}

	@Override
	public void save(Metric m) {
		BasicDBObject metric = getMetricDBObject(m);
		collection.insert(metric);
	}

	private BasicDBObject getMetricDBObject(Metric m) {
		BasicDBObject metric = new BasicDBObject("name",m.getName())
				.append("likeihood", m.getLikeihood())
				.append("phraseSameAsRegister", m.isPhraseSameAsRegister())
				.append("result", m.isResult());
		return metric;
	}
	
	

}
