package com.peteroconnor.fyp.SpeakerAuthentication.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.peteroconnor.fyp.SpeakerAuthentication.PhraseGen.PhraseGen;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class LoginWindow extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblPrompt;
	private JButton btnNewPhrase;
	private PhraseGen phraseGen;
	private String phrase = "If you see this uncomment generatePhrase()";
	private final String htmlTag1 = "<html><p style='width: 500px'>";
	private final String htmlTag2 = "</p></html>";


	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 700);
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
		btnNewPhrase.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNewPhrase){
			phrase = phraseGen.generatePhrase();
			lblPrompt.setText(htmlTag1 + phrase + htmlTag2);
		}

	}
}
