package br.unicamp.ic.mc536.grupo17.dao.filter;


public class SearchFilter {
	private String column;
	private String filter;
	private Object value;
	
	public SearchFilter(){
		
	}
	
	public SearchFilter(String column, String filter, Object value) {
		super();
		this.column = column;
		this.filter = filter;
		this.value = value;
	}

	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	
}
