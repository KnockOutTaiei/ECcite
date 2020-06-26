package model;
import java.io.Serializable;
public class EveryHistory implements Serializable{
	String product_name;
	String stock;
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
}