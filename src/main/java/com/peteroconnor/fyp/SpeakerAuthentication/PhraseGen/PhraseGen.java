package com.peteroconnor.fyp.SpeakerAuthentication.PhraseGen;

import java.io.IOException;
import java.util.Random;

import com.peteroconnor.fyp.SpeakerAuthentication.DB.DBController;
import com.peteroconnor.fyp.SpeakerAuthentication.DB.PhraseDAOImpl;
import com.peteroconnor.fyp.SpeakerAuthentication.DB.WordDAOImpl;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.Phrase;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.Word;

//C:\program files\mongodb\server\3.0\bin)
//mongod
public class PhraseGen {
	private DBController dbcontroller;
	private WordDAOImpl wordDaoI;
	private PhraseDAOImpl phraseDAOImpl;
	
	public PhraseGen(){
		dbcontroller = new DBController();
		dbcontroller.connect();
		phraseDAOImpl = new PhraseDAOImpl(dbcontroller.getDatabase());
	}
	
	public String generatePhrase(){
		Random rand = new Random();
		Long id = (long) (rand.nextInt(Phrase.MAX_ID - Phrase.MIN_ID + 1) + Phrase.MIN_ID);
		Phrase phrase = phraseDAOImpl.find(id);
		return phrase.getPhrase();
//		String singular = getWord().getSingular();
//		String plural1 = getWord().getPlural();
//		String plural2 = getWord().getPlural();
//		String plural3 = getWord().getPlural();
//		Phrase phrase = new Phrase(singular, plural1, plural2, plural3);
//		
//		return phrase.getPhrase();
	}
	
	public String getPhrase(Long id){
		Phrase phrase = phraseDAOImpl.find(id);
		return phrase.getPhrase();
		
	}

	private Word getWord() {
		Random rand = new Random();
		Long id = (long) (rand.nextInt(Word.MAX_ID - Word.MIN_ID + 1) + Word.MIN_ID);
		Word word = wordDaoI.find(id);
		return word;
	}
}
