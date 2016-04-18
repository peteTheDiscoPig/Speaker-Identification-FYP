package com.peteroconnor.fyp.SpeakerAuthentication.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.peteroconnor.fyp.SpeakerAuthentication.App;
import com.peteroconnor.fyp.SpeakerAuthentication.Playback;
import com.peteroconnor.fyp.SpeakerAuthentication.VoiceCapture;
import com.peteroconnor.fyp.SpeakerAuthentication.DB.DBController;
import com.peteroconnor.fyp.SpeakerAuthentication.DB.MetricDAOImpl;
import com.peteroconnor.fyp.SpeakerAuthentication.DB.UserDAOImpl;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.AudioData;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.Metric;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.User;
import com.peteroconnor.fyp.SpeakerAuthentication.FeatureExtraction.MFCC;
import com.peteroconnor.fyp.SpeakerAuthentication.GMM.GaussianMixtureModel;
import com.peteroconnor.fyp.SpeakerAuthentication.GMM.Identifer;
import com.peteroconnor.fyp.SpeakerAuthentication.PhraseGen.PhraseGen;
import com.peteroconnor.fyp.SpeakerAuthentication.speechRecognition.SpeechRecognition;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.stream.DoubleStream;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class LoginWindow extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblPrompt;
	private JButton btnNewPhrase;
	private JButton btnRecord, btnPlayback, btnBack, btnContinue;
	private PhraseGen phraseGen;
	private String phrase = "don't count your chickens before the eggs have hatched";//"the quick brown fox jumped over the lazy dog";//"If you see this uncomment generatePhrase()";
	private final String htmlTag1 = "<html><p style='width: 500px'>";
	private final String htmlTag2 = "</p></html>";
	private Playback playback;
	private MFCC mfcc;
	private File file;
	private SpeechRecognition speechRecognition;
	static App app;
	private VoiceCapture vc;
	private User bestMatch;
	private JButton btnIncorrect, btnCorrect;
	private JLabel lblMetrics;

	/**
	 * Create the frame.
	 */
	public LoginWindow(App app) {
		this.app = app;
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 700);

		playback = new Playback();
		mfcc = new MFCC();

		file = new File(AudioData.VOICE_FILE_LOCATION);
		speechRecognition = new SpeechRecognition();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblPromptHead = new JLabel("Please say the following:");
		lblPromptHead.setBounds(71, 52, 200, 20);
		contentPane.add(lblPromptHead);

		// dissable when editing LoginWindow GUI
		phraseGen = new PhraseGen();
		phrase = phraseGen.generatePhrase();

		lblPrompt = new JLabel(htmlTag1 + phrase + htmlTag2);
		lblPrompt.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPrompt.setBounds(71, 88, 592, 104);
		contentPane.add(lblPrompt);

		btnNewPhrase = new JButton("New Phrase");
		btnNewPhrase.setBounds(70, 194, 115, 29);
		contentPane.add(btnNewPhrase);

		btnRecord = new JButton("Record");
		btnRecord.setBounds(71, 337, 100, 30);
		contentPane.add(btnRecord);

		btnBack = new JButton("Back");
		btnBack.setBounds(198, 337, 100, 30);
		contentPane.add(btnBack);

		btnPlayback = new JButton("Playback");
		btnPlayback.setEnabled(false);
		btnPlayback.setBounds(327, 337, 100, 30);
		contentPane.add(btnPlayback);

		btnContinue = new JButton("Continue");
		btnContinue.setEnabled(false);
		btnContinue.setBounds(452, 338, 100, 30);
		contentPane.add(btnContinue);
		
		btnCorrect = new JButton("Correct");
		btnCorrect.setBounds(71, 599, 115, 29);
		contentPane.add(btnCorrect);
		
		btnIncorrect = new JButton("Incorrect");
		btnIncorrect.setBounds(198, 599, 115, 29);
		contentPane.add(btnIncorrect);
		
		lblMetrics = new JLabel("Metrics");
		lblMetrics.setBounds(161, 551, 69, 20);
		contentPane.add(lblMetrics);
		
		btnNewPhrase.addActionListener(this);
		btnRecord.addActionListener(this);
		btnBack.addActionListener(this);
		btnPlayback.addActionListener(this);
		btnContinue.addActionListener(this);
		btnCorrect.addActionListener(this);
		btnIncorrect.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewPhrase) {
			phrase = phraseGen.generatePhrase();
			lblPrompt.setText(htmlTag1 + phrase + htmlTag2);
		} else if (e.getSource() == btnBack) {
			app.setVisible(true);
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
			double[][] mfccs = mfcc.preformFeatureExtraction();
			
			Identifer identifer = new Identifer(mfccs);
			
			bestMatch = identifer.getBestMatch();
			System.out.println("Best match: " + bestMatch.getName());
			
			
////	    	GraphAudio g = new GraphAudio("test", mfccs[20]);
////	    	g.showGraph();
//			
//			//get users from database, gmm for each mfcc, compare gmm to mfccs generated
//			DBController dbcontroller = new DBController();
//			dbcontroller.connect();
//			UserDAOImpl userDAOImpl = new UserDAOImpl(dbcontroller.getDatabase());
//			User user1 = userDAOImpl.find((long) 1);
//			User user2 = userDAOImpl.find((long) 5);
//			
//			GaussianMixtureModel gmm1 = new GaussianMixtureModel(user1.getMFCCs());
//			GaussianMixtureModel gmm2 = new GaussianMixtureModel(user2.getMFCCs());
//			
//			double[] user1Array = gmm1.compare(mfccs);
//			double[] user2Array = gmm2.compare(mfccs);
//			
//			System.out.println("User 1: "+ Arrays.toString(user1Array));
//			System.out.println("User 2: "+ Arrays.toString(user2Array));
//			
//			System.out.println("user 1 sum: " + DoubleStream.of(user1Array).sum());
//			System.out.println("user 2 sum: " + DoubleStream.of(user2Array).sum());
//			
//			
////			GaussianMixtureModel gmm = new GaussianMixtureModel(mfccs);
////			System.out.println("User 1: "+ Arrays.toString(gmm.compare(user1.getMFCCs())));
////			
////			System.out.println("User 2: "+ Arrays.toString(gmm.compare(user2.getMFCCs())));
		}
		else if(e.getSource() == btnCorrect){
			generateMetric(true);
		}
		else if(e.getSource() == btnIncorrect){
			generateMetric(false);
		}

	}
	
	
	private void generateMetric(boolean result) {
		Metric metric = new Metric(bestMatch.getName(), bestMatch.getLikeihood(), phrase, result);
		saveMetric(metric);
	}

	private void saveMetric(Metric metric) {
		DBController dbcontroller = new DBController();
		dbcontroller.connect();
		MetricDAOImpl metricDAOImpl = new MetricDAOImpl(dbcontroller.getDatabase());
		metricDAOImpl.save(metric);
		System.out.println("metric saved");
	}

	private void checkSpokenPhrase() {
		speechRecognition.transcriptUsingHTTP(file);
		if(speechRecognition.isPhraseMatch(phrase)){
			btnContinue.setEnabled(true);
	    	btnPlayback.setEnabled(true);
//	    	lblPhraseMatch.setVisible(true);
		}
		else{
			String whatWasSaid = speechRecognition.getSpokenPhrase();
			String message = "You did not say the correct phrase. You said:\n" + whatWasSaid + "\nPlease re-record to continue";
			JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
