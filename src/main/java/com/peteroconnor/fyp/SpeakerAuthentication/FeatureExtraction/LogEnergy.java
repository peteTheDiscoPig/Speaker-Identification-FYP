package com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction;

public class LogEnergy {
	
	public double[][] logFrames(double[][] frames){
		int length = frames.length;
		int frameLength = frames[0].length;
		double[][] loggedFrames = new double[length][frameLength];
		
		for(int i = 0; i < length; i++){
			loggedFrames[i] = logFrame(frames[i]);
		}
		
		return loggedFrames;
	}
	
	private double[] logFrame(double[] frame){
		int length = frame.length;
		double[] logedFrame = new double[length];
		for(int i = 0; i < length; i++){
			logedFrame[i] = Math.log(frame[i]);
		}
		return logedFrame;
	}
}
