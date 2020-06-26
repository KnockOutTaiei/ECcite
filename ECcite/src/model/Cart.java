/*
 * 商品カートを表現するJavaBeans
 * 消費税の計算もここに入る（＝カート自身が計算する）
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Cart implements Serializable, Iterable<ItemSet>{

	//カート：ItemSetの配列
	ArrayList<ItemSet> cart = new ArrayList<ItemSet>();

	public ArrayList<ItemSet> getCart() {
		return cart;
	}
	public void setCart(ArrayList<ItemSet> cart) {
		this.cart = cart;
	}
	public void add(Item item,int stockInCart) {
		Item itemToAdd = new Item();
		itemToAdd.setProduct_id(item.getProduct_id());
		itemToAdd.setCategory_id(item.getCategory_id());
		itemToAdd.setProduct_name(item.getProduct_name());
		itemToAdd.setProduct_price(item.getProduct_price());
		itemToAdd.setProduct_img(item.getProduct_img());
		itemToAdd.setProduct_detail(item.getProduct_detail());
		ItemSet itemSetToAdd = new ItemSet(itemToAdd,stockInCart);
		cart.add(itemSetToAdd);
	}
	public long calcConsumptionTax(){
		double tax = 0.10;
		long sum = 0;

		for(ItemSet itemset:cart)
			sum+=itemset.getItem().getProduct_price() * itemset.getStockInCart();

		return (long)Math.round(sum*tax);
	}
	public long calcTotal(){
		double taxPlusOne = 1.10;
		long sum = 0;

		for(ItemSet itemset:cart)
			sum+=itemset.getItem().getProduct_price() * itemset.getStockInCart();

		return (long)Math.round(sum*taxPlusOne);
	}
	public void erase() {
		cart.clear();
	}

	public Cart() {}
	@Override
	public Iterator<ItemSet> iterator() {
		return cart.iterator();
	}
}