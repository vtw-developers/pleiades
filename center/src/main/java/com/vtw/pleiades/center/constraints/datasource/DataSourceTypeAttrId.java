package com.vtw.pleiades.center.constraints.datasource;

import java.io.Serializable;

public class DataSourceTypeAttrId implements Serializable {
	
	private static final long serialVersionUID = -9135835326906766061L;

	private Long dataSourceType;

    private String name;

	public DataSourceTypeAttrId() {
	}
	
	public DataSourceTypeAttrId(Long dataSourceType, String name) {
		this.dataSourceType = dataSourceType;
		this.name = name;
	}

	public Long getDataSourceType() {
		return dataSourceType;
	}

	public void setDataSourceType(Long dataSourceType) {
		this.dataSourceType = dataSourceType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "DataSourceTypeAttrId [dataSourceType=" + dataSourceType + ", name=" + name + "]";
	}
}
