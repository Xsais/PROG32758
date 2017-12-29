package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LS")
public class LorentzServlet extends HttpServlet {
	
	private double result, weightNum, heightNum;
	private String firstName, lastName, height, weight, sex;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		firstName = request.getParameter("firstName");
		lastName = request.getParameter("lastName");
		height = request.getParameter("height");
		weight = request.getParameter("weight");
		sex = request.getParameter("sex");
		
		out.print("<html><title>Lorentz Formula</title><body bgcolor=\"#7bd17f\">");
		
		if (errorChecking()) {	
			
			if (sex.equals("male")) {
				result = (heightNum - 100) - ((heightNum - 150)/2.5);
			}
			else {
				result = (heightNum - 100) - ((heightNum - 150)/4);
			}
			out.println("Ideal Weight: " + result + "<br>");
			out.print("Hello " + firstName + " " + lastName + ", you"
					+ " need to ");

			if (result <= weightNum) {
				out.print("lose " + (weightNum - result) + " kg.");
			}
			else {
				out.print("gain " + Math.abs(weightNum - result) + " kg.");
			}
			
		}
		else {
			out.print("Error. You must fill out all fields correctly in order to proceed.");
		}
		
		out.print("</body></html>");
		
	}
	
	public boolean errorChecking() {
		try {
			weightNum = Double.parseDouble(weight);
			heightNum = Double.parseDouble(height);
		}
		catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

}
