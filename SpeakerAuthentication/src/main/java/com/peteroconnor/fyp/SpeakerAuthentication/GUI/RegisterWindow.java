package com.peteroconnor.fyp.SpeakerAuthentication.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.peteroconnor.fyp.SpeakerAuthentication.App;

import edu.cmu.sphinx.linguist.flat.SentenceHMMState.Color;

public class RegisterWindow extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel header, lblName, lblEmail, lblPhone;
	private JTextField txtName, txtEmail, txtPhone;
    private JButton btnContinue, btnClear, btnBack;
    static App app;
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterWindow frame = new RegisterWindow(app);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterWindow(App app) {
		this.app = app;
		 setVisible(true);
	        setSize(700, 700);
	        getContentPane().setLayout(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setTitle("Registration - User Details");
	        
	        header = new JLabel("Register");
	        
	        header.setFont(new Font("Serif", Font.BOLD, 20));
	 
	        lblName = new JLabel("Name:");
	        lblEmail = new JLabel("Email:");
	        lblPhone = new JLabel("Phone No:"); 
	        txtName = new JTextField();
	        txtEmail = new JTextField();
	        txtPhone = new JTextField();
	 
	        btnContinue = new JButton("Continue");
	        btnClear = new JButton("Clear");
	        btnBack = new JButton("Back");
	 
	        btnContinue.addActionListener(this);
	        btnClear.addActionListener(this);
	        btnBack.addActionListener(this);
	        
	        header.setBounds(80, 30, 420, 30);
	        lblName.setBounds(80, 70, 200, 30);
	        lblEmail.setBounds(80, 110, 200, 30);
	        lblPhone.setBounds(80, 156, 200, 30);
	        txtName.setBounds(300, 70, 200, 30);
	        txtEmail.setBounds(300, 110, 200, 30);
	        txtPhone.setBounds(300, 156, 200, 30);
	        btnContinue.setBounds(158, 257, 100, 30);
	        btnClear.setBounds(278, 257, 100, 30);
	        btnBack.setBounds(400, 258, 100, 30);
	 
	        getContentPane().add(header);
	        getContentPane().add(lblName);
	        getContentPane().add(txtName);
	        getContentPane().add(lblEmail);
	        getContentPane().add(txtEmail);
	        getContentPane().add(lblPhone);
	        getContentPane().add(txtPhone);
	        getContentPane().add(btnContinue);
	        getContentPane().add(btnClear);
	        getContentPane().add(btnBack);
	        
	}
	
	public void actionPerformed(ActionEvent e) 
    {
       if (e.getSource() == btnContinue)
        {
           String name = txtName.getText();
           String email = txtEmail.getText();
           String phone = txtPhone.getText();
           
       } 
         else if(e.getSource() == btnClear)
      {
           txtName.setText("");
           txtEmail.setText("");
           txtPhone.setText("");
       }
         else if(e.getSource() == btnBack){
        	 app.setVisible(true);
        	 this.setVisible(false);
        	 this.dispose();
        	 
         }
   } 

}
