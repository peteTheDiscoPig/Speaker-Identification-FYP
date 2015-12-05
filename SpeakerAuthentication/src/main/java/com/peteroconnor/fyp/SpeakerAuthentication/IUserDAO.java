package com.peteroconnor.fyp.SpeakerAuthentication;

public interface IUserDAO {
	void save(User u);
	User find(Long id);
	void update(User u);
	void delete(Long id);
}
