package rpg.controller;
import model.*;
import model.JDBC.CanLogin;
import model.JDBC.GetAccount;
import model.JDBC.GetItemList;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

//for String validation
import java.util.regex.*;

public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		SystemMessage systemMessage = new SystemMessage();

		//Get RequestParameter
		String acc_id = request.getParameter("acc_id");
		String login_pw = request.getParameter("login_pw");

		//String validation
		Pattern patternAcc_id = Pattern.compile(".{1,31}");
		Pattern patternLogin_pw = Pattern.compile("[a-zA-Z0-9]{8,21}");//X{n,m}	X, at least n but not more than m times
		Matcher matcherAcc_id = patternAcc_id.matcher(acc_id);
		Matcher matcherLogin_pw = patternLogin_pw.matcher(login_pw);
		if(!matcherAcc_id.matches() || !matcherLogin_pw.matches()) {
			systemMessage.setMessage("ログインIDは１文字以上３０文字未満、パスワードは半角英数および記号を含む８文字以上２０文字未満でお願いします。");
			session.setAttribute("systemMessage",systemMessage);
			response.sendRedirect("login.jsp");
			return;
		}
		//debugwrite
		//System.out.println(userName);

		//If no input, systemMessage
		if(acc_id.equals("") || acc_id == null|| login_pw.equals("") || login_pw==null) {
			systemMessage.setMessage("ログインIDまたはパスワードを入力してください");
			session.setAttribute("systemMessage",systemMessage);
			response.sendRedirect("login.jsp");
			return;
		}

		//Login
		boolean canPass = false;
		CanLogin canlogin = new CanLogin();
		canPass = canlogin.startSQL(acc_id, login_pw);
		////Login Fail
		if(!canPass) {
			systemMessage.setMessage("ログインIDまたはパスワードが一致しません");
			session.setAttribute("systemMessage",systemMessage);
			response.sendRedirect("login.jsp");
			return;
		}
		//Login Success: Get Account
		Account account = (Account)session.getAttribute("account");
		if(account == null) {
			account = new Account();
			GetAccount getAccount = new GetAccount();
			account = getAccount.startSQL(account, acc_id);
		}
		session.setAttribute("account", account);

		//Create Cart
		Cart cart = (Cart)session.getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
		}
		session.setAttribute("cart", cart);

		//Get ItemList
		ItemList itemList = new ItemList();
		////Get item information from database
		GetItemList getItemList = new GetItemList();
		int category_id = 1;
		getItemList.startSQL(itemList, category_id);

		////Save on SessionScoup
		session.setAttribute("itemList", itemList);
		NowPage nowPage = new NowPage();
		session.setAttribute("nowPage", nowPage);

		//Forward
		systemMessage.erase();
		session.setAttribute("systemMessage",systemMessage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("itemList.jsp");
		dispatcher.forward(request, response);
	}

	public LoginController() {};
}