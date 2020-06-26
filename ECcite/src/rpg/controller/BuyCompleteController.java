/*
 * 購入完了処理
 * 在庫の整合もここでやる
 * TODO:20200514
 */
package rpg.controller;
import model.*;
import model.JDBC.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

//for String validation

public class BuyCompleteController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private class GetOrderException extends Exception{

	}
	private class GetOrderDetailException extends Exception{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		SystemMessage systemMessage = new SystemMessage();

		//Take items from Cart
		Cart cart = (Cart)session.getAttribute("cart");

		//Create voucher
		////Create Order in Database
	    //////Get Present Maximum ID
		Long nextMaxOrder_id = null;
		{
			try {
				GetMaxFromOrderTbl getMaxOrderFromTbl = new GetMaxFromOrderTbl();
				Long maxOrder_id = getMaxOrderFromTbl.getLongViaSQL("order_id","OrderTbl");
				if(maxOrder_id!=null) {
					nextMaxOrder_id = ++maxOrder_id;
				}else throw new GetOrderException();
			}catch(GetOrderException e) {
				e.printStackTrace();
				nextMaxOrder_id = (long) 1;
			}
		}
		////Create Order instance
		Order order = new Order();
		{
			order.setOrder_id((int)(long)nextMaxOrder_id);
			Account account = (Account)session.getAttribute("account");order.setAcc_id(account.getAcc_id());
			order.setWhole_amount((int)cart.calcTotal());
			order.setOrder_date(LocalDateTime.now());
			order.setLimit_date(LocalDateTime.now());
			order.setConfirm_date(LocalDateTime.now());//TODO:If confirmed
			order.setOrder_status("支払い待ち");
		}
	    ////Save Order to database
		CreateOrder createOrder = new CreateOrder();
		createOrder.startSQL(order);


		////Create OrderDetail in DataBase
	    //////Get Present Maximum ID
		Long nextMaxOrderDetail_id = null;
		{
			try {
				GetMaxFromOrderDetailTbl getMaxOrderDetailTbl = new GetMaxFromOrderDetailTbl();
				Long maxOrderDetail_id = getMaxOrderDetailTbl.getLongViaSQL("voucher_id","OrderDetailTbl");
				if(maxOrderDetail_id!=null) {
					nextMaxOrderDetail_id = ++maxOrderDetail_id;
				}else throw new GetOrderDetailException();
			}catch(GetOrderDetailException e) {
				e.printStackTrace();
				nextMaxOrderDetail_id = (long) 1;
			}
		}
		////Create OrderDetail instance: 明細は店ごととかもできるが（発送別でという分け方もできるが）、今回は品物毎に全て分けてしまう
		ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		{
			int counter = (int)(long)nextMaxOrderDetail_id;
			for(ItemSet il: cart.getCart()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrder_id(order.getOrder_id());
				orderDetail.setVoucher_id(counter);
				orderDetail.setProduct_id(il.getItem().getProduct_id());
				orderDetail.setStock(il.getStockInCart());
				orderDetail.setAmount(il.getItem().getProduct_price()*il.getStockInCart());
				orderDetail.setTax(0.1);
				orderDetail.setDel_flg(false);
				//orderDetail.setIns_date();
				//orderDetail.setUpd_date();
				orderDetailList.add(orderDetail);
				counter++;
			}
		}
		////Save OrderDetail to database
		CreateOrderDetail createOrderDetail = new CreateOrderDetail();
		for(OrderDetail orderDetail: orderDetailList) {
			createOrderDetail.startSQL(orderDetail);
		}


		//Reset Cart and Export them to History
		////get or create history
		Cart history = (Cart)session.getAttribute("history");
		if(history == null){
			history = new Cart();
		}
		////copying
		for(int i=0;i<cart.getCart().size();i++) {
			history.add(cart.getCart().get(i).getItem(), cart.getCart().get(i).getStockInCart());
		}
		////cart reset
		cart.erase();

		//Save on SessionScoup
		session.setAttribute("order",order);
		session.setAttribute("orderDetailList",orderDetailList);
		session.setAttribute("history",history);
		session.setAttribute("cart",cart);

		//Forward
		systemMessage.erase();
		session.setAttribute("systemMessage",systemMessage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("completed.jsp");
		dispatcher.forward(request, response);
	}

	public BuyCompleteController() {};
}
/*
 * orderDetail const *p = new orderDetail;
 */