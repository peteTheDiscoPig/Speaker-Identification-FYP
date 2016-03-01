package com.peteroconnor.fyp.SpeakerAuthentication.PhraseGen;

import java.util.Random;

import com.peteroconnor.fyp.SpeakerAuthentication.Entity.Word;

public class Phrase {
	private String singular;
	private String plural1;
	private String plural2;
	private String plural3;
	
	public Phrase(String singular, String plural1, String plural2, String plural3){
		this.singular = singular;
		this.plural1 = plural1;
		this.plural2 = plural2;
		this.plural3 = plural3;
	}
	
	public String getPhrase(){
		String phrase = "one " + singular + " ";
		phrase += numbers[getRandNum()]+ " " + plural1 + " ";
		phrase += numbers[getRandNum()]+ " " + plural2 + " ";
		phrase += "and ";
		phrase += numbers[getRandNum()]+ " " + plural3; 
		
		return phrase;
	}
	
	private int getRandNum() {
		Random rand = new Random();
		int number = (rand.nextInt(10 - 2 + 1) + 2);
		return number;
	}

	/**
	 * @return the singular
	 */
	public String getSingular() {
		return singular;
	}
	/**
	 * @param singular the singular to set
	 */
	public void setSingular(String singular) {
		this.singular = singular;
	}
	/**
	 * @return the plural1
	 */
	public String getPlural1() {
		return plural1;
	}
	/**
	 * @param plural1 the plural1 to set
	 */
	public void setPlural1(String plural1) {
		this.plural1 = plural1;
	}
	/**
	 * @return the plural2
	 */
	public String getPlural2() {
		return plural2;
	}
	/**
	 * @param plural2 the plural2 to set
	 */
	public void setPlural2(String plural2) {
		this.plural2 = plural2;
	}
	
	private static final String[] numbers = {
			"", "one", "two", "three", "four", "five",
			"six", "seven", "eight", "nine", "ten"
	};
	
	
}
