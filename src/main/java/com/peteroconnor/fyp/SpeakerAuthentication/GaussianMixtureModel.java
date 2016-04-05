package com.peteroconnor.fyp.SpeakerAuthentication;

import java.util.Arrays;

import org.apache.commons.math3.distribution.MixtureMultivariateNormalDistribution;
import org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization;
import org.openimaj.math.statistics.distribution.MixtureOfGaussians;
import org.openimaj.ml.gmm.GaussianMixtureModelEM;

public class GaussianMixtureModel {

	double[][] mfcc;
	private MixtureOfGaussians mixture;
	
	public GaussianMixtureModel(double[][] mfcc){
		this.mfcc = mfcc;
		GaussianMixtureModelEM gmm = new GaussianMixtureModelEM(32, GaussianMixtureModelEM.CovarianceType.Full);
		mixture = gmm.estimate(mfcc);
		boolean converged = gmm.hasConverged();
		System.out.println("Converged: " + converged);
//		System.out.println("gmm: " + mixture);
//		
//		double[] logProb = mixture.estimateLogProbability(mfcc);
//		
//		System.out.println("log prob: " + Arrays.toString(logProb));
		

	}
	
	public double[] compare(double[][] dbMfcc){
		return mixture.estimateLogProbability(dbMfcc);
	}
	
	public double compare(double[] frame){
		return mixture.estimateLogProbability(frame);
	}
	
	

}
