package com.peteroconnor.fyp.SpeakerAuthentication;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class DBController {
	
	private String hostName;
	private int port;
	private String dbName;
	
	private DB database;
	private DBCollection dBCollection;
	
	public DBController(){
		hostName = "localhost";
		port = 27017;
		dbName = "voiceDB";
	}
	
	public DBController(String hostName, int port, String dbName){
		this.hostName = hostName;
		this.port = port;
		this.dbName = dbName;
	}
	
	public void connect(){
		try {
			MongoClient mongo = new MongoClient(hostName, port);
			database = mongo.getDB(dbName);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DBCollection getCollection(String collectionName){
		DBCollection dbCollection = database.getCollection(collectionName);
		return dbCollection;
	}
	
	
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public DB getDatabase() {
		return database;
	}
	public void setDatabase(DB database) {
		this.database = database;
	}
	public DBCollection getdBCollection() {
		return dBCollection;
	}
	public void setdBCollection(DBCollection dBCollection) {
		this.dBCollection = dBCollection;
	}
	
	
	
}
