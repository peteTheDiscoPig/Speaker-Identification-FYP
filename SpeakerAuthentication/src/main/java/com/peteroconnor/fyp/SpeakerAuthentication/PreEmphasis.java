package com.peteroconnor.fyp.SpeakerAuthentication;

public class PreEmphasis {
	final double pef = 0.95;
	//y(n) = s(n) - as(n - 1)
	
	//Preemphasizer pe = new Preemphasizer(0.95);
	public double[] preemphasise(double[] soundAsDouble){
		double[] ped = new double[soundAsDouble.length];
		System.out.println("Start PE________________________________________________");
		for(int i = 1; i<soundAsDouble.length; i++){
			ped[i] = soundAsDouble[i] - (pef * soundAsDouble[i-1]);
			
			//System.out.println(ped[i]);
		}
		
		return ped;
	}
	
}
