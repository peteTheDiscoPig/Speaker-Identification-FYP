package com.peteroconnor.fyp.SpeakerAuthentication.metrics;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.peteroconnor.fyp.SpeakerAuthentication.DB.DBController;
import com.peteroconnor.fyp.SpeakerAuthentication.DB.UserDAOImpl;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.AudioData;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.User;
import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.MFCC;
import com.peteroconnor.fyp.SpeakerAuthentication.GMM.Identifer;

public class TestIdentificationWithSavedWavs {

	public static void main(String[] args) {
		
		int[] userNumbers = new int[]{1, 2, 4, 5, 6, 7, 8};
		int[] phrasesForLogin = new int[]{2, 3, 4, 5, 6, 9, 10};
		int registerPhraseNumber = 11;
		MFCC mfcc = new MFCC();
		final String FOLDER_NAME = "C:/SpeakerAuthentication";
		File newFile = new File(FOLDER_NAME+"/audio/voice.wav");
		int correct = 0, incorrect = 0;
		
		//Uncomment to store in database
//		for(int i = 0; i < userNumbers.length; i++){
//			//register users
//			
//			//go to person_userNumber[i] folder
//			//copy phrase_5 to C:\SpeakerAuthentication\audio folder with name voice.wav
//			//double[][] mfccs = mfcc.preformFeatureExtraction()
//			//create user with name = person_userNumber[i] and mfccs = mfccs
//			//save user
//			
//			File folderToSaveIn = new File(FOLDER_NAME+"/audio");
//			
//			File voiceFile = new File(FOLDER_NAME + "/Tests/person_"+userNumbers[i]+"/phrase_5.wav");
//			
//			try {
//				FileUtils.copyFile(voiceFile, newFile);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//			double[][] mfccs = mfcc.preformFeatureExtraction(); 
//			
//			User user = new User("person_"+userNumbers[i], "N/A", "N/A");
//			user.setMFCCs(mfccs);
//			
//			DBController dbcontroller = new DBController();
//			dbcontroller.connect();
//			UserDAOImpl userDAOImpl = new UserDAOImpl(dbcontroller.getDatabase());
//			userDAOImpl.save(user);
//			
//			
//		}
		
		for(int i = 0; i< userNumbers.length; i++){
			for(int j = 0; j < phrasesForLogin.length; j++){
				//login users
				
				//go to person_userNumber[i] folder
				//copy phrase_phrasesForLogin[j] to C:\SpeakerAuthentication\audio folder with name voice.wav
				//double[][] mfccs = mfcc.preformFeatureExtraction()
				
				//System.out.println("Expected: person_"+ userNumber[i] 
				
//				Identifer identifer = new Identifer(mfccs);
//				
//				bestMatch = identifer.getBestMatch();
//				System.out.println("Best match: " + bestMatch.getName());
				
				File voiceFile = new File(FOLDER_NAME + "/Tests/person_"+userNumbers[i]+"/phrase_"+phrasesForLogin[j]+".wav");
				try {
					FileUtils.copyFile(voiceFile, newFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				double[][] mfccs = mfcc.preformFeatureExtraction(); 
				
				Identifer identifer = new Identifer(mfccs);
				
				User bestMatch = identifer.getBestMatch();
				
				System.out.println("Best match: " + bestMatch.getName());
				System.out.println("Expected: person_"+userNumbers[i]+ "\n");
				
				if(bestMatch.getName().equals("person_"+userNumbers[i])){
					correct++;
				}
				else{
					incorrect++;
				}
					
			}
		}
		
		int total = correct + incorrect;
		System.out.println("Number of login attempts: " + total);
		System.out.println("Correct: " + correct + " Incorrect: "+ incorrect);
		double percentCorrect = ((double)correct/total)* 100;
		System.out.println("percent correct: "+ percentCorrect +"%");
		
		
		
	}

}
