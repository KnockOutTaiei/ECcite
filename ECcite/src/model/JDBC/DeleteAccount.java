/*
 * アカウント情報をデータベースへ追加するDAO
 * 未テスト
*/
package model.JDBC;
import java.sql.*;

import model.Account;

public class DeleteAccount extends JDBCBase{
	/**@override*/
	/**@deprecated*/
	public boolean startSQL() {return false;}
	/**@override*/
	public boolean startSQL(Account account) {
		if(super.isConnect) {
			try {
				//SQLの発行
				////precompile
				String query = "UPDATE Account SET del_flg=TRUE WHERE acc_id=?";
				PreparedStatement pstmt = super.connection.prepareStatement(query);
				pstmt.setString(1, account.getAcc_id());

				////commit
				int succeededRowNum = pstmt.executeUpdate();
				super.connection.commit();

				//結果の参照
				if(succeededRowNum!=1) {throw new SQLException("succeededRowNum is not 1");}

				//クローズ処理もスタック構造を意識するとよい
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