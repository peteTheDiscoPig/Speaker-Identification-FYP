package com.peteroconnor.fyp.SpeakerAuthentication;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.BeforeClass;
import org.junit.Test;

import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.DCT;

public class DCTTest {
	private static final double DELTA = 1e-3;
	public static DCT dct;
	
	@BeforeClass
    public static void beforeClass(){
		dct = new DCT();
	}

	@Test
	public void test() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		double[] input =  new double[]{20, 60, 10, -90, 80, 120, 20, 115};
		double[] expecteds = new double[]{670.0000, -308.3878, 229.6567, 231.0802,
				-120.2082, -509.6407, 203.3661, 69.0309};
		Method method = DCT.class.getDeclaredMethod("transform", double[].class);
		method.setAccessible(true);
		double[] actuals = (double[]) method.invoke(dct, input);
		
		System.out.println(expecteds[1]/2 + " $ " + actuals[1]);
		assertEquals(expecteds[1]/2, actuals[1], DELTA);
	}

}
