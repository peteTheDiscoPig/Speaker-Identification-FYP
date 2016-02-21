package com.peteroconnor.fyp.SpeakerAuthentication.PhraseGen;

import java.io.IOException;
import java.util.Random;

import com.peteroconnor.fyp.SpeakerAuthentication.DB.DBController;
import com.peteroconnor.fyp.SpeakerAuthentication.DB.WordDAOImpl;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.Word;

//C:\program files\mongodb\server\3.0\bin)
//mongod
public class PhraseGen {
	private DBController dbcontroller;
	private WordDAOImpl wordDaoI;
	
	public PhraseGen(){
		dbcontroller = new DBController();
		dbcontroller.connect();
		wordDaoI = new WordDAOImpl(dbcontroller.getDatabase());
	}
	
	public String generatePhrase(){
		String singular = getWord().getSingular();
		String plural1 = getWord().getPlural();
		String plural2 = getWord().getPlural();
		String plural3 = getWord().getPlural();
		Phrase phrase = new Phrase(singular, plural1, plural2, plural3);
		
		return phrase.getPhrase();
	}

	private Word getWord() {
		Random rand = new Random();
		Long id = (long) (rand.nextInt(Word.MAX_ID - Word.MIN_ID + 1) + Word.MIN_ID);
		Word word = wordDaoI.find(id);
		return word;
	}
}
