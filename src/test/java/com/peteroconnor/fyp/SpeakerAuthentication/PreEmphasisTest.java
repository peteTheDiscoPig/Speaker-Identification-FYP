package com.peteroconnor.fyp.SpeakerAuthentication;

import static org.junit.Assert.*;

import org.junit.Test;

import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.PreEmphasis;

public class PreEmphasisTest {
	private static final double DELTA = 1e-15;
	@Test
	public void test() {
		PreEmphasis pe = new PreEmphasis();
		double[] data = new double[]{1,2,3,4,5,6,7,8,9};
		double[] actuals = pe.preemphasise(data);
		double[] expecteds = new double[]{0.0, 1.05, 1.1, 1.15, 1.2, 1.25, 1.3, 1.35, 1.4};
		
		//fail("Not yet implemented");
		for(int i = 0; i < actuals.length; i++){
			assertEquals(actuals[i], expecteds[i], DELTA);
		}
	}

}
