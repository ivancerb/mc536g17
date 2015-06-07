package br.unicamp.ic.mc536.grupo17.dao.filter;



public class CompoundSearchFilter extends SearchFilter{
	
	private String table;
	private boolean isJoin;
	
	public CompoundSearchFilter(){
		
	}
	
	public CompoundSearchFilter(String table, String column, String filter, Object value, boolean isJoin) {
		super(column, filter, value);
		this.table = table;
		this.isJoin = isJoin;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public boolean isJoin() {
		return isJoin;
	}

	public void setJoin(boolean isJoin) {
		this.isJoin = isJoin;
	}	
	
	
}
