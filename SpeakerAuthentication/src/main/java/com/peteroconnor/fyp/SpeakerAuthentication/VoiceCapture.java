package com.peteroconnor.fyp.SpeakerAuthentication;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class VoiceCapture {
	//ref https://docs.oracle.com/javase/tutorial/sound/capturing.html
	// ref http://www.codejava.net/coding/capture-and-record-sound-into-wav-file-with-java-sound-api
	private final String FOLDER_NAME = "C:/SpeakerAuthentication/audio";
	
	private File folder = new File(FOLDER_NAME);
	private File voiceFile = new File(AudioData.VOICE_FILE_LOCATION);
	
	private final long AUDIO_LENGTH = 5000;//how long to record for milliseconds
	
	public final float SAMPLE_RATE = 44100;
	public final int SAMPLE_SIZE_IN_BITS = 16;//depth
	public final int CHANNELS = 1;//mono = 1, stereo = 2
	public final boolean SIGNED = true;
	public final boolean BIG_ENDIAN = false;
	
	MyTimer timer = new MyTimer();
	
	TargetDataLine line;
	AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
	
	public VoiceCapture(){
		createFolder();
	}
	
	
	public void capture(){
		AudioFormat audioFormat = new AudioFormat(SAMPLE_RATE,
				SAMPLE_SIZE_IN_BITS, CHANNELS, SIGNED, BIG_ENDIAN);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(audioFormat);
			line.start();
			
			AudioInputStream ais = new AudioInputStream(line);
			System.out.println("Recording");
			timer.startTimer(AUDIO_LENGTH, line);
			AudioSystem.write(ais, fileType, voiceFile);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void createFolder(){
		if(!folder.exists()){
			try{
				folder.mkdirs();
		    } 
		    catch(SecurityException se){
		    	System.out.println("Unable to create folder");
		    }        
		}
	}
}
