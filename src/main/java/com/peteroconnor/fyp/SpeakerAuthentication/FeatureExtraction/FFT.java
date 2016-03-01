package com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

public class FFT{
	private final DftNormalization STANDARD_NORMAILZATION_CONVENTION = DftNormalization.STANDARD;
	
	FastFourierTransformer fft = new FastFourierTransformer(STANDARD_NORMAILZATION_CONVENTION);
	
	public double[][] doFFT(double[][] frames){
		int numberOfFrames = frames.length;
		int samplesInFrame = frames[0].length;
		double[][] newFrames = new double[numberOfFrames][samplesInFrame];
		for(int i = 0; i<numberOfFrames; i++){
			newFrames[i] = doFFTonFrame(frames[i]);
		}
		
		return newFrames;
	}
	
	private double[] doFFTonFrame(double[] frame){
		Complex[] complex = getComplex(frame);
		double[] absolutes = getAllAbsolutes(complex);
		return absolutes;
	}
	
	private double[] getAllAbsolutes(Complex[] complex) {
		int length = complex.length/2 + 1; //remove mirror in second half of fft
		double[] absolutes = new double[length];
		for(int i = 0; i < length; i++){
			absolutes[i] = getAbsoluteValue(complex[i]);
		}
		return absolutes;
	}

	private double getAbsoluteValue(Complex complex){
		double real = complex.getReal();
		double imaginary = complex.getImaginary();
		//should this be squared?
		double absoluteValue = Math.sqrt((real * real) + (imaginary * imaginary));
		return absoluteValue;
	}
	
	private Complex[] getComplex(double[] frame){
		Complex[] frameComplex =  fft.transform(frame, TransformType.FORWARD);
		return frameComplex;
	}

}
