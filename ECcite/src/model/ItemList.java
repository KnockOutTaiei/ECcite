package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemList implements Serializable, Iterable<Item>{
	ArrayList<Item> itemList = new ArrayList<Item>();

	public ArrayList<Item> getItemList() {
		return itemList;
	}
	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
	public void add(Item item) {
		Item itemToAdd = new Item();
		itemToAdd.setProduct_id(item.getProduct_id());
		itemToAdd.setCategory_id(item.getCategory_id());
		itemToAdd.setProduct_name(item.getProduct_name());
		itemToAdd.setProduct_price(item.getProduct_price());
		itemToAdd.setProduct_img(item.getProduct_img());
		itemToAdd.setProduct_detail(item.getProduct_detail());
		itemToAdd.setStock(item.getStock());
		itemList.add(itemToAdd);
	}

	public ItemList() {}
	@Override
	public Iterator<Item> iterator() {
		return itemList.iterator();
	}
}