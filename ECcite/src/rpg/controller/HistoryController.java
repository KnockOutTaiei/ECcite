/*
 * 履歴という名の所持アイテムを表示
 */
package rpg.controller;
import model.*;
import model.JDBC.*;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

//for String validation

public class HistoryController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		SystemMessage systemMessage = new SystemMessage();

		//Get History
		History history = new History();
		GetAndCreateHistory getAndCreateHistory = new GetAndCreateHistory();
		getAndCreateHistory.startSQL(history,((Account)session.getAttribute("account")).getAcc_id());

		//Save on SessionScoup
		session.setAttribute("history", history);

		//Forward
		systemMessage.erase();
		session.setAttribute("systemMessage",systemMessage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("holdings.jsp");
		dispatcher.forward(request, response);
	}

	public HistoryController() {};
}