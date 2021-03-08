package com.vtw.pleiades.center.route.template;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RouteTemplate {

	@Id
	private String id;

	@Column(unique = true)
	private String name;

	private String description;

	public RouteTemplate() {
	}
	
	public RouteTemplate(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RouteTemplate [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
}
