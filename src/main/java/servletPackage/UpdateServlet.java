package servletPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import testWebApp.db.DBconn;
import testWebApp.db.JsonEncodeStorage;

public class UpdateServlet extends HttpServlet 
{
  private static final long serialVersionUID = 1L;
 
  public UpdateServlet()
  {
    super();
  }
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 

  {
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

    String updateField = (String) joUser.get("updateField");
    String newValue = (String) joUser.get("newValue");
    String id = (String)joUser.get("searchId2");
    int searchId = Integer.parseInt(id);
    DBconn dbconn = new DBconn();
    String updateResults = dbconn.updateUser(searchId, updateField, newValue);
    PrintWriter out = response.getWriter();
    out.print(updateResults);
    out.flush();
    out.close();
  }
}
