//code modified from: http://www.programcreek.com/java-api-examples/index.php?source_dir=PAV-master/libpav/src/pav/lib/frame/MelFilterBank.java
package com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.Mel;

import java.util.Arrays;

import com.peteroconnor.fyp.SpeakerAuthentication.Entity.AudioData;

public class MelFilterBank extends MelConversions{
	private final int minimumFrequency = 300; //could be 20 - lower end of hearing
	private final int maximumFrequency = 8000;
	private final int numberOfFilters = 26; //can be 26 to 40
	private final double minimumMel;
	private final double maximumMel;
	private final double hopNumber;
	private final MelFilter[] filterBanks;
	private final double sampleRate = AudioData.SAMPLE_RATE_IN_KHZ * 1000;

	public MelFilterBank() {
		filterBanks = new MelFilter[numberOfFilters];

		minimumMel = frequencyToMel(minimumFrequency);
		maximumMel = frequencyToMel(maximumFrequency);
		hopNumber = (maximumMel - minimumMel) / (numberOfFilters + 1);

		double minMel = minimumMel;
		double centerMel = minimumMel + hopNumber;
		double maxMel = centerMel + hopNumber;

		for (int i = 0; i < numberOfFilters; i++) {
			filterBanks[i] = new MelFilter(minMel, centerMel, maxMel);

			minMel += hopNumber;
			centerMel += hopNumber;
			maxMel += hopNumber;
		}

	}

	public double[][] getFilterBankResult(double[][] fullFftSpectrum) {
		int numnerOfFrames = fullFftSpectrum.length;
		int numberInFrame = fullFftSpectrum[0].length;
		double[][] result = new double[numnerOfFrames][numberInFrame];
		for (int i = 0; i < numnerOfFrames; i++) {
			result[i] = filter(fullFftSpectrum[i]);
		}
		return result;
	}

	
	private double[] filter(double[] spectrum) {
		int length = spectrum.length;
		int filterLength = filterBanks.length;
		double fDelta = (sampleRate / 2) / length;
		double fDelta2 = fDelta / 2;

		double[] mel = new double[length];
		double[] out = new double[filterLength];

		for (int i = 0; i < length; i++) {
			mel[i] = frequencyToMel(i * fDelta + fDelta2);
		}

		for (int i = 0; i < filterLength; i++) {
			out[i] = filterBanks[i].filter(mel, spectrum);
		}
//		System.out.println(Arrays.toString(out));
		return out;
	}

	
	
	
	// //equations for frequency to mel and inverse found:
	// http://rug.mnhn.fr/seewave/HTML/MAN/mel.html
	// private final int NUMBER_OF_FILTERS = 40; //can be between 26 and 40
	// private final int START_AND_END = 2;
	// private final int NUMBER_OF_POINTS = NUMBER_OF_FILTERS + START_AND_END;
	// private final double LOWER_FREQUENCY = 300; //could be 20. bottom of
	// hearing range
	// private final double UPPER_FREQUENCY = 8000;
	// private double[] startAndEndInMel = getStartAndEndInMel();
	// private int fftLength;
	//
	// private double[][] melFilters;
	// private double[][] fftIntensities;
	// private double[] melSpacedPoints;
	//
	//
	// public MelFilterBank(int fftLength, double[][] fftIntensities) {
	// this.fftLength = fftLength;
	// this.fftIntensities = fftIntensities;
	// }
	//
	// /**
	// * @param frequency. The input frequency
	// * @return The mel of the inputed frequency
	// */
	// public double frequencyToMel(double frequency){
	// double mel;
	// //mel = 2595 * Math.log10(1+frequency/700);
	// mel = 1127.01048 * Math.log(1+(frequency/700));
	// return mel;
	// }
	//
	//
	// /**
	// * @param mel
	// * @return The frequency of the inputed mel
	// */
	// public double melToFrequency(double mel){
	// double frequency;
	// //frequency = 700 * ((Math.pow(10, mel/2595))-1);// not working
	// frequency = 700 * (Math.exp(mel/1127.01048)-1);
	// return frequency;
	// }
	//
	//
	// /**
	// * @return An array containing the upper and lower frequency as mel
	// */
	// private double[] getStartAndEndInMel() {
	// double[] startEndMel = new double[2];
	// startEndMel[0] = frequencyToMel(LOWER_FREQUENCY);
	// startEndMel[1] = frequencyToMel(UPPER_FREQUENCY);
	// return startEndMel;
	// }
	//
	//
	//
	// /**
	// * @param lowerValue
	// * @param upperValue
	// * @param numberOfSteps
	// * @return The linearly spaced step size between upper and lower value
	// */
	// public double getStepSizeForLinearSpace(double lowerValue, double
	// upperValue, int numberOfSteps){
	// double stepSize;
	// stepSize = (upperValue - lowerValue) / (numberOfSteps - 1) ;
	// return stepSize;
	// }
	//
	//
	//
	// /**
	// * @return The linearly spaced step size between upper and lower mel
	// */
	// private double getLinearMelSpacing(){
	// double spacing = getStepSizeForLinearSpace(startAndEndInMel[0],
	// startAndEndInMel[1], NUMBER_OF_POINTS);
	// return spacing;
	// }
	//
	//
	// /**
	// * @return The all mel points linearly spaced between upper and lower mel
	// */
	// public double[] getMelSpacedPoints(){
	// double[] melPoints = new double[NUMBER_OF_POINTS];
	// double spacing = getLinearMelSpacing();
	// double currentValue = startAndEndInMel[0] - spacing;
	// for(int i = 0; i<NUMBER_OF_POINTS; i++){
	// currentValue += spacing;
	// melPoints[i] = currentValue;
	// }
	//
	// return melPoints;
	// }
	//
	//
	// public double[] melSpacedPointsToFrequencySpacedPoints(){
	// melSpacedPoints = getMelSpacedPoints();
	// int length = melSpacedPoints.length;
	// double[] frequencySpacedPoints = new double[length];
	// for(int i = 0; i < length; i++){
	// frequencySpacedPoints[i] = melToFrequency(melSpacedPoints[i]);
	// }
	// return frequencySpacedPoints;
	// }
	//
	// private int[] getFFTBinNumberForMel(){
	// double[] frequencies = melSpacedPointsToFrequencySpacedPoints();
	// int length = frequencies.length;
	// int[] bins = new int[length];
	//
	// for(int i = 0; i < length; i++){
	// bins[i] = getBinNumber(frequencies[i]);
	// }
	//// System.out.println(Arrays.toString(bins));
	// return bins;
	// }
	//
	// private int getBinNumber(double frequency){
	// int binNumber = (int) Math.round(fftLength * frequency /
	// (AudioData.SAMPLE_RATE_IN_KHZ *1000));
	// return binNumber;
	// }
	//
	// public double[][] getIntensitiesInAllFrames(){
	// getFFTBinNumberForMel();
	// double[][] intensitiesInFrames = new
	// double[fftIntensities.length][fftIntensities[1].length];
	// for(int i = 0; i < intensitiesInFrames.length; i++){
	// intensitiesInFrames[i] = getIntensityInFilters(fftIntensities[i]);
	// }
	// return intensitiesInFrames;
	// }
	//
	// private double[] getIntensityInFilters(double [] intensitiesInFrame){
	// double[] filterIntensities = new double[NUMBER_OF_FILTERS];
	//
	// for(int i = 0; i < NUMBER_OF_FILTERS; i++){
	// int min = i, center = i + 1, max = i + 2;
	// filterIntensities[i] = getIntensityInFilter(min, center, max,
	// intensitiesInFrame);
	// }
	//// System.out.println(Arrays.toString(filterIntensities));
	// return filterIntensities;
	// }
	//
	// private double getIntensityInFilter(int min, int center, int max,
	// double[] intensities){
	//// code modified from:
	// http://www.programcreek.com/java-api-examples/index.php?source_dir=PAV-master/libpav/src/pav/lib/frame/MelFilterBank.java
	//
	// double intensity = 0;
	// double melMin = melSpacedPoints[min];
	// double melCenter = melSpacedPoints[center];
	// double melMax = melSpacedPoints[max];
	// double vMax = 2 / (melToFrequency(melMax)- melToFrequency(melMin));
	//
	// double[] melFilter = Arrays.copyOfRange(melSpacedPoints, min, max + 1);
	// int length = melFilter.length;
	//
	// for(int i = 0; i < length; i++){
	// double v = melFilter[i];
	// double k;
	//
	// if(v <= melCenter){
	// k = (melCenter - v)/ (melCenter - melMax);
	// }
	// else{
	// k = 1 - ((v - melCenter)/ (melMax - melCenter));
	// }
	//
	// intensity += intensities[i] * k * vMax;
	// }
	// return intensity;
	// }
	//
	//

}
