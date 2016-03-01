package com.peteroconnor.fyp.SpeakerAuthentication;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.BeforeClass;
import org.junit.Test;

import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.WavProcessor;

public class WavProcessorTest {
	public static WavProcessor wp;
	
	@BeforeClass
    public static void beforeClass() {
		wp = new WavProcessor();
    }
 
	
	@Test
	public void testZeroAlignment() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		double[] input = new double[]{0,0,0,0,0,0,0,0,0,1};
		Method method = WavProcessor.class.getDeclaredMethod("zeroAlignment", double[].class);
		method.setAccessible(true);
		double[] actuals = (double[]) method.invoke(wp, input);
		int expectedLength = 1;
		assertEquals(actuals.length, expectedLength);
	}

}
