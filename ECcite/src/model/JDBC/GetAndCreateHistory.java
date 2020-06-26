/*
 *IDの最大値をデータベースからとってくるDAO
*/
package model.JDBC;
import java.sql.*;
import model.*;

public class GetAndCreateHistory extends JDBCBase{
	/**@override*/
	/**@deprecated*/
	protected boolean startSQL(){return false;}//delete definition

	public boolean startSQL(History history, String acc_id) {
		if(super.isConnect) {
			try {
				//SQLの発行
				////precompile
				String query = "SELECT product_name,acc_id,stock FROM OrderTbl INNER JOIN OrderDetailTbl ON OrderTbl.order_id = OrderDetailTbl.order_id INNER JOIN ProductTbl ON OrderDetailTbl.product_id = ProductTbl.product_id WHERE acc_id=?;";
				PreparedStatement pstmt = super.connection.prepareStatement(query);
				pstmt.setString(1, acc_id);
				ResultSet result = pstmt.executeQuery();
				////commit
				super.connection.commit();

				//結果の参照
				while(result.next()) {
					EveryHistory everyHistory = new EveryHistory();
					everyHistory.setProduct_name(result.getString("product_name"));
					everyHistory.setStock(result.getString("stock"));
					history.add(everyHistory);
				}

				//クローズ処理もスタック構造を意識するとよい
				result.close();//一番上にのったResultSetを解放
				pstmt.close();//その直下のPreparedStatementを解放
				//super.disconnectConnection();//一番下の
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

}