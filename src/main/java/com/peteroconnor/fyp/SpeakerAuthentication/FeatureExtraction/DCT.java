package com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction;

import org.apache.commons.math3.transform.DctNormalization;
import org.apache.commons.math3.transform.FastCosineTransformer;
import org.apache.commons.math3.transform.TransformType;

public class DCT {
	private FastCosineTransformer fct;
	private final DctNormalization NORMALISATION = DctNormalization.STANDARD_DCT_I;
	private final TransformType TRANSFORM_TYPE = TransformType.FORWARD;
	
	public DCT(){
		fct = new FastCosineTransformer(NORMALISATION);
	}
	
	public double[][] transformFrames(double[][] frames){
		double[][] transformedFrames = new double[frames.length][frames[0].length];
		for (int i = 0; i < transformedFrames.length; i++){
			transformedFrames[i] = transform(frames[i]);
		}
		return transformedFrames;
	}
	
	private double[] transform(double[] frame){
		double[] transformedFrame = fct.transform(frame, TRANSFORM_TYPE);
		return transformedFrame;
	}
	
	
}
