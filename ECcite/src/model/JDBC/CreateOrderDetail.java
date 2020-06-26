/*
 * 注文詳細レコード作成
*/
package model.JDBC;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.*;

public class CreateOrderDetail extends JDBCBase{
	/**@override*/
	/**@deprecated*/
	protected boolean startSQL(){return false;}//delete definition
	/**@override*/
	public boolean startSQL(OrderDetail orderDetail) {
		boolean resultflag = false;
		if(super.isConnect) {
			try {
				//SQLの発行
				////precompile
				String query = "INSERT INTO OrderDetailTbl(order_id,voucher_id,product_id,stock,amount,tax,del_flg,ins_date,upd_date) VALUES(?,?,?,?,?,?,?,?,?);";
				PreparedStatement pstmt = super.connection.prepareStatement(query);

				////from LocalDateTime to like"2020/05/11 15:36:45"
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

				pstmt.setLong(1, orderDetail.getOrder_id());//TODO:Database holds id as INT!
				pstmt.setInt(2, orderDetail.getVoucher_id());
				pstmt.setInt(3, orderDetail.getProduct_id());
				pstmt.setInt(4, orderDetail.getStock());
				pstmt.setInt(5, orderDetail.getAmount());
				pstmt.setDouble(6,orderDetail.getTax());
				pstmt.setBoolean(7, false);
				LocalDateTime now = LocalDateTime.now();
				String timeStr = now.format(formatter);
				pstmt.setString(8, timeStr);
				pstmt.setString(9, timeStr);
				int succeededRowNum = pstmt.executeUpdate();
				////commit
				super.connection.commit();

				//結果の参照
				if(succeededRowNum!=1) {
					System.out.println("CreateOrderDetail SQL fail");
				}

				//クローズ処理もスタック構造を意識するとよい
				pstmt.close();//その直下のPreparedStatementを解放
				return true;
				//super.disconnectConnection();//一番下の
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

}