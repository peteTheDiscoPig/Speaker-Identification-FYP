package com.peteroconnor.fyp.SpeakerAuthentication.Entity;

import com.peteroconnor.fyp.SpeakerAuthentication.GaussianMixtureModel;

public class User {
	
	private Long id;
	private String name;
	private String phoneNumber;
	private String email;
	private double[][] MFCCs;
	
	
	
	public User(){
		
	}
	
	public User(String name, String phoneNumber, String email){
		this.setName(name);
		this.setPhoneNumber(phoneNumber);
		this.setEmail(email);
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
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mFCCs
	 */
	public double[][] getMFCCs() {
		return MFCCs;
	}

	/**
	 * @param mFCCs the mFCCs to set
	 */
	public void setMFCCs(double[][] mFCCs) {
		MFCCs = mFCCs;
	}
	
	
}
