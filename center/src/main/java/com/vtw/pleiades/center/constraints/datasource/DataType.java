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
public class DataType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dataType")
	private final List<DataTypeAttr> attrs = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	private final List<DataSourceType> dataSourceTypes = new ArrayList<>();
	
	public DataType() {
	}

	public DataType(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<DataTypeAttr> getAttrs() {
		return attrs;
	}

	public List<DataSourceType> getDataSourceTypes() {
		return dataSourceTypes;
	}

	@Override
	public String toString() {
		return "DataType [id=" + id + ", name=" + name + ", attrs=" + attrs + ", dataSourceTypes=" + dataSourceTypes
				+ "]";
	}
}
