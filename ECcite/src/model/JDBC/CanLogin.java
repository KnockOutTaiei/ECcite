/*
 * ログイン可能か
 * アカウント情報をデータベースからとってきて照らし合わせるDAO
*/
package model.JDBC;
import java.sql.*;

public class CanLogin extends JDBCBase{
	/**@override*/
	/**@deprecated*/
	protected boolean startSQL(){return false;}//delete definition
	/**@override*/
	public boolean startSQL(String acc_id,String login_pw) {
		boolean resultflag = false;
		if(super.isConnect) {
			try {
				//SQLの発行
				////precompile
				String query = "SELECT login_pw FROM Account WHERE acc_id=?;";
				PreparedStatement pstmt = super.connection.prepareStatement(query);
				pstmt.setString(1, acc_id);
				ResultSet result = pstmt.executeQuery();
				////commit
				super.connection.commit();

				//結果の参照
				while(result.next()) {
					resultflag = login_pw.equals(result.getString("login_pw"));
				}

				//クローズ処理もスタック構造を意識するとよい
				result.close();//一番上にのったResultSetを解放
				pstmt.close();//その直下のPreparedStatementを解放
				return resultflag;
				//super.disconnectConnection();//一番下の
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

}