package com.peteroconnor.fyp.SpeakerAuthentication.DB;

import com.peteroconnor.fyp.SpeakerAuthentication.Entity.Phrase;

public interface IPhraseDAO {
	void save(Phrase phrase);
	Phrase find(Long id);
}
