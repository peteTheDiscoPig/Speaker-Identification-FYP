package com.peteroconnor.fyp.SpeakerAuthentication.Entity;

import com.peteroconnor.fyp.SpeakerAuthentication.GUI.RegisterRecord;

public class Metric {
	
	private String name;
	private double likeihood;
	private boolean phraseSameAsRegister;
	private boolean result;
	private String expectedName;
	private String phraseNo;

	public Metric(String name, double likeihood, String phrase, boolean result, String expectedName, String phraseNo) {
		this.setName(name);
		this.setLikeihood(likeihood);
		this.setResult(result);
		this.setPhraseSameAsRegister(phrase == RegisterRecord.prompt ? true : false);
		this.expectedName = expectedName;
		this.phraseNo = phraseNo;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the likeihood
	 */
	public double getLikeihood() {
		return likeihood;
	}

	/**
	 * @param likeihood the likeihood to set
	 */
	public void setLikeihood(double likeihood) {
		this.likeihood = likeihood;
	}

	/**
	 * @return the phraseSameAsRegister
	 */
	public boolean isPhraseSameAsRegister() {
		return phraseSameAsRegister;
	}

	/**
	 * @param phraseSameAsRegister the phraseSameAsRegister to set
	 */
	public void setPhraseSameAsRegister(boolean phraseSameAsRegister) {
		this.phraseSameAsRegister = phraseSameAsRegister;
	}

	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * @return the expectedName
	 */
	public String getExpectedName() {
		return expectedName;
	}

	/**
	 * @param expectedName the expectedName to set
	 */
	public void setExpectedName(String expectedName) {
		this.expectedName = expectedName;
	}

	/**
	 * @return the phraseNo
	 */
	public String getPhraseNo() {
		return phraseNo;
	}

	/**
	 * @param phraseNo the phraseNo to set
	 */
	public void setPhraseNo(String phraseNo) {
		this.phraseNo = phraseNo;
	}

	
}
