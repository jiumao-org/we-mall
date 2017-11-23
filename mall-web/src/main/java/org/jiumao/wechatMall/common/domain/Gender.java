package org.jiumao.wechatMall.common.domain;

public enum Gender {
	
	MAN("男"),WOMEN("女"),UNKNOWN("保密");
	
	private String alias;

	private Gender(String alias) {
		this.alias = alias;
	}
	
	@Override
	public String toString() {
		return alias;
	}
	
}
