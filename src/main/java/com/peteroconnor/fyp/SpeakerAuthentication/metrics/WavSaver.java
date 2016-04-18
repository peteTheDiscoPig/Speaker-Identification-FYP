package com.peteroconnor.fyp.SpeakerAuthentication.metrics;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.peteroconnor.fyp.SpeakerAuthentication.Entity.AudioData;

import gov.sandia.cognition.io.FileUtil;

public class WavSaver {
	private final String FOLDER_NAME = "C:/SpeakerAuthentication/Tests";
	private File voiceFile = new File(AudioData.VOICE_FILE_LOCATION);
	
	public void renameAndSaveWavToNewLocation(String personName, String phraseNo){
		File folderToSaveIn = new File(FOLDER_NAME+"/"+personName);
		createFolder(folderToSaveIn);
		File newFile = new File(FOLDER_NAME+"/"+personName+"/"+phraseNo+".wav");
		try {
			FileUtils.copyFile(voiceFile, newFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Wav saved for tests");
		
		
	}
	
	private void createFolder(File folder){
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
