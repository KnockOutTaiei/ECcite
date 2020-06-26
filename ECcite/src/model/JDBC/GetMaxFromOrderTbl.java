/*
 *IDの最大値をデータベースOrderTblからとってくるDAO
*/
package model.JDBC;
import java.sql.*;

public class GetMaxFromOrderTbl extends JDBCBase{
	/**@override*/
	/**@deprecated*/
	protected boolean startSQL(){return false;}//delete definition

	public Long getLongViaSQL(String columnToMax, String tableName) {
		Long returnValue = null;
		if(super.isConnect) {
			try {
				//SQLの発行
				////precompile
				String query = "SELECT MAX(order_id) AS maximum FROM OrderTbl;";//SELECT MAX(?) AS maximum FROM OrderTbl;だと''が入る
				PreparedStatement pstmt = super.connection.prepareStatement(query);
				//pstmt.setString(1, columnToMax);
				ResultSet result = pstmt.executeQuery();
				////commit
				super.connection.commit();

				//結果の参照
				while(result.next()) {
					returnValue = (Long)(long)result.getInt("maximum");//SQL文はASでのエイリアスも見ているので、AS書けばよい
				}

				//クローズ処理もスタック構造を意識するとよい
				result.close();//一番上にのったResultSetを解放
				pstmt.close();//その直下のPreparedStatementを解放
				//super.disconnectConnection();//一番下の
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return returnValue;
	}

}