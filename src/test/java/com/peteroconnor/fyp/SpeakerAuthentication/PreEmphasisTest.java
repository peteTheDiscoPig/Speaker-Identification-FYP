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
		double[] expecteds = new double[]{0.0, 1.05, 1.1, 1.1500000000000004, 1.2000000000000002, 1.25, 1.3000000000000007, 1.3500000000000005, 1.4000000000000004};
		
		//fail("Not yet implemented");
		for(int i = 0; i < actuals.length; i++){
			assertEquals(actuals[i], expecteds[i], DELTA);
		}
		
		
	}

}
