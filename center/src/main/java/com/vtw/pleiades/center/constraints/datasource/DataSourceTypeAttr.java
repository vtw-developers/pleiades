package com.vtw.pleiades.center.constraints.datasource;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(DataSourceTypeAttrId.class)
public class DataSourceTypeAttr implements Serializable {

	private static final long serialVersionUID = -6233890792143349909L;

	@ManyToOne(targetEntity = DataSourceType.class)
	@Id
	private DataSourceType dataSourceType;
	
	@Id
	private String name;
	
	private String type;
	
	private int position;
	
	public DataSourceTypeAttr() {
	}

	public DataSourceTypeAttr(DataSourceType dataSourceType, String name, String type, int position) {
		this.dataSourceType = dataSourceType;
		this.name = name;
		this.type = type;
		this.position = position;
	}

	public DataSourceType getDataSourceType() {
		return dataSourceType;
	}

	public void setDataSourceType(DataSourceType dataSourceType) {
		this.dataSourceType = dataSourceType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "DataSourceTypeAttr [dataSourceType=" + dataSourceType + ", name=" + name + ", type=" + type
				+ ", position=" + position + "]";
	}
}
