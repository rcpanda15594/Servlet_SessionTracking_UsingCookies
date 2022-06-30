package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/surl")
public class SecondServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	
		PrintWriter pw=null;
		String income=null,tax=null;
		
	//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		
	//read  form2/req2 data
		income=req.getParameter("income");
		tax=req.getParameter("tax");
		
//read form1/req1 data from cookies
		Cookie ck[]=req.getCookies();
		String name=null,fname=null;
		if(ck!=null) {
			name=ck[0].getValue();
			fname=ck[1].getValue();
			
		}//if
		//write  form1/req1 & form2/req2 values to DB tables as record
				try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");		
				PreparedStatement ps=con.prepareStatement("INSERT INTO TAX_TAB VALUES(?,?,?,?)");
				ps.setString(1,name);
				ps.setString(2,fname);
				ps.setString(3,income);
				ps.setString(4,tax);
				
				int result=ps.executeUpdate();
				
				if(result==1)
					pw.println("<br><b>Record Inserted<br>");
				else
					pw.println("<br><b>Record not Inserted");
				
				}//try
				catch(Exception e) {
					e.printStackTrace();
				}
				
				
				
				
		//display form1/req1 & form2/request2 data on browser
				pw.println("<br>Form1 data is:"+name+"...."+fname);
				pw.println("<br>Form2 data is:"+income+"...."+tax);
				
				pw.close();

	}//doget

	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}//dopost

}
