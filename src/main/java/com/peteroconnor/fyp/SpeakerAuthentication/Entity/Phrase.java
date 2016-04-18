package com.peteroconnor.fyp.SpeakerAuthentication.Entity;

public class Phrase {
	public static final int MIN_ID = 1, MAX_ID = 10;
	private Long id;
	private String phrase;
	
	public Phrase(Long id, String phrase){
		this.id = id;
		this.phrase = phrase;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the phrase
	 */
	public String getPhrase() {
		return phrase;
	}

	/**
	 * @param phrase the phrase to set
	 */
	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

}
