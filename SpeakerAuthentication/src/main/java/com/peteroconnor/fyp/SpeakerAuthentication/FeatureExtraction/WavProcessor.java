package com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.peteroconnor.fyp.SpeakerAuthentication.Entity.AudioData;

public class WavProcessor {
	//Reference http://algs4.cs.princeton.edu/11model/StdAudio.java
	private static final double MAX_16_BIT = Short.MAX_VALUE;     // 32,767
	private static final int HEADER_SIZE = 8500;//80 for just header
	private static final double NOISE_GATE_VALUE = 0.02;
	private float sampleRate = 16000;
	
	
	public AudioData bytesToAmplitude(String filename) {
		
        byte[] dataWithHeader = readByte(filename);
        byte[] data = removeHeader(dataWithHeader);
        int N = data.length;
        double[] amplitudes = new double[N/2]; //
        for (int i = 0; i < N/2; i++) {
        	amplitudes[i] = ((short) (((data[2*i+1] & 0xFF) << 8) + (data[2*i] & 0xFF))) / ((double) MAX_16_BIT);
        }
        amplitudes = applyNoiseGate(amplitudes);
        amplitudes = zeroAlignment(amplitudes);
        
        AudioData audioData = new AudioData(dataWithHeader, data, amplitudes);
        preEmphasise(audioData);
        return audioData;
    }
	
	private double[] zeroAlignment(double[] amplitudes) {
		boolean isZero = true;
		int countZeros = 0;
		while(isZero){
			if(amplitudes[countZeros] != 0){
				isZero = false;
			}
			else{
				countZeros ++;
			}
		}
		double[] zeroAlignedAmps = new double[amplitudes.length - countZeros];
		zeroAlignedAmps = Arrays.copyOfRange(amplitudes, countZeros, amplitudes.length);
		return zeroAlignedAmps;
	}

	private double[] applyNoiseGate(double[] amplitudes) {
		for(int i = 0; i < amplitudes.length; i++){
			if(amplitudes[i]<NOISE_GATE_VALUE && amplitudes[i]> -NOISE_GATE_VALUE){
				amplitudes[i] = 0;
			}
		}
		return amplitudes;
	}

	private void preEmphasise(AudioData audioData) {
		PreEmphasis preEmphasis = new PreEmphasis();
		double[] preEmphasisedAmplitudes = preEmphasis.preemphasise(audioData.getAmplidudes());
		audioData.setPreEmphasisedAmplitudes(preEmphasisedAmplitudes);
	}

	private byte[] removeHeader(byte[] data) {
		byte[] newData = new byte[data.length - HEADER_SIZE];
		newData = Arrays.copyOfRange(data, HEADER_SIZE, data.length);
		return newData;
	}

	private static byte[] readByte(String filename) {
        byte[] data = null;
        AudioInputStream ais = null;
        try {

            // try to read from file
            File file = new File(filename);
            
            ais = AudioSystem.getAudioInputStream(file);
            data = new byte[ais.available()];
            ais.read(data);
            
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Could not read " + filename);
        }

        catch (UnsupportedAudioFileException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(filename + " in unsupported audio format");
        }
        
        return data;
    }

}
