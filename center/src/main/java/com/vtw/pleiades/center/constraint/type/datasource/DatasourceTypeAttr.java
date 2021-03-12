package com.vtw.pleiades.center.constraint.type.datasource;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
@IdClass(DatasourceTypeAttrId.class)
public class DatasourceTypeAttr implements Serializable {

	private static final long serialVersionUID = -6233890792143349909L;

	@ManyToOne(targetEntity = DatasourceType.class)
	@MapsId("id")
	@Id
	private DatasourceType datasourceType;
	
	/**
	 * 속성명: 백엔드 프로그램에서 사용되는 것으로 영문명으로 입력
	 */
	@Id
	private String name;
	
	/**
	 * 속성유형
	 */
	private String type;
	
	/**
	 * 한글명
	 */
	private String localeName;
	
	public DatasourceTypeAttr() {
	}

	public DatasourceTypeAttr(DatasourceType datasourceType, String name, String type) {
		this.datasourceType = datasourceType;
		this.name = name;
		this.type = type;
	}

	public DatasourceType getDataSourceType() {
		return datasourceType;
	}

	public void setDataSourceType(DatasourceType datasourceType) {
		this.datasourceType = datasourceType;
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
	
	public String getLocaleName() {
		return localeName;
	}

	public void setLocaleName(String localeName) {
		this.localeName = localeName;
	}

	@Override
	public String toString() {
		return "DatasourceTypeAttr [datasourceType=" + datasourceType + ", name=" + name + ", type=" + type
				+ ", localeName=" + localeName + "]";
	}
}
