package com.peteroconnor.fyp.SpeakerAuthentication;

import javax.sound.sampled.TargetDataLine;

import com.peteroconnor.fyp.SpeakerAuthentication.GUI.RegisterRecord;

public class MyTimer {
	private long timeInMills;
	private TargetDataLine line;
	
	public void startTimer(long timeInMills, TargetDataLine line){
		this.timeInMills = timeInMills;
		this.line = line;
		voiceInTimer.start();
	}
	
	Thread voiceInTimer = new Thread(new Runnable() {
		
		public void run() {
			
			try {
				Thread.sleep(timeInMills);
			} catch (Exception e) {
				// TODO: handle exception
			}
			endRecording();
		}

		private void endRecording() {
			line.stop();
	        line.close();
	        System.out.println("Finished");
		}
	});

}
