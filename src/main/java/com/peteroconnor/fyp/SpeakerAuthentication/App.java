package com.peteroconnor.fyp.SpeakerAuthentication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import com.peteroconnor.fyp.SpeakerAuthentication.Entity.AudioData;
import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.Framer;
import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.MFCC;
import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.PreEmphasis;
import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.WavProcessor;
import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.Windower;
import com.peteroconnor.fyp.SpeakerAuthentication.GUI.LoginWindow;
import com.peteroconnor.fyp.SpeakerAuthentication.GUI.RegisterWindow;
import com.peteroconnor.fyp.SpeakerAuthentication.PhraseGen.PhraseGen;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.Button;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class App extends JFrame implements ActionListener
{
	//private JFrame frame;
	
    public static void main( String[] args )
    {
//    	VoiceCapture vc = new VoiceCapture();
//    	vc.capture();
    	
    	
    	MFCC mfcc = new MFCC();
    	mfcc.preformFeatureExtraction();
//    	PhraseGen p = new PhraseGen();
//    	System.out.println(p.generatePhrase());
//    	System.out.println(p.generatePhrase());
//    	System.out.println(p.generatePhrase());
//    	System.out.println(p.generatePhrase());
//    	System.out.println(p.generatePhrase());
    	
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
		//frame = new JFrame();
		setVisible(true);
		setSize(700, 700);
//		setBounds(100, 100, 1227, 939);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblAutomaticSpeakerVerification = new JLabel("Automatic Speaker Verification");
		lblAutomaticSpeakerVerification.setBounds(0, 0, 678, 37);
		lblAutomaticSpeakerVerification.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblAutomaticSpeakerVerification.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblAutomaticSpeakerVerification);
		
		Button registerButton = new Button("Register");
		registerButton.addActionListener(this);
		
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerButton.setBounds(290, 444, 100, 30);
		getContentPane().add(registerButton);
		
		Button loginButton = new Button("Login");
		loginButton.addActionListener(this);
		
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		loginButton.setBounds(290, 506, 100, 30);
		getContentPane().add(loginButton);
		
		JLabel imgLbl = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/wavImg.png")).getImage();
		imgLbl.setIcon(new ImageIcon(img));
		imgLbl.setBounds(31, 43, 609, 340);
		getContentPane().add(imgLbl);
//		frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
	}
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("Register")){
			RegisterWindow registerWindow = new RegisterWindow(this);
			registerWindow.setVisible(true);
			setVisible(false);
		}
		
		if(action.equals("Login")){
			LoginWindow loginWindow = new LoginWindow();
			loginWindow.setVisible(true);
			setVisible(false);
		}
		
		
	}
}
