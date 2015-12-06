package com.peteroconnor.fyp.SpeakerAuthentication;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class WavProcessor {
	private static final double MAX_16_BIT = Short.MAX_VALUE;     // 32,767
	private float sampleRate = 44100;
	
	public double[] read(String filename) {
		
        byte[] dataWithHeader = readByte(filename);
        byte[] data = removeHeader(dataWithHeader);
        int N = data.length;
        double[] d = new double[N/2];
        for (int i = 0; i < N/2; i++) {
            d[i] = ((short) (((data[2*i+1] & 0xFF) << 8) + (data[2*i] & 0xFF))) / ((double) MAX_16_BIT);
//            System.out.println(d[i]);
            
        }
        
        return d;
    }
	
	private byte[] removeHeader(byte[] data) {
		byte[] newData = new byte[data.length - 44];
		newData = Arrays.copyOfRange(data, 44, data.length);
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
