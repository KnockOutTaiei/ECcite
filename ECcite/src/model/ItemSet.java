/*
 * Itemとその個数を一括で管理するためのクラス
 */
package model;

public class ItemSet{
	Item item;
	int stockInCart;
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getStockInCart() {
		return stockInCart;
	}
	public void setStockInCart(int stockInCart) {
		this.stockInCart = stockInCart;
	}
	public ItemSet(Item item, int stockInCart) {this.item = item;this.stockInCart = stockInCart;}
}