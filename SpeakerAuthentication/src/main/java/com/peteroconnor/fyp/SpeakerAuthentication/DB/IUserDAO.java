package com.peteroconnor.fyp.SpeakerAuthentication.DB;

import com.peteroconnor.fyp.SpeakerAuthentication.Entity.User;

public interface IUserDAO {
	void save(User u);
	User find(Long id);
	void update(User u);
	void delete(Long id);
}
