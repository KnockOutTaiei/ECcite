/*
 * カートを表示
 * 商品詳細から:=doPost:=カートに入れる
 * その他から:=doGet:=閲覧のみ
 */
package rpg.controller;
import model.*;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

//for String validation

public class CartController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		SystemMessage systemMessage = new SystemMessage();

		//Get RequestParameter
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		int stockInCart = Integer.parseInt(request.getParameter("stockInCart"));

		//If no input, systemMessage
		if(product_id  == 0) {
			systemMessage.setMessage("商品が存在しません");
			session.setAttribute("systemMessage",systemMessage);
			response.sendRedirect("itemList.jsp");
			return;
		}

		//Save it to cart
		Cart cart = (Cart)session.getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
		}
		Item theItem = (Item)session.getAttribute("theItem");
		cart.add(theItem,stockInCart);

		//Save on SessionScoup
		session.setAttribute("cart", cart);

		//Forward
		systemMessage.erase();
		session.setAttribute("systemMessage",systemMessage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
		dispatcher.forward(request, response);
	}

	public CartController() {};
}