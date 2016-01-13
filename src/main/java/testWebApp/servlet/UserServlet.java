package testWebApp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import testWebApp.db.DBconn;
public class UserServlet extends HttpServlet 
{
  private static final long serialVersionUID = 1L;
 
  public UserServlet()
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
    
    String name = (String) joUser.get("name");
    String email = (String) joUser.get("email");
    String telephone = (String) joUser.get("telephone");
    System.out.println(telephone);
    String street = (String) joUser.get("street");
    String city = (String) joUser.get("city");
    String state = (String) joUser.get("state");
    String zip = (String) joUser.get("zip");

    response.setContentType("text/html");
    DBconn dbconn = new DBconn();
    dbconn.addUser(name, email, telephone, street, city, state, zip);
  }
}