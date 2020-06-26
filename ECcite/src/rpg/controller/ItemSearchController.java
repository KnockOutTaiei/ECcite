package rpg.controller;
import model.*;
import model.JDBC.GetAccount;
import model.JDBC.SearchItemList;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class ItemSearchController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		SystemMessage systemMessage = new SystemMessage();

		//Get RequestParameter
		String textSearch = request.getParameter("textSearch");
		int categorySearch = Integer.parseInt(request.getParameter("categorySearch"));
		////create NowPage
		NowPage nowPage = new NowPage();
		{
			String temp = request.getParameter("nowPage");
			if(temp != null){
				nowPage = new NowPage(Integer.parseInt(temp));
			}
		}

		//If no input, systemMessage
		if(textSearch == null|| categorySearch == 0) {
			systemMessage.setMessage("検索条件を入力してください");
			session.setAttribute("systemMessage",systemMessage);
			response.sendRedirect("itemList.jsp");
			return;
		}

		//If searchwords' size is big, cut it down
		if(textSearch.length()>30) {
			textSearch = textSearch.substring(0, 29);
		}

		//Search by SQL
		ItemList itemList = new ItemList();
		SearchItemList searchItemList = new SearchItemList();
		searchItemList.startSQL(itemList, textSearch, categorySearch);

		//Save on SessionScoup
		session.setAttribute("itemList", itemList);
		session.setAttribute("nowPage", nowPage);

		//Forward
		systemMessage.erase();
		session.setAttribute("systemMessage",systemMessage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("itemList.jsp");
		dispatcher.forward(request, response);
	}

	public ItemSearchController() {};
}