package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Order implements Serializable{
	int order_id;
	String acc_id;
	int whole_amount;
	LocalDateTime order_date;
	LocalDateTime limit_date;
	LocalDateTime confirm_date;
	String order_status;

	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getAcc_id() {
		return acc_id;
	}
	public void setAcc_id(String acc_id) {
		this.acc_id = acc_id;
	}
	public int getWhole_amount() {
		return whole_amount;
	}
	public void setWhole_amount(int whole_amount) {
		this.whole_amount = whole_amount;
	}
	public LocalDateTime getOrder_date() {
		return order_date;
	}
	public void setOrder_date(LocalDateTime order_date) {
		this.order_date = order_date;
	}
	public LocalDateTime getLimit_date() {
		return limit_date;
	}
	public void setLimit_date(LocalDateTime limit_date) {
		this.limit_date = limit_date;
	}
	public LocalDateTime getConfirm_date() {
		return confirm_date;
	}
	public void setConfirm_date(LocalDateTime confirm_date) {
		this.confirm_date = confirm_date;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
}