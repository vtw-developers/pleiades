package com.vtw.pleiades.center.constraints.datasource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class DataSourceType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "dataSourceType", cascade = CascadeType.ALL)
	private final List<DataSourceTypeAttr> attrs = new ArrayList<>();

	@ManyToMany
	private final List<DataType> dataTypes = new ArrayList<>();

	public DataSourceType() {
	}

	public DataSourceType(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DataSourceTypeAttr> getAttrs() {
		return attrs;
	}

	public List<DataType> getDataTypes() {
		return dataTypes;
	}

	@Override
	public String toString() {
		return "DataSourceType [id=" + id + ", name=" + name + ", attrs=" + attrs + ", dataTypes=" + dataTypes + "]";
	}
}
