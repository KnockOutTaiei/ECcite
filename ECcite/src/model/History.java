package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class History implements Serializable, Iterable<EveryHistory>{

	ArrayList<EveryHistory>history = new ArrayList<EveryHistory>();

	public ArrayList<EveryHistory> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<EveryHistory> history) {
		this.history = history;
	}

	public void add(EveryHistory everyHistory) {
		history.add(everyHistory);
	}
	public History() {}

	@Override
	public Iterator<EveryHistory> iterator() {
		return history.iterator();
	}
}