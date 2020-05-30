package table;

import java.util.List;
import java.util.Map;

public class Rows {
	private List<Map<String,String>> cells;
	private boolean divideAfter;

	public boolean isDivideAfter() {
		return divideAfter;
	}

	public void setDivideAfter(boolean divideAfter) {
		this.divideAfter = divideAfter;
	}

	public List<Map<String, String>> getCells() {
		return cells;
	}

	public void setCells(List<Map<String, String>> cells) {
		this.cells = cells;
	}




	

}
