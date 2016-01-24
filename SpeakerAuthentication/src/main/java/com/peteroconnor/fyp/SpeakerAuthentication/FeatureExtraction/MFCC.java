package com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction;

import com.peteroconnor.fyp.SpeakerAuthentication.Entity.AudioData;
import com.peteroconnor.fyp.SpeakerAuthentication.GUI.GraphAudio;

public class MFCC {
	WavProcessor wavProcessor = new WavProcessor();
	Framer f = new Framer();
	AudioData audioData;
	Windower windower = new Windower();
	
	public void preformFeatureExtraction(){
		audioData = wavProcessor.bytesToAmplitude(AudioData.VOICE_FILE_LOCATION);
    	double[][] framedSignal = f.frameSignal(audioData);
    	audioData.setFramedSignal(framedSignal);
//    	audioData.savetoFile();
    	double[][] newFrames = windower.applyHammingWindow(framedSignal);
    	
    	//windowing(framedSignal)
    	
//    	GraphAudio g = new GraphAudio("test", framedSignal[100]);
//    	g.showGraph();
//    	GraphAudio p = new GraphAudio("test", newFrames[100]);
//    	p.showGraph();
    	
    	
    	GraphAudio g = new GraphAudio("test", audioData.getAmplidudes());
    	g.showGraph();
//    	GraphAudio p = new GraphAudio("test", audioData.getPreEmphasisedAmplitudes());
//    	p.showGraph();
	}
}
