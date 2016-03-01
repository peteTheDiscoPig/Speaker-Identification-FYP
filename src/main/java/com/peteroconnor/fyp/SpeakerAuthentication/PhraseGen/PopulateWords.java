package com.peteroconnor.fyp.SpeakerAuthentication.PhraseGen;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.peteroconnor.fyp.SpeakerAuthentication.DB.DBController;
import com.peteroconnor.fyp.SpeakerAuthentication.DB.WordDAOImpl;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.Word;

public class PopulateWords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBController dbcontroller = new DBController();
		dbcontroller.connect();
		
		WordDAOImpl w = new WordDAOImpl(dbcontroller.getDatabase());
		
		String line;
		int id =0;
		try{
			InputStream inStream = new FileInputStream("src/words.txt");
		    InputStreamReader inStreamReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
		    BufferedReader bufferedReader = new BufferedReader(inStreamReader);
		    
		    while ((line = bufferedReader.readLine()) != null) {
		    	id++;
		        String[] words = line.split(" ");
		        Word word = new Word((long) id, words[0], words[1]);
		        w.save(word);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
