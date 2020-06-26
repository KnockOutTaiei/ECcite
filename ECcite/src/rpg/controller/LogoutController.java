package rpg.controller;
import model.*;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class LogoutController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		session.invalidate();

		//Forward
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

	public LogoutController() {};
}