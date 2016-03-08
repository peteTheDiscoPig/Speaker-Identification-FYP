package com.peteroconnor.fyp.SpeakerAuthentication;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.math3.complex.Complex;
import org.junit.BeforeClass;
import org.junit.Test;

import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.FFT;

public class FFTTest {
	private static final double DELTA = 1e-15;
	public static FFT fft;
	
	@BeforeClass
    public static void beforeClass(){
		fft = new FFT();
	}
	
	
	@Test
	public void getComplexTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		double[] input = new double[]{1, 2, 3, 4};
		double[] expectedReal = new double[]{10, -2, -2, -2};
		double[] expectedImg = new double[]{0, 2, 0, -2};
		Method method = FFT.class.getDeclaredMethod("getComplex", double[].class);
		method.setAccessible(true);
		Complex[] actuals = (Complex[]) method.invoke(fft, input);
		
		assertEquals(actuals[0].getReal(), expectedReal[0], DELTA);
		assertEquals(actuals[1].getReal(), expectedReal[1], DELTA);
		assertEquals(actuals[2].getReal(), expectedReal[2], DELTA);
		assertEquals(actuals[3].getReal(), expectedReal[3], DELTA);
		
		assertEquals(actuals[0].getImaginary(), expectedImg[0], DELTA);
		assertEquals(actuals[1].getImaginary(), expectedImg[1], DELTA);
		assertEquals(actuals[2].getImaginary(), expectedImg[2], DELTA);
		assertEquals(actuals[3].getImaginary(), expectedImg[3], DELTA);
	}

}
