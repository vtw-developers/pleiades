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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DatasourceType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "datasourceType", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderColumn(name = "position")
	private final List<DatasourceTypeAttr> attrs = new ArrayList<>();

	@ManyToMany
	private final Set<DataType> dataTypes = new HashSet<>();

	public DatasourceType() {
	}

	public DatasourceType(String name) {
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

	@JsonIgnore
	public List<DatasourceTypeAttr> getAttrs() {
		final List<DatasourceTypeAttr> list = new ArrayList<>();
		list.addAll(attrs);
		return attrs;
	}
	
	public void setAttrs(List<DatasourceTypeAttr> attrs) {
		this.attrs.clear();
		this.attrs.addAll(attrs);
		attrs.forEach(attr -> attr.setDataSourceType(this));
	}
	
	public void addAttr(DatasourceTypeAttr attr) {
		attr.setDataSourceType(this);
		attrs.add(attr);
	}
	
	public void removeAttr(DatasourceTypeAttr attr) {
		attrs.remove(attr);
	}

	@JsonIgnore
	public Set<DataType> getDataTypes() {
		return dataTypes;
	}
	
	@Override
	public String toString() {
		return "DataSourceType [id=" + id + ", name=" + name + ", attrs=" + attrs + ", dataTypes=" + dataTypes + "]";
	}
}
