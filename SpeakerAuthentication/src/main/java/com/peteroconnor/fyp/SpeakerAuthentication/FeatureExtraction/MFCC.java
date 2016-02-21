package com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction;

import java.util.Arrays;

import org.apache.commons.math3.complex.Complex;

import com.peteroconnor.fyp.SpeakerAuthentication.Entity.AudioData;
import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.Mel.MelFilterBank;
import com.peteroconnor.fyp.SpeakerAuthentication.GUI.GraphAudio;

public class MFCC {
	WavProcessor wavProcessor = new WavProcessor();
	Framer f = new Framer();
	AudioData audioData;
	Windower windower = new Windower();
	private int fftLength;
	
	public void preformFeatureExtraction(){
		audioData = wavProcessor.bytesToAmplitude(AudioData.VOICE_FILE_LOCATION);
    	double[][] framedSignal = f.frameSignal(audioData);
    	audioData.setFramedSignal(framedSignal);
//    	audioData.savetoFile();
    	double[][] newFrames = windower.applyHammingWindow(framedSignal);
    	fftLength = newFrames[0].length;
    	
    	FFT fft = new FFT();
    	double[][] absolutes = fft.doFFT(newFrames);
//    	GraphAudio g = new GraphAudio("test", absolutes[20]);
//    	g.showGraph();
//    	System.out.println(Arrays.toString(absolutes[20]));
    	MelFilterBank mfb = new MelFilterBank();
    	double[][] filterBankResult =  mfb.getFilterBankResult(absolutes);
    	
//    	double[][] intensities = mfb.getIntensitiesInAllFrames();
    	
//    	System.out.println(mfb.frequencyToMel(160));
//    	System.out.println(mfb.melToFrequency(2840.0641213170993));
//    	System.out.println(mfb.melToFrequency(401.9705861630036));
//    	System.out.println(Arrays.toString(mfb.getMelSpacedPoints()));
//    	System.out.println(Arrays.toString(mfb.melSpacedPointsToFrequencySpacedPoints()));
    	
    	
    	
//    	//Test Sine Wave
//    	int size = 512;
//    	double[] sine = new double[size];
//    	double TWO_PI = (Math.PI * 2);
//    	double phaseIncrement = TWO_PI * size;
//    	double currentPhase = 0.0;
//    	for(int i = 0; i<size; i++){
//    		sine[i] = Math.sin(currentPhase);
//    		System.out.println(sine[i]);
//    		currentPhase += phaseIncrement;
//    	}
//    	GraphAudio s = new GraphAudio("sine Wave", sine);
//    	s.showGraph();
//    	
//    	//End Test Sine wave
    	
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
