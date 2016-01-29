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
//    	GraphAudio p = new GraphAudio("newFrame", newFrames[6]);
//    	p.showGraph();
//    	GraphAudio g = new GraphAudio("oldFrame", framedSignal[6]);
//    	g.showGraph();

//    	GraphAudio g = new GraphAudio("test", audioData.getAmplidudes());
//    	g.showGraph();
//    	GraphAudio p = new GraphAudio("test", audioData.getPreEmphasisedAmplitudes());
//    	p.showGraph();

    	//TEST DATA for window shape
//    	double[] testa = new double[]{10,-10,10,-10,10,-10,10,-10,
//    			10,-10,10,-10,10,-10,10,-10,10,-10,10,-10,10,-10,
//    			10,-10,10,-10,10,-10,10,-10,10,-10,10,-10,10,-10,10,
//    			-10,10,-10,10,-10,10,-10,10,-10,10,-10,10,-10,10,-10,10,
//    			-10,10,-10,10,-10,10,-10,10,-10,10,-10,10,-10,10,-10,10,
//    			-10,10,-10,10,-10,10,-10,10,-10,10,-10,10,-10,10,-10,10,-10,
//    			10,-10,10,-10,10,-10,10,-10,10,-10,10,-10,10,-10,};
//    	
//    	double[][] test = new double[2][100];
//    	test[0] = testa;
//    	test[1] = testa;
//    	
//    	double[][] newTestFrames = windower.applyHammingWindow(test);
//    	
//    	GraphAudio p = new GraphAudio("newFrame", newTestFrames[1]);
//    	p.showGraph();
//    	GraphAudio g = new GraphAudio("oldFrame", test[1]);
//    	g.showGraph();
    	
    	
    	//***********************************************************************************************
    	
    	
//    	
	}
}
