package com.peteroconnor.fyp.SpeakerAuthentication.GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;

import com.peteroconnor.fyp.SpeakerAuthentication.Playback;
import com.peteroconnor.fyp.SpeakerAuthentication.VoiceCapture;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.AudioData;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.User;
import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.MFCC;
import com.peteroconnor.fyp.SpeakerAuthentication.speechRecognition.SpeechRecognition;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class RegisterRecord extends JFrame implements ActionListener{

//	private JFrame frmRegistrationVoice;
	private JButton btnRecord, btnPlayback, btnBack, btnContinue;
	static RegisterWindow rw;
	private User user;
	private VoiceCapture vc;
	private boolean isRecording = false;
	private Playback playback;
	private MFCC mfcc;
	private String prompt = "the quick brown fox jumped over the lazy dog";
	private File file;
	private SpeechRecognition speechRecognition;
	private JLabel lblPhraseMatch;
	

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
		
		file = new File(AudioData.VOICE_FILE_LOCATION);
		speechRecognition = new SpeechRecognition();
		
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
		
		btnContinue = new JButton("Continue");
		btnContinue.setBounds(461, 219, 100, 30);
		getContentPane().add(btnContinue);
		btnContinue.addActionListener(this);
		btnContinue.setEnabled(false);
		
		JLabel lblPrompt = new JLabel(prompt);
		lblPrompt.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPrompt.setBounds(80, 349, 481, 30);
		getContentPane().add(lblPrompt);
		
		JLabel lblPromptHeader = new JLabel("Please say the following:");
		lblPromptHeader.setBounds(80, 313, 200, 20);
		getContentPane().add(lblPromptHeader);
		
		lblPhraseMatch = new JLabel("Phrase Match");
		lblPhraseMatch.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPhraseMatch.setForeground(new Color(46, 139, 87));
		lblPhraseMatch.setBounds(80, 395, 145, 30);
		getContentPane().add(lblPhraseMatch);
		lblPhraseMatch.setVisible(false);
		
		
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBack){
       	 rw.setVisible(true);
       	 setVisible(false);
       	 dispose();
        }
		else if(e.getSource() == btnRecord){
			btnContinue.setEnabled(false);
	    	btnPlayback.setEnabled(false);
			vc = new VoiceCapture();//don't move this
	    	vc.capture();
	    	checkSpokenPhrase();
		}
		else if(e.getSource() == btnPlayback){
			playback.playVoice();
		}
		else if(e.getSource() == btnContinue){
			mfcc.preformFeatureExtraction();
		}
		
	}

	private void checkSpokenPhrase() {
		speechRecognition.transcriptUsingHTTP(file);
		if(speechRecognition.isPhraseMatch(prompt)){
			btnContinue.setEnabled(true);
	    	btnPlayback.setEnabled(true);
	    	lblPhraseMatch.setVisible(true);
		}
		else{
			String whatWasSaid = speechRecognition.getSpokenPhrase();
			String message = "You did not say the correct phrase. You said:\n" + whatWasSaid + "\nPlease re-record to continue";
			JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	
}
