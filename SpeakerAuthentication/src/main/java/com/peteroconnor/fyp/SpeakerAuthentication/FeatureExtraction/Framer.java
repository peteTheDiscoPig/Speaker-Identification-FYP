package com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction;

import java.util.Arrays;

import com.peteroconnor.fyp.SpeakerAuthentication.Entity.AudioData;

public class Framer {
	public static final int FRAME_SIZE = 40;
	public static final int SHIFT = 20;
	public static final int COUNT_IN_FRAME = (int) Math.round(AudioData.SAMPLE_RATE_IN_KHZ * FRAME_SIZE);
	public static final int COUNT_IN_SHIFT = (int) Math.round(AudioData.SAMPLE_RATE_IN_KHZ * SHIFT);
	
	private int numberOfFrames;
	
	public double[][] frameSignal(AudioData audioData){
		double[] samples = audioData.getAmplidudes();
		double[] newSamples = padIfNessessary(samples);
		
		numberOfFrames = newSamples.length /COUNT_IN_FRAME;
		
		double[][] frames = new double[numberOfFrames][COUNT_IN_FRAME];
		
		int from = 0;
		int to = COUNT_IN_FRAME;
		
		for(int i = 0; i< numberOfFrames; i++){
			double[] frameOfSamples = Arrays.copyOfRange(samples, from, to);
			for(int j = 0; j<COUNT_IN_FRAME; j++){
				frames[i][j] = frameOfSamples[j];
			}
			from = to - COUNT_IN_SHIFT;
			to = from + COUNT_IN_FRAME;
		}
		
		return frames;
	}

	private double[] padIfNessessary(double[] samples) {
		int remainder = samples.length % COUNT_IN_FRAME;
		
		if(remainder != 0){
			int amountToPad = COUNT_IN_FRAME - remainder;
			double[] newSamples = Arrays.copyOf(samples, samples.length + amountToPad);
			
			for(int i = samples.length -1; i<newSamples.length; i++){
				newSamples[i] = 0;
			}
			return newSamples;
		}
		else{
			return samples;
		}
	}
}
