package com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.Mel;

public class MelFilter extends MelConversions{
	private final double minimumMel;
	private final double centerMel;
	private final double maximumMel;
	private final double vMax;

	public MelFilter(double minMel, double centMel, double maxMel) {
		minimumMel = minMel;
		centerMel = centMel;
		maximumMel = maxMel;
		vMax = 2 / (melToFrequency(maxMel) - melToFrequency(minMel));
	}

	
	public double filter(double[] mels, double[] intensities) {
		double intensity = 0;

		int l = mels.length;

		for (int i = 0; i < l; i++) {
			
			double v = mels[i];

			if (v < minimumMel)
				continue;
			if (v > maximumMel)
				break;

			double k = (v <= centerMel) ? (centerMel - v) / (centerMel - minimumMel)
					: 1 - ((v - centerMel) / (maximumMel - centerMel));

			intensity += intensities[i] * k * vMax;
		}

		return intensity;
	}

}
