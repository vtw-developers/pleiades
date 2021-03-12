package com.vtw.pleiades.center.constraint.type.datasource;

import java.io.Serializable;

public class DatasourceTypeAttrId implements Serializable {
	
	private static final long serialVersionUID = -9135835326906766061L;

	private Long datasourceType;

    private String name;

	public DatasourceTypeAttrId() {
	}
	
	public DatasourceTypeAttrId(Long datasourceType, String name) {
		this.datasourceType = datasourceType;
		this.name = name;
	}

	public Long getDataSourceType() {
		return datasourceType;
	}

	public void setDataSourceType(Long datasourceType) {
		this.datasourceType = datasourceType;
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
		result = prime * result + ((datasourceType == null) ? 0 : datasourceType.hashCode());
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
		DatasourceTypeAttrId other = (DatasourceTypeAttrId) obj;
		if (datasourceType == null) {
			if (other.datasourceType != null)
				return false;
		} else if (!datasourceType.equals(other.datasourceType))
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
		return "DataSourceTypeAttrId [datasourceType=" + datasourceType + ", name=" + name + "]";
	}
}
