package model;

import java.io.Serializable;

public class NowPage implements Serializable{
	private int count = 1;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public NowPage(int param) {this.count = param;}
	public NowPage() {}
}