package com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction;

public class Windower {
	//Y(n) = X(n) * W(n)
	//W(n) = 0.54 – 0.46 cos [2 pi * n / N – 1]
	
	public double[][] applyHammingWindow(double[][] frames){
		int numberOfFrames = frames.length;
		int samplesInFrame = frames[0].length;
		double[][] newFrames = new double[numberOfFrames][samplesInFrame];
		for(int i = 0; i<numberOfFrames; i++){
			newFrames[i] = applyHammingToFrame(frames[i]);
		}
		
		return newFrames;
	}
	
	private double[] applyHammingToFrame(double[] frame){
		int frameLength = frame.length;
		double[] result = new double[frameLength];
		
		for(int i=0; i<frame.length; i++){
			result[i] = applyHammingFunction(i, frameLength, frame[i]);
		}
		
		
		return result;
	}

	private double applyHammingFunction(int position, int frameLength, double originalValue) {
		//W(n) = 0.54 – 0.46 cos [2 pi * n / N – 1]
		double hammingValue = 0.54f - 0.46f * Math.cos((Math.PI * 2 * position)/frameLength - 1);
		//Y(n) = X(n) * W(n)
		double newValue = originalValue * hammingValue;
		return newValue;
	}
}
