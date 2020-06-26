/*NO USE
 * アカウントIDの最大値をデータベースからとってくるDAO
*/
package model.JDBC;
import java.sql.*;

public class GetMax extends JDBCBase{
	/**@override*/
	/**@deprecated*/
	protected boolean startSQL(){return false;}//delete definition

	public Long getLongViaSQL(String columnToMax, String tableName) {
		Long returnValue = null;
		if(super.isConnect) {
			try {
				//SQLの発行
				////precompile
				String query = "SELECT MAX(?) FROM ?;";
				PreparedStatement pstmt = super.connection.prepareStatement(query);
				pstmt.setBinaryStream(1, columnToMax);//When you designate tablename, setString() UNAVOIDABLY insert ' ' into preparedstatement. so you can't designate table...
				pstmt.setString(2, tableName);
				ResultSet result = pstmt.executeQuery();
				////commit
				super.connection.commit();

				//結果の参照
				while(result.next()) {
					returnValue = result.getLong("MAX("+columnToMax+")");
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
////Assign ID
/*Long nextmaxacc_id = null;
{
	GetMaxAcc_id getmaxacc_id = new GetMaxAcc_id();
	Long maxacc_id = getmaxacc_id.getLongViaSQL();
	if(maxacc_id!=null) {
		nextmaxacc_id = ++maxacc_id;
	}
}*/