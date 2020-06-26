/*
 *IDの最大値をデータベースからとってくるDAO
*/
package model.JDBC;
import java.sql.*;

public class GetMaxFromOrderDetailTbl extends JDBCBase{
	/**@override*/
	/**@deprecated*/
	protected boolean startSQL(){return false;}//delete definition

	public Long getLongViaSQL(String columnToMax, String tableName) {
		Long returnValue = null;
		if(super.isConnect) {
			try {
				//SQLの発行
				////precompile
				String query = "SELECT MAX(voucher_id) AS maximum FROM OrderDetailTbl;";//SELECT MAX(?) AS maximum FROM OrderDetailTbl;
				PreparedStatement pstmt = super.connection.prepareStatement(query);
				//pstmt.setString(1, columnToMax);
				ResultSet result = pstmt.executeQuery();
				////commit
				super.connection.commit();

				//結果の参照
				while(result.next()) {
					returnValue = (Long)(long)result.getInt("maximum");
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
/*
 * ①アカウント作成画面で「作成」を実行した後は、ログインs画面へ遷移
②マイアカウント画面で「削除」を実行した後は、ログイン画面へ遷移
③マイアカウント画面で「更新」を実行した後は、商品検索画面へ遷移

 */