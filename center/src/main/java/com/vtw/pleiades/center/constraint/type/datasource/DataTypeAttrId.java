package com.vtw.pleiades.center.constraint.type.datasource;

import java.io.Serializable;

public class DataTypeAttrId implements Serializable {
	
	private static final long serialVersionUID = 7254630034663276878L;

	private Long dataType;

    private String name;

	public DataTypeAttrId() {
	}

	public DataTypeAttrId(Long dataType, String name) {
		this.dataType = dataType;
		this.name = name;
	}

	public Long getDataType() {
		return dataType;
	}

	public void setDataType(Long dataType) {
		this.dataType = dataType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataType == null) ? 0 : dataType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataTypeAttrId other = (DataTypeAttrId) obj;
		if (dataType == null) {
			if (other.dataType != null)
				return false;
		} else if (!dataType.equals(other.dataType))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DataTypeAttrId [dataType=" + dataType + ", name=" + name + "]";
	}
}
