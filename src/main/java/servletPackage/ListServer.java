package servletPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

public class ListServer extends HttpServlet 
{
	  private static final long serialVersionUID = 1L;
	 
	  public ListServer()
	  {
	    super();
	  }
	  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  System.out.print("HEY");
		  response.setContentType("text/html");

		  PrintWriter out = response.getWriter();

	  }
}
