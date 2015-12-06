package com.peteroconnor.fyp.SpeakerAuthentication;

public class PreEmphasis {
	//y(n) = s(n) - as(n - 1)
	private final double PRE_EMPHASIS_FACTOR = 0.95;
	
	public double[] preemphasise(double[] amplitudeArray){
		double[] perEmphasisedAmplitudeArray = new double[amplitudeArray.length];
		for(int i = 1; i<amplitudeArray.length; i++){
			perEmphasisedAmplitudeArray[i] = amplitudeArray[i] - (PRE_EMPHASIS_FACTOR * amplitudeArray[i-1]);
		}
		return perEmphasisedAmplitudeArray;
	}
	
}
