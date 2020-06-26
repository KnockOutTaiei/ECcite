/*
 * 商品リストをデータベースからとってくるDAO
 * カテゴリ検索
*/
package model.JDBC;
import java.sql.*;

import model.Item;
import model.ItemList;

public class GetItemList extends JDBCBase{
	/**@override*/
	/**@deprecated*/
	protected boolean startSQL(){return false;}//delete definition

	public boolean startSQL(ItemList itemList,int category_id) {
		if(super.isConnect) {
			try {
				//SQLの発行
				////precompile
				String query = "SELECT a.product_id, a.category_id, a.product_name, a.product_price, a.product_img, a.product_detail, a.stock FROM (SELECT ProductTbl.product_id, ProductTbl.product_name, ProductTbl.category_id, ProductTbl.product_price, ProductTbl.product_img, ProductTbl.product_detail, StockTbl.stock FROM ProductTbl LEFT OUTER JOIN StockTbl ON ProductTbl.product_id = StockTbl.product_id)AS a WHERE a.category_id=?;";
				PreparedStatement pstmt = super.connection.prepareStatement(query);
				pstmt.setInt(1, category_id);
				ResultSet result = pstmt.executeQuery();
				////commit
				super.connection.commit();

				//結果の参照
				while(result.next()) {
					Item itemToList = new Item();
					itemToList.setProduct_id(result.getInt("product_id"));
					itemToList.setProduct_name(result.getString("product_name"));
					itemToList.setProduct_price(result.getInt("product_price"));
					itemToList.setProduct_img(result.getString("product_img"));
					itemToList.setProduct_detail(result.getString("product_detail"));
					itemToList.setStock(result.getInt("stock"));
					itemList.add(itemToList);
				}

				//クローズ処理もスタック構造を意識するとよい
				result.close();//一番上にのったResultSetを解放
				pstmt.close();//その直下のPreparedStatementを解放
				//super.disconnectConnection();//一番下の
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

}