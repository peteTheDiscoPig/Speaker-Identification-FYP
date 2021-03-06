package com.peteroconnor.fyp.SpeakerAuthentication.speechRecognition;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechModel;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechSession;

public class SpeechRecognition {
	
	private final String HTTP_ENDPOINT = "https://stream.watsonplatform.net/speech-to-text/api";
    private final String USERNAME = "da04164d-1e1c-4dac-965f-77be2885d75a";//"ea4519f7-1b9d-4fa1-a7f8-8dba98c350f0"; 
    private final String PASSWORD = "l3Eu5vIgJAD6";//"5dzeWzbTOMBq";
    private final String CONTENT_TYPE = "audio/wav";
    private SpeechResults transcript;
    private JSONObject json;
    private String spokenPhrase = "";
	

	public void transcriptUsingHTTP(File audioFile) {
        System.out.println("Starting speech to text");

        SpeechToText service = new SpeechToText();
        service.setUsernameAndPassword(USERNAME, PASSWORD);
        service.setEndPoint(HTTP_ENDPOINT);

        SpeechSession session = service.createSession(SpeechModel.EN_BROADBAND16K);
//        System.out.println("session: " + session.toString());

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(SpeechToText.AUDIO, audioFile);
        params.put(SpeechToText.CONTENT_TYPE, CONTENT_TYPE);
        params.put(SpeechToText.CONTINUOUS, true);
        params.put(SpeechToText.SESSION_ID, session.getSessionId());

        transcript = service.recognize(params);
        
//        System.out.println("transcript: " + transcript.toString());
        
        service.deleteSession(session);
        System.out.println("Speech to text ended");

      }
	
	public String getSpokenPhrase(){
		json = new JSONObject(transcript.toString());
		JSONArray jsonArray = json.getJSONArray("results");
		
		spokenPhrase = jsonArray.getJSONObject(0).getJSONArray("alternatives")
				.getJSONObject(0).getString("transcript").trim();
		
		
		return spokenPhrase;
	}
	
	public boolean isPhraseMatch(String prompt){
		getSpokenPhrase();
		return spokenPhrase.equals(prompt);
	}
	
	
}
