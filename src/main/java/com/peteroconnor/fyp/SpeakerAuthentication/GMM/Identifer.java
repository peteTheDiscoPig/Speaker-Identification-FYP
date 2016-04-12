package com.peteroconnor.fyp.SpeakerAuthentication.GMM;

import java.util.stream.DoubleStream;

import com.peteroconnor.fyp.SpeakerAuthentication.DB.DBController;
import com.peteroconnor.fyp.SpeakerAuthentication.DB.UserDAOImpl;
import com.peteroconnor.fyp.SpeakerAuthentication.Entity.User;

import cern.colt.Arrays;

public class Identifer {
	private double[][] mfccs;
	private UserDAOImpl userDAOImpl;

	public Identifer(double[][] mfccs) {
		this.setMfccs(mfccs);
		DBController dbcontroller = new DBController();
		dbcontroller.connect();
		userDAOImpl = new UserDAOImpl(dbcontroller.getDatabase());
	}
	
	

	/**
	 * @return the mfccs
	 */
	public double[][] getMfccs() {
		return mfccs;
	}

	/**
	 * @param mfccs the mfccs to set
	 */
	public void setMfccs(double[][] mfccs) {
		this.mfccs = mfccs;
	}



	public User getBestMatch() {
		//get array of users from database
		User[] users = userDAOImpl.findAll();
		//get log prob for each
		User bestMatch = bestMatch(users);
		// return user with highest value
		return bestMatch;
	}



	private User bestMatch(User[] users) {
		for(User u: users){
			GaussianMixtureModel gmm = new GaussianMixtureModel(u.getMFCCs());
			double[] likeihoodArray = gmm.compare(mfccs);
			//System.out.println(Arrays.toString(likeihoodArray));
			double likeihood = DoubleStream.of(likeihoodArray).sum();
			System.out.println(u.getName() + " " + likeihood);
			u.setLikeihood(likeihood);
		}
		User highestLikeihood = getHighestLikeihood(users);
		return highestLikeihood;
	}



	private User getHighestLikeihood(User[] users) {
		User highestLikeihood = users[0];
		for(User u: users){
			if(u.getLikeihood()> highestLikeihood.getLikeihood()){
				highestLikeihood = u;
			}
		}
		return highestLikeihood;
	}

}
