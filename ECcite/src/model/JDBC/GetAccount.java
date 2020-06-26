/*
 * アカウント情報をデータベースへ追加するDAO
 * 未テスト
*/
package model.JDBC;
import java.sql.*;

import model.Account;

public class GetAccount extends JDBCBase{
	/**@override*/
	/**@deprecated*/
	public boolean startSQL() {return false;}
	/**@override
	 * @param reference
	 * */
	public Account startSQL(Account account, String acc_id) {//Pass by reference
		if(super.isConnect) {
			try {
				//SQLの発行
				////precompile
				String query = "SELECT acc_id,login_name,gender,fullname,mail,tel_no,zip,prefecture,city,address FROM Account WHERE acc_id=?;";
				PreparedStatement pstmt = super.connection.prepareStatement(query);
				pstmt.setString(1, acc_id);
				ResultSet result = pstmt.executeQuery();
				////commit
				super.connection.commit();

				//結果の参照
				while(result.next()) {
					account.setAcc_id(result.getString("acc_id"));
					account.setLogin_name(result.getString("login_name"));
					//account.setLogin_pw(result.getString("login_pw"));//DANGEROUS
					account.setGender(result.getString("gender"));
					account.setFullname(result.getString("fullname"));
					account.setLogin_name(result.getString("login_name"));
					account.setMail(result.getString("mail"));
					account.setTel_no(result.getString("tel_no"));
					account.setZip(result.getString("zip"));
					account.setPrefecture(result.getString("prefecture"));
					account.setCity(result.getString("city"));
					account.setAddress(result.getString("address"));
				}

				//クローズ処理もスタック構造を意識するとよい
				pstmt.close();//その直下のPreparedStatementを解放
				//super.disconnectConnection();//一番下の
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return account;
	}

}