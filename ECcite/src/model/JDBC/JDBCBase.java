package model.JDBC;
import java.sql.*;

public class JDBCBase{
	protected static Connection connection = null;
	protected static boolean isConnect = false;

	public JDBCBase(){
		//JDBCドライバの登録
		if(connection==null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver Registered");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return;
			}

			//DBMSへの接続
			if(!isConnect)isConnect = establishConnection();
		}
	}

	protected boolean startSQL() {return false;};

	protected boolean establishConnection(){
		try {
			String databaseURI = "jdbc:mysql://localhost/GWEC?characterEncoding=utf8&character_set_server=utf8mb4";
			String loginUser = "root";
			String password = "password";
			connection = DriverManager.getConnection(databaseURI,loginUser,password);
			connection.setAutoCommit(false);//Use Transaction
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean disconnectConnection(){
		if(isConnect) {
			try {
				connection.close();
				isConnect = false;
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		else return false;
	}
}