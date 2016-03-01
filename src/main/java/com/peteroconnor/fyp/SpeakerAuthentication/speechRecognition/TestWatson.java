package com.peteroconnor.fyp.SpeakerAuthentication.speechRecognition;

import java.io.File;

import com.peteroconnor.fyp.SpeakerAuthentication.Entity.AudioData;

public class TestWatson {

	public static void main(String[] args) {
		File file = new File(AudioData.VOICE_FILE_LOCATION);
		String prompt = "the quick brown fox jumped over the lazy dog";
		
		SpeechRecognition sr = new SpeechRecognition();
		sr.transcriptUsingHTTP(file);
		
		System.out.println(sr.getSpokenPhrase());
		
		System.out.println(sr.isPhraseMatch(prompt));

	}
}
