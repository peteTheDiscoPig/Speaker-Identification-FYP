package com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.Mel;

public abstract class MelConversions {
	
	/**
	 * @param frequency. The input frequency
	 * @return The mel of the inputed frequency
	 */
	public double frequencyToMel(double frequency) {
		double mel;
		// mel = 2595 * Math.log10(1+frequency/700);
		mel = 1127.01048 * Math.log(1 + (frequency / 700));
		return mel;
	}

	/**
	 * @param mel
	 * @return The frequency of the inputed mel
	 */
	public double melToFrequency(double mel) {
		double frequency;
		// frequency = 700 * ((Math.pow(10, mel/2595))-1);// not working
		frequency = 700 * (Math.exp(mel / 1127.01048) - 1);
		return frequency;
	}

}
