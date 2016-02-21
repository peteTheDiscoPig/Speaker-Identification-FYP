package com.peteroconnor.fyp.SpeakerAuthentication.GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.peteroconnor.fyp.SpeakerAuthentication.Playback;
import com.peteroconnor.fyp.SpeakerAuthentication.VoiceCapture;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.User;
import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.MFCC;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class RegisterRecord extends JFrame implements ActionListener{

//	private JFrame frmRegistrationVoice;
	private JButton btnRecord, btnPlayback, btnBack, btnContinue;
	private JLabel lblRecording;
	public JLabel lblFinished;
	static RegisterWindow rw;
	private User user;
	private VoiceCapture vc;
	private boolean isRecording = false;
	private Playback playback;
	private MFCC mfcc;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RegisterRecord window = new RegisterRecord(rw);
//					window.frmRegistrationVoice.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public RegisterRecord(RegisterWindow rw, User user) {
		this.rw = rw;
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setTitle("Registration - Voice Record");
		setSize(700, 700);
//		frame.setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		playback = new Playback();
		mfcc = new MFCC();
		
		btnRecord = new JButton("Record");
		btnRecord.setBounds(80, 218, 100, 30);
		getContentPane().add(btnRecord);
		btnRecord.addActionListener(this);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(207, 218, 100, 30);
		getContentPane().add(btnBack);
		btnBack.addActionListener(this);
		
		btnPlayback = new JButton("Playback");
		btnPlayback.setBounds(336, 218, 100, 30);
		getContentPane().add(btnPlayback);
		btnPlayback.addActionListener(this);
		btnPlayback.setEnabled(false);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(80, 70, 200, 30);
		getContentPane().add(lblName);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(80, 110, 200, 30);
		getContentPane().add(lblEmail);
		
		JLabel lblPhone = new JLabel("Phone No:");
		lblPhone.setBounds(80, 156, 200, 30);
		getContentPane().add(lblPhone);
		
		JLabel lblNameVal = new JLabel(user.getName());
		lblNameVal.setBounds(295, 70, 200, 30);
		getContentPane().add(lblNameVal);
		
		JLabel lblEmailVal = new JLabel(user.getEmail());
		lblEmailVal.setBounds(295, 110, 200, 30);
		getContentPane().add(lblEmailVal);
		
		JLabel lblPhoneVal = new JLabel(user.getPhoneNumber());
		lblPhoneVal.setBounds(295, 156, 200, 30);
		getContentPane().add(lblPhoneVal);
		
		setLblRecording(new JLabel("Recording"));
		getLblRecording().setForeground(new Color(0, 128, 0));
		getLblRecording().setFont(new Font("Tahoma", Font.PLAIN, 22));
		getLblRecording().setBounds(45, 348, 123, 30);
		getContentPane().add(getLblRecording());
		getLblRecording().setVisible(false);
		
		
		
		lblFinished = new JLabel("Finished");
		lblFinished.setForeground(new Color(255, 0, 0));
		lblFinished.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblFinished.setBounds(45, 288, 123, 30);
		getContentPane().add(lblFinished);
		
		btnContinue = new JButton("Continue");
		btnContinue.setBounds(461, 219, 100, 30);
		getContentPane().add(btnContinue);
		btnContinue.addActionListener(this);
		btnContinue.setEnabled(false);
		
		lblFinished.setVisible(false);
		
		
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBack){
       	 rw.setVisible(true);
       	 setVisible(false);
       	 dispose();
        }
		else if(e.getSource() == btnRecord){
			
			
			vc = new VoiceCapture();
//			setRecording(true);
			
	    	vc.capture();
	    	setRecording(false);
	    	btnContinue.setEnabled(true);
		}
		else if(e.getSource() == btnPlayback){
			playback.playVoice();
		}
		else if(e.getSource() == btnContinue){
			mfcc.preformFeatureExtraction();
		}
		
	}

	/**
	 * @return the lblRecording
	 */
	public JLabel getLblRecording() {
		return lblRecording;
	}

	/**
	 * @param lblRecording the lblRecording to set
	 */
	public void setLblRecording(JLabel lblRecording) {
		this.lblRecording = lblRecording;
	}

	public void setRecording(boolean recording) {
		if(recording){
			lblRecording.setVisible(true);
			lblFinished.setVisible(false);
			
		}
		else{
			lblRecording.setVisible(false);
			lblFinished.setVisible(true);
			btnPlayback.setEnabled(true);
			btnRecord.setText("Re-record");
		}
		
		
	}
	
}
