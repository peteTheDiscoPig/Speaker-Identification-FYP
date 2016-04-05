package com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction;

import java.util.Arrays;

import org.apache.commons.math3.transform.DctNormalization;
import org.apache.commons.math3.transform.FastCosineTransformer;
import org.apache.commons.math3.transform.TransformType;

public class DCT {
	//private FastCosineTransformer fct;
	//private final DctNormalization NORMALISATION = DctNormalization.ORTHOGONAL_DCT_I;//.STANDARD_DCT_I;
	//private final TransformType TRANSFORM_TYPE = TransformType.FORWARD;
	public static final int NUMBER_OF_MFCC = 15;
	
	
	
	
	//Modify take values from 1 to 13 note 0th value must be removed
	
	public DCT(){
		//fct = new FastCosineTransformer(NORMALISATION);
	}
	
	public double[][] transformFrames(double[][] frames){
		double[][] transformedFrames = new double[frames.length][NUMBER_OF_MFCC];
		for (int i = 0; i < transformedFrames.length; i++){
			//transformedFrames[i] = transform(frames[i]);
			double[] transformedFrame = transform(frames[i]);
			transformedFrames[i] = Arrays.copyOfRange(transformedFrame, 1, NUMBER_OF_MFCC +1);
		}
		return transformedFrames;
	}
	
	private double[] transform(double[] frame){
		//Modified from http://stackoverflow.com/questions/14106984/how-to-calculate-discrete-cosine-transform-dct-in-php
//		double[] transformedFrame = fct.transform(frame, TRANSFORM_TYPE);
//		System.out.println(Arrays.toString(transformedFrame));
//		return transformedFrame;
		int N = frame.length;
		double[] X = new double[N];
		
//		for (int k = 0; k < N; ++k) {
//		    double sum = 0.;
//		    double s = (k == 0) ? Math.sqrt(.5) : 1.;
//		    for (int n = 0; n < N; ++n) {
//		      sum += s * frame[n] * Math.cos(Math.PI * (n + .5) * k / N);
//		    }
//		    X[k] = sum * Math.sqrt(2. / N);
//		    System.out.println(X[k]);
//		  }
//		return X;
		
		
//		function dct1D($in){
//		    $results = array();
//		    $N = count($in);
//		    for($k = 0; $k < $N; $k++){
//		        $sum = 0;
//		        for($n = 0; $n < $N; $n++){
//		             $sum += $in[$n] * cos($k * pi() * ($n + 0.5) / ($N));
//		        }
//		        $sum *= sqrt(2 / $N);
//		        if($k == 0){
//		            $sum *= 1 / sqrt(2);
//		        }
//		        $results[$k] = $sum;
//		    }
//		    return $results;
//		}
		
		for(int i = 0; i < N; i++ ){
			double sum = 0;
			for(int j = 0; j < N; j++){
				sum += frame[j] * Math.cos(i * Math.PI * (j + 0.5) / N);
			}
			
			// scale factor removed as values too small probably due to normalization at start
//			sum *= Math.sqrt(2/N);
			
			if(i == 0){
				sum *= 1 / Math.sqrt(2);
			}
			X[i] = sum;
//			System.out.println(sum);
		}
		return X;
	}
	
	
}
