/*
 * アカウント情報をデータベースへ追加するDAO
 * 未テスト
*/
package model.JDBC;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import model.Account;

public class CreateAccount extends JDBCBase{
	/**@override*/
	/**@deprecated*/
	public boolean startSQL() {return false;}
	/**@override*/
	public boolean startSQL(Account account) {
		if(super.isConnect) {
			try {
				//SQLの発行
				////precompile
				String query = "INSERT INTO Account(acc_id,login_name,login_pw,gender,fullname,mail,tel_no,zip,prefecture,city,address,del_flg,ins_date,upd_date) VALUES(?,?,?,?,?,?,?,?,?,?,?,FALSE,?,?);";
				PreparedStatement pstmt = super.connection.prepareStatement(query);
				pstmt.setString(1, account.getAcc_id());
				pstmt.setString(2, account.getLogin_name());
				pstmt.setString(3, account.getLogin_pw());
				pstmt.setString(4, account.getGender());
				pstmt.setString(5, account.getFullname());
				pstmt.setString(6, account.getMail());
				pstmt.setString(7, account.getTel_no());
				pstmt.setString(8, account.getZip());
				pstmt.setString(9, account.getPrefecture());
				pstmt.setString(10, account.getCity());
				pstmt.setString(11, account.getAddress());

				//from LocalDateTime to like"2020/05/11 15:36:45"
				LocalDateTime localdatetime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				String timeStr = localdatetime.format(formatter);
				pstmt.setString(12, timeStr);
				pstmt.setString(13, timeStr);
				int succeededRowNum = pstmt.executeUpdate();
				////commit
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