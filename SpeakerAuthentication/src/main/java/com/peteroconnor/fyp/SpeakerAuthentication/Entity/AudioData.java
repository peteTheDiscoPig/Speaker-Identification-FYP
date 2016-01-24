package com.peteroconnor.fyp.SpeakerAuthentication.Entity;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class AudioData {
	public static final double SAMPLE_RATE_IN_KHZ = 44.1;
	public static final String VOICE_FILE_LOCATION = "C:/SpeakerAuthentication/audio/voice.wav";
	private byte[] originalBytes;
	private byte[] bytesWithoutHeader;
	private double[] amplidudes;
	private double[] preEmphasisedAmplitudes;
	private double[][] framedSignal;
	
	private double lengthInMills;
	
	public AudioData(byte[] originalBytes, byte[] bytesWithoutHeader, double[] amplidudes){
		this.originalBytes = originalBytes;
		this.bytesWithoutHeader = bytesWithoutHeader;
		this.amplidudes = amplidudes;
		lengthInMills = amplidudes.length / SAMPLE_RATE_IN_KHZ;
	}
	
	public AudioData(){
		
	}
	
	public byte[] getOriginalBytes() {
		return originalBytes;
	}

	public void setOriginalBytes(byte[] originalBytes) {
		this.originalBytes = originalBytes;
	}

	public double[] getAmplidudes() {
		return amplidudes;
	}

	public void setAmplidudes(double[] amplidudes) {
		this.amplidudes = amplidudes;
		lengthInMills = amplidudes.length / SAMPLE_RATE_IN_KHZ;
	}

	public double[] getPreEmphasisedAmplitudes() {
		return preEmphasisedAmplitudes;
	}

	public void setPreEmphasisedAmplitudes(double[] preEmphasisedAmplitudes) {
		this.preEmphasisedAmplitudes = preEmphasisedAmplitudes;
	}

	public double getLengthInMills() {
		return lengthInMills;
	}

	public void setLengthInMills(double lengthInMills) {
		this.lengthInMills = lengthInMills;
	}

	public byte[] getBytesWithoutHeader() {
		return bytesWithoutHeader;
	}

	public void setBytesWithoutHeader(byte[] bytesWithoutHeader) {
		this.bytesWithoutHeader = bytesWithoutHeader;
	}

	public double[][] getFramedSignal() {
		return framedSignal;
	}

	public void setFramedSignal(double[][] framedSignal) {
		this.framedSignal = framedSignal;
	}
	
	public void savetoFile(){
		String originalBytesString = "Bytes " + Arrays.toString(originalBytes) ;
		String amplidudesString = "Amplitudes " + Arrays.toString(amplidudes);
		String preEmphasisedAmplitudesString = "Amplitudes Array Per Emphasised " + Arrays.toString(preEmphasisedAmplitudes);
		String framedSignalString = "Framed signal ";
		
		for(int i = 0; i< framedSignal.length; i++){
			framedSignalString +=  Arrays.toString(framedSignal[i])  + System.lineSeparator();
		}
		
		saveDatafile("Bytes.txt", originalBytesString);
		saveDatafile("Amplitudes.txt", amplidudesString);
		saveDatafile("PreEmp.txt", preEmphasisedAmplitudesString);
		saveDatafile("FramedSignal.txt", framedSignalString);
	}
	
	private void saveDatafile(String fileName, String data){
		PrintWriter out = null;
		try {
			out = new PrintWriter("C:/SpeakerAuthentication/"+fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(data);
		
		out.close();
	}
	
}
