package table;

import java.util.List;
import java.util.Map;

public class TableCard {
	private List<Rows> rows;
	private List<Map<String,String>> columnProperties;
	
	public List<Map<String, String>> getColumnProperties() {
		return columnProperties;
	}

	public void setColumnProperties(List<Map<String,String>> cp_list) {
		this.columnProperties = cp_list;
	}

	public List<Rows> getRows() {
		return rows;
	}

	public void setRows(List<Rows> rows) {
		this.rows = rows;
	}


	
}
