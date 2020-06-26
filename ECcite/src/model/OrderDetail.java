package model;
import java.io.Serializable;
import java.time.LocalDateTime;

public class OrderDetail implements Serializable{
	int order_id;
	int voucher_id;
	int product_id;
	int stock;
	int amount;
	double tax;
	boolean del_flg;
	LocalDateTime ins_date;
	LocalDateTime upd_date;

	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getVoucher_id() {
		return voucher_id;
	}
	public void setVoucher_id(int voucher_id) {
		this.voucher_id = voucher_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public boolean isDel_flg() {
		return del_flg;
	}
	public void setDel_flg(boolean del_flg) {
		this.del_flg = del_flg;
	}
	public LocalDateTime getIns_date() {
		return ins_date;
	}
	public void setIns_date(LocalDateTime ins_date) {
		this.ins_date = ins_date;
	}
	public LocalDateTime getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(LocalDateTime upd_date) {
		this.upd_date = upd_date;
	}
}