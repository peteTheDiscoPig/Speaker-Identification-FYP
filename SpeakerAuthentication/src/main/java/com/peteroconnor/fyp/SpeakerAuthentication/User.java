package com.peteroconnor.fyp.SpeakerAuthentication;

public class User {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private GaussianMixtureModel speakerModel;
	
	
	
	public User(){
		
	}
	
	public User(String firstName, String lastName, String phoneNumber, String email, GaussianMixtureModel speakerModel){
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.speakerModel = speakerModel;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public GaussianMixtureModel getSpeakerModel() {
		return speakerModel;
	}
	public void setSpeakerModel(GaussianMixtureModel speakerModel) {
		this.speakerModel = speakerModel;
	}

	

}
