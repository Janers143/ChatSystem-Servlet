package com.servlets;

import java.util.ArrayList;
import java.util.Iterator;

public class ServletActiveUsers {
	
	private static ServletActiveUsers servActiveUsers = new ServletActiveUsers();
	
	private ArrayList<ServletUser> ActiveUsers;
	
	public ServletActiveUsers() {
		this.ActiveUsers = new ArrayList<>();
	}
	
	public boolean addUser(ServletUser usr) {
		if (!this.ActiveUsers.contains(usr) && !this.containsSamePseudonym(usr.getPseudonym())) {
			this.ActiveUsers.add(usr);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean removeUser(ServletUser usr) {
		return this.ActiveUsers.remove(usr);
	}
	
	public boolean changePseudoUser(ServletUser usr) {
		if (this.ActiveUsers.contains(usr) && !this.containsSamePseudonym(usr.getPseudonym())) {
			getUserByMAC(usr.getMACAddress()).changePseudonym(usr.getPseudonym());
			return true;
		}
		else {
			return false;
		}
	}
	
	public static ServletActiveUsers getSAU() {
		return servActiveUsers;
	}
	
    public boolean containsSamePseudonym(String pseudo){
        boolean exists = false;
        ServletUser usr;
        Iterator<ServletUser> iter = this.ActiveUsers.iterator();
        while(iter.hasNext()){
            usr = iter.next();
            if(usr.getPseudonym().equals(pseudo)){
                exists = true;
            }
        }
        return exists;
    }
    
    public String parseableString() {
    	String ret = "\n";
		ServletUser usr = null;
		Iterator<ServletUser> it = this.ActiveUsers.iterator();
		while (it.hasNext()) {
			usr = (ServletUser) it.next();
			ret += usr.getPseudonym() + "-" + usr.getIPAddress() + "-" + usr.getMACAddress() + "\n";
		}
		
		return ret;
    }
    
    public ServletUser getUserByMAC(String macAdr) {
    	ServletUser usr = null;
    	ServletUser usr_found = null;
    	Iterator<ServletUser> iter = this.ActiveUsers.iterator();
        while(iter.hasNext()){
            usr = iter.next();
            if(usr.getMACAddress().equals(macAdr)){
                usr_found = usr;
            }
        }
        return usr_found;
    }
	
	public String toString() {
		String ret = "";
		ServletUser usr = null;
		Iterator<ServletUser> it = this.ActiveUsers.iterator();
		while (it.hasNext()) {
			usr = (ServletUser) it.next();
			ret += "<li>Pseudonym: "
					+ usr.getPseudonym() + " | IP Address: "
					+ usr.getIPAddress() + " | MAC Address: " 
					+ usr.getMACAddress() + "</li>";
		}
		
		return ret;
	}
}
