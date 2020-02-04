package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\"/>");
		out.println("<title>MyServlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Page générée par le servlet !!</h1>");
		out.println("<h2>Users connected right now :</h2>");
		out.println("<ul>" + ServletActiveUsers.getSAU().toString() + "</ul>");
		out.println("</body>");
		out.println("</html>");
		out.println();
		out.println("<!--" + ServletActiveUsers.getSAU().parseableString() + "-->");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String addrMac = request.getParameter("macAdr");
		String pseudo = request.getParameter("pseudo");
		String addrIp = request.getRemoteAddr();
		String status = request.getParameter("status");
		
		ServletUser error = new ServletUser("ERROR", "ERROR", "ERROR");
		boolean ok;
		
		// Test if the post method received is valid 
		if (addrMac != null && pseudo != null && addrIp != null && status != null) {
			ServletUser usr = new ServletUser(pseudo, addrIp, addrMac);
			
			// Select an action in function of the status (connected,
			// disconnected, newpseudo)
			switch(status) {
			case "connected":
				ok = ServletActiveUsers.getSAU().addUser(usr);
				break;
			case "disconnected":
				ok = ServletActiveUsers.getSAU().removeUser(usr);
				break;
			case "newpseudo":
				ok = ServletActiveUsers.getSAU().changePseudoUser(usr);
				break;
			default:
				ok = false;
				ServletActiveUsers.getSAU().addUser(error);		
			}
		}
		else {
			ok = false;
			ServletActiveUsers.getSAU().addUser(error);
		}
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (ok) {
			out.println("OK");
		}
		else {
			out.println("KO");
		}
	}
	
}
