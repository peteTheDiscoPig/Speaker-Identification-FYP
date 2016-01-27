package com.peteroconnor.fyp.SpeakerAuthentication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import com.peteroconnor.fyp.SpeakerAuthentication.Entity.AudioData;
import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.Framer;
import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.MFCC;
import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.WavProcessor;
import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.Windower;
import com.peteroconnor.fyp.SpeakerAuthentication.GUI.RegisterWindow;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;

public class App implements ActionListener
{
	private JFrame frame;
	
    public static void main( String[] args )
    {
//    	VoiceCapture vc = new VoiceCapture();
//    	vc.capture();
//    	
    	MFCC mfcc = new MFCC();
    	mfcc.preformFeatureExtraction();
    	
    	
    	EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    App frame = new App();
                    //App.setVisible(true);
                    //window.frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
    public App(){
    	initialize();
    }
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 1227, 939);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAutomaticSpeakerVerification = new JLabel("Automatic Speaker Verification");
		lblAutomaticSpeakerVerification.setBounds(0, 0, 1205, 37);
		lblAutomaticSpeakerVerification.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblAutomaticSpeakerVerification.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblAutomaticSpeakerVerification);
		
		Button button = new Button("Register");
		button.addActionListener(this);
		
		button.setFont(new Font("Dialog", Font.PLAIN, 30));
		button.setBounds(458, 274, 200, 50);
		frame.getContentPane().add(button);
	}
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("Register")){
			RegisterWindow registerWindow = new RegisterWindow();
			registerWindow.setVisible(true);
			frame.setVisible(false);
		}
		
		
	}
	
	
}
