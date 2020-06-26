/*
 * 注文レコード作成
*/
package model.JDBC;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.*;

public class CreateOrder extends JDBCBase{
	/**@override*/
	/**@deprecated*/
	protected boolean startSQL(){return false;}//delete definition
	/**@override*/
	public boolean startSQL(Order order) {
		boolean resultflag = false;
		if(super.isConnect) {
			try {
				//SQLの発行
				////precompile
				String query = "INSERT INTO OrderTbl(order_id,whole_amount,order_date,limit_date,confirm_date,order_status,del_flg,ins_date,upd_date,acc_id) VALUES(?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement pstmt = super.connection.prepareStatement(query);

				//Set
				////from LocalDateTime to like"2020/05/11 15:36:45"
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

				pstmt.setLong(1, order.getOrder_id());//TODO:Database holds id as INT!
				pstmt.setInt(2, order.getWhole_amount());
				String order_dateStr = order.getOrder_date().format(formatter);pstmt.setString(3,order_dateStr);
				String limit_dateStr = order.getLimit_date().format(formatter);pstmt.setString(4,limit_dateStr);
				String confirm_dateStr = order.getConfirm_date().format(formatter);pstmt.setString(5,confirm_dateStr);
				pstmt.setString(6, order.getOrder_status());
				pstmt.setBoolean(7,false);
				LocalDateTime now = LocalDateTime.now();
				String timeStr = now.format(formatter);
				pstmt.setString(8, timeStr);
				pstmt.setString(9, timeStr);
				pstmt.setString(10, order.getAcc_id());
				int succeededRowNum = pstmt.executeUpdate();
				////commit
				super.connection.commit();

				//結果の参照
				if(succeededRowNum!=1) {
					System.out.println("CreateOrder SQL fail");
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