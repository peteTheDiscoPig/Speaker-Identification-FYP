package com.peteroconnor.fyp.SpeakerAuthentication.Entity;

public class Word {
	public static final int MIN_ID = 1, MAX_ID = 39;
	
	
	public Word(long id, String singular, String plural){
		this.id = id;
		this.singular = singular;
		this.plural = plural;
	}

	private long id;
	private String singular;
	private String plural;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the singular word
	 */
	public String getSingular() {
		return singular;
	}
	/**
	 * @param singular the singular word to set
	 */
	public void setSingular(String singular) {
		this.singular = singular;
	}
	
	/**
	 * @return the plural word
	 */
	public String getPlural() {
		return plural;
	}
	/**
	 * @param plural the plural word to set
	 */
	public void setPlural(String plural) {
		this.plural = plural;
	}
}
