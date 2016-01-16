package servletPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import testWebApp.db.DBconn;

public class ListServer extends HttpServlet 
{
	  private static final long serialVersionUID = 1L;
	 
	  public ListServer()
	  {
	    super();
	  }
	  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("text/html");
		  DBconn conn = new DBconn();
		  ArrayList value = new ArrayList();
			value = conn.getUsers();
			 PrintWriter out = response.getWriter();
			if (value.isEmpty()) {
				String noUserString = "There are no user entries";
				out.print(noUserString);
			    out.flush();
			    out.close();
			} else {
			  response.setContentType("application/json");
			    out.print(value);
			    out.flush();
			    out.close();
			}
	  }
}
