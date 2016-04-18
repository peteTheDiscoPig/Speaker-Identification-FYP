package com.peteroconnor.fyp.SpeakerAuthentication.PhraseGen;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.peteroconnor.fyp.SpeakerAuthentication.DB.DBController;
import com.peteroconnor.fyp.SpeakerAuthentication.DB.PhraseDAOImpl;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.Phrase;

public class PopulatePhrases {

	public static void main(String[] args) {
		DBController dbcontroller = new DBController();
		dbcontroller.connect();
		
		PhraseDAOImpl phraseDAOImpl = new PhraseDAOImpl(dbcontroller.getDatabase());

		String line;
		int id = 0;
		
		try{
			InputStream inStream = new FileInputStream("src/phrases.txt");
		    InputStreamReader inStreamReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
		    BufferedReader bufferedReader = new BufferedReader(inStreamReader);
		    
		    while ((line = bufferedReader.readLine()) != null){
		    	id++;
		    	com.peteroconnor.fyp.SpeakerAuthentication.Entity.Phrase phrase;
		    	phrase = new Phrase((long) id, line);
		    	phraseDAOImpl.save(phrase);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
