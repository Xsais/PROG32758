package myServletPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Boulet_list
 */
@WebServlet("/Boulet_list")
public class Boulet_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Boulet_list() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\n <html>\n<head><title>QLab 6</title>\n<link rel='stylesheet'" +
				" href='style.css' />\n</head>\n<body>\n<h1>A Boulet List</h1>\n<br />\n<br />\n<ul>\n" +
				"<li>" + (int)(Math.random() * 100) + "</li>" +
				"<li>" + (int)(Math.random() * 100) + "</li>" +
				"<li>" + (int)(Math.random() * 100) + "</li>" +
				"<li>" + (int)(Math.random() * 100) + "</li>" +
				"<li>" + (int)(Math.random() * 100) + "</li>" +
				"<li>" + (int)(Math.random() * 100) + "</li>" +
				"<li>" + (int)(Math.random() * 100) + "</li>" +
				"<li>" + (int)(Math.random() * 100) + "</li>" +
				"<li>" + (int)(Math.random() * 100) + "</li>" +
				"<li>" + (int)(Math.random() * 100) + "</li>\n" +
				"</ul>\n</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
