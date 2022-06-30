package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/furl")
public class FirstServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null,fname=null;
	//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		
		
	//read form1/req1 data
		name=req.getParameter("pname");
		fname=req.getParameter("fname");
	
	//Create Cookies
		Cookie ck1=new Cookie("pname", name);
		Cookie ck2=new Cookie("fname", fname);
		res.addCookie(ck1);
		res.addCookie(ck2);
		
//Generate Second Form dynamically
		pw.println("<form action='surl' method='post'>");
		pw.println("Income:<input type='text' name='income'><br>");
		pw.println("Tax:<input type='text' name='tax'><br>");
		pw.println("<input type='submit' value='submit'>");
		pw.println("</form>");
		
		//close the stream
		pw.close();
	}//doget
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
}//class
