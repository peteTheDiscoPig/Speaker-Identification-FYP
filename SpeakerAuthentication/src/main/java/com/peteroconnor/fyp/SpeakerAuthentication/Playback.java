package com.peteroconnor.fyp.SpeakerAuthentication;

import java.io.File;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import edu.cmu.sphinx.tools.audio.AudioDataInputStream;
import edu.cmu.sphinx.tools.audio.AudioPlayer;

public class Playback {
	public final String SOUND_FILENAME = "C:/SpeakerAuthentication/audio/voice.wav";
	
	public void playVoice() {
		try
	    {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File(SOUND_FILENAME)));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
		
	}
}
