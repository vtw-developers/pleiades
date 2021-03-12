package com.vtw.pleiades.center.constraint.type.datasource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
public class DataType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "dataType", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderColumn(name = "position")
	private final List<DataTypeAttr> attrs = new ArrayList<>();
	
	@ManyToMany
	private final Set<DatasourceType> dataSourceTypes = new HashSet<>();
	
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
		final List<DataTypeAttr> list = new ArrayList<>();
		list.addAll(attrs);
		return attrs;
	}
	
	public void setAttrs(List<DataTypeAttr> attrs) {
		this.attrs.clear();
		this.attrs.addAll(attrs);
		attrs.forEach(attr -> attr.setDataType(this));
	}
	
	public void addAttr(DataTypeAttr attr) {
		attrs.add(attr);
	}
	
	public void removeAttr(DataTypeAttr attr) {
		attrs.remove(attr);
	}

	public Set<DatasourceType> getDataSourceTypes() {
		return dataSourceTypes;
	}

	@Override
	public String toString() {
		return "DataType [id=" + id + ", name=" + name + ", attrs=" + attrs + ", dataSourceTypes=" + dataSourceTypes
				+ "]";
	}
}
