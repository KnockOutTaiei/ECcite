package controller;
import model.*;
import model.JDBC.*;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.regex.*;

public class MyAccountController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		SystemMessage systemMessage = new SystemMessage();

		//Update Account
		UpdateAccount updateAccount = new UpdateAccount();
		Account account = (Account)session.getAttribute("account");
		{
			////Get RequestParameter
			String acc_id = request.getParameter("acc_id");
			String login_name = request.getParameter("login_name");
			String login_pw = request.getParameter("login_pw");//セッションスコープとはいえパスワードを保存するのは危険
			String gender = request.getParameter("gender");
			String fullname = request.getParameter("fullname");
			String mail = request.getParameter("mail");
			String tel_no = request.getParameter("tel_no");
			String zip = request.getParameter("zip");
			String prefecture = request.getParameter("prefecture");
			String city = request.getParameter("city");
			String address = request.getParameter("address");

			////String Validation
			if(
				!isMatch(acc_id,".{1,31}") ||
				!isMatch(login_name,".{1,31}") ||
				!isMatch(login_pw,"[a-zA-Z0-9]{8,21}") ||
				!isMatch(gender,"[MFO]") ||
				!isMatch(fullname,".{1,21}") ||
				!isMatch(mail,"[a-zA-Z0-9!#%&'/=_~`¥*¥+¥?¥{¥}¥^¥$¥-¥|]{1,}@[a-zA-Z_0-9¥.!#%&'/=_~`¥*¥+¥?¥{¥}¥^¥$¥-¥|]{1,}") ||
				!isMatch(mail,".{1,65}") ||//mail size check
				!!isMatch(mail,"[¥.]$") ||//ends with . is not allowed
				!isMatch(tel_no,"[0-9]{1,}[¥-]{0,2}[0-9]{1,}[¥-]{0,2}[0-9]{1,}") ||
				!isMatch(tel_no,".{1,12}") ||//TEL size check
				!isMatch(zip,".{1,11}") ||
				!isMatch(prefecture,".{1,11}") ||
				!isMatch(city,".{1,11}") ||
				!isMatch(address,".{1,31}")
				) {
				systemMessage.setMessage("入力が形式に添っていません");
				session.setAttribute("systemMessage",systemMessage);
				response.sendRedirect("myccount.jsp");
				return;
			}
		}
		////Set strings to Account
		account.setAcc_id(request.getParameter("acc_id"));
		account.setLogin_name(request.getParameter("login_name"));
		account.setLogin_pw(request.getParameter("login_pw"));
		account.setGender(request.getParameter("gender"));
		account.setFullname(request.getParameter("fullname"));
		account.setMail(request.getParameter("mail"));
		account.setTel_no(request.getParameter("tel_no"));
		account.setZip(request.getParameter("zip"));
		account.setPrefecture(request.getParameter("prefecture"));
		account.setCity(request.getParameter("city"));
		account.setAddress(request.getParameter("address"));
		updateAccount.startSQL(account);

		//Save it to sessionscoup
		session.setAttribute("account", account);

		//Redirect
		response.sendRedirect("myaccount.jsp");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		SystemMessage systemMessage = new SystemMessage();

		//Delete Account
		Account account = (Account)session.getAttribute("account");
		DeleteAccount deleteAccount = new DeleteAccount();
		deleteAccount.startSQL(account);

		//Discard Session
		session.invalidate();

		//Redirect
		response.sendRedirect("login.jsp");
	}

	private boolean isMatch(String textToValidate, String regexToUse){
        Pattern pattern = Pattern.compile(regexToUse);//X{n,m}	X, at least n but not more than m times
		Matcher matcher = pattern.matcher(textToValidate);
		return matcher.matches();
    }
	public MyAccountController() {};
}