package org.jiumao.db.jdbcUtils;

public class SqlParam {
	public String param_name;
	public int param_type;
	public String param_value;
	
	public SqlParam(String param_name,int param_type,String param_value){
		this.param_name = param_name;
		this.param_type = param_type;
		this.param_value = param_value;
	}
	public String getParam_name() {
		return param_name;
	}
	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}
	public int getParam_type() {
		return param_type;
	}
	public void setParam_type(int param_type) {
		this.param_type = param_type; 
	}
	public String getParam_value() {
		return param_value;
	}
	public void setParam_value(String param_value) {
		this.param_value = param_value;
	}
	
	
}
