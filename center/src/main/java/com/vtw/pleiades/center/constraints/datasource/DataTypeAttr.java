package com.vtw.pleiades.center.constraints.datasource;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(DataTypeAttrId.class)
public class DataTypeAttr implements Serializable {

	private static final long serialVersionUID = -1962085258360742120L;

	@ManyToOne(targetEntity = DataType.class)
	@Id
	private DataType dataType;
	
	@Id
	private String name;
	
	private String type;
	
	private int position;
	
	public DataTypeAttr() {
	}

	public DataTypeAttr(DataType dataType, String name, String type, int position) {
		this.dataType = dataType;
		this.name = name;
		this.type = type;
		this.position = position;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
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
		return "DataTypeAttr [dataType=" + dataType + ", name=" + name + ", type=" + type + ", position=" + position
				+ "]";
	}
}
