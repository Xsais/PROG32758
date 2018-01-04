package myServlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MagicSquareServlet
 */
@WebServlet("/MagicSquareServlet")
public class MagicSquareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MagicSquareServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// variable contains user size preference for magic square
		int userPref = Integer.parseInt(request.getParameter("magicSquareSize"));

		// selection statement used to prevent errors, magic box must be at least 2x2 in
		// size to work
		if (userPref < 2) {
			out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"ISO-8859-1\">\r\n"
					+ "<title>Assignment 3</title>\r\n" + "</head>\r\n" + "<body><h1>Assignment 3 by Group 1</h1>\r\n"
					+ "	<br><form action=\"MagicSquareServlet\" method=\"GET\">\r\n"
					+ "		Enter size of Magic Square: <input type=\"text\" autofocus pattern='\\d{1,}' title='Enter a number' name='magicSquareSize' required>\r\n"
					+ "		<br> \r\n" + "		<input type=\"submit\" value=\"Enter\">\r\n" + "	</form><br>"
					+ "<script>alert('You must enter a number higher than 1')</script>" + "</body></html");
		} else {
			// pass user defined matrix size to helper class to create Magic Square
			int[][] magicSquare = MagicSquareHelper.createMagicSquare(userPref);

			out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"ISO-8859-1\">\r\n"
					+ "<title>Assignment 3</title>\r\n" + "</head>\r\n" + "<body><h1>Assignment 3 by Group 1</h1>\r\n"
					+ "	<br><form action=\"MagicSquareServlet\" method=\"GET\">\r\n"
					+ "		Enter size of Magic Square: <input type=\"text\" autofocus pattern='\\d{1,}' title='Enter a number' name='magicSquareSize' required>\r\n"
					+ "		<br> \r\n" + "		<input type=\"submit\" value=\"Enter\">\r\n"
					+ "	</form><br><table border=1>");
			for (int i = 0; i < userPref; i++) {
				out.println("<tr>");
				for (int j = 0; j < userPref; j++) {
					out.printf("<td>" + magicSquare[i][j] + "</td>");
				}
			}
			out.println("</table></body></html>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
