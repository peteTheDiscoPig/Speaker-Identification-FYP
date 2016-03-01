package com.peteroconnor.fyp.SpeakerAuthentication.DB;

import com.peteroconnor.fyp.SpeakerAuthentication.Entity.Word;

public interface IWordDAO {
	void save(Word w);
	Word find(Long id);
}
