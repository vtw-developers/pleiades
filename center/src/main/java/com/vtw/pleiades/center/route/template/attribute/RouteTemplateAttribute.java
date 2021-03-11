package com.vtw.pleiades.center.route.template.attribute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.vtw.pleiades.center.route.template.RouteTemplate;

@Entity
public class RouteTemplateAttribute {

	@Id
	private String id;

	@Column(unique = true)
	private String name;

	private String description;
	
	@ManyToOne(targetEntity = RouteTemplate.class)
	private RouteTemplate template;

	public RouteTemplateAttribute() {
	}
	
	public RouteTemplateAttribute(String id, String name, String description) {
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
	
	public RouteTemplate getTemplate() {
		return template;
	}

	public void setTemplate(RouteTemplate template) {
		this.template = template;
	}

	@Override
	public String toString() {
		return "RouteTemplate [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
}
