package com.taobao.tddl.interact.bean;

import java.util.Collections;
import java.util.Map;

public class ReverseOutput {
	private String sql;
	private String table;
	private Map<Integer,Object> params=Collections.emptyMap();
	
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public Map<Integer,Object> getParams() {
		return params;
	}
	public void setParams(Map<Integer,Object> params) {
		this.params = params;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	
	
}
