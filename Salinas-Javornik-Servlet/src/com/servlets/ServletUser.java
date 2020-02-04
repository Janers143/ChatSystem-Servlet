package com.servlets;

/**
 * This class allows us to store some basic information of the users
 * that connect to the server
 * @author salinasg
 */
public class ServletUser {
	
	// ATTRIBUTES
	/**
	 * The distant user's pseudonym
	 */
	private String Pseudonym;
	
	/**
	 * The distant users's IP address
	 */
	private String IPAddress;
	
	/**
	 * The distant user's MAC address
	 */
	private String MACAddress;
	
	/**
	 * Constructor : Used to create a new User when it connects
	 */
	public ServletUser(String pseudo, String ip, String mac) {
		this.IPAddress = ip;
		this.MACAddress = mac;
		this.Pseudonym = pseudo;
	}
	
	// GETTERS
	/**
	 *Gets the user's pseudonym
	 * @return User's pseudonym
	 */
	public String getPseudonym() { return this.Pseudonym; }
	
	/**
	 *Gets the user's IP address
	 * @return User's IP address
	 */
	public String getIPAddress() { return this.IPAddress; }
	
	/**
	 *Gets the user's MAC address
	 * @return User's MAC address
	 */
	public String getMACAddress() { return this.MACAddress; }
	
	// SETTERS
	public void changePseudonym(String newPseudo) {
		this.Pseudonym = newPseudo;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.Pseudonym + " - " + this.IPAddress + " - " + this.MACAddress;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o){
		boolean ret;
		
		// Checking if the users we're comparing are the same
		if (o == this) {
			ret = true;
		}
		// Checking if the object is a ServletUser
		if (!(o instanceof ServletUser)) {
			ret = false;
		}
		
		ServletUser usr = (ServletUser) o;
		ret = (usr.getMACAddress().equals(this.MACAddress)) && (usr.getIPAddress().equals(this.IPAddress));
		
		return ret;
	}
}
