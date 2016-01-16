package servletPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import testWebApp.db.DBconn;



public class DeleteServlet extends HttpServlet 
{
	  private static final long serialVersionUID = 1L;
	 
	  public DeleteServlet()
	  {
	    super();
	  }
	  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("application/text");
		  StringBuffer sb = new StringBuffer();
		    try 
		    {
		      BufferedReader reader = request.getReader();
		      String line = null;
		      while ((line = reader.readLine()) != null)
		      {
		        sb.append(line);
		      }
		    } catch (Exception e) { e.printStackTrace(); }
		 
		    JSONParser parser = new JSONParser();
		    JSONObject joUser = null;
		    try
		    {
		      joUser = (JSONObject) parser.parse(sb.toString());
		    } catch (ParseException e) { e.printStackTrace(); }

		    String id = (String) joUser.get("searchId");
		    int searchId = Integer.parseInt(id);
		    DBconn conn = new DBconn();
		    String status = conn.delUser(searchId);
		    PrintWriter out = response.getWriter();
		    out.print(status);
		    out.flush();
		    out.close();
	  	}
	  
}