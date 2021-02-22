package com.vtw.pleiades.center.management.server;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.vtw.pleiades.center.management.system.IntegrationSystem;

@Entity
public class IntegrationServer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String description;
	
	@ManyToOne(targetEntity = IntegrationSystem.class)
	private IntegrationSystem system;

	public IntegrationServer() {
	}
	
	public IntegrationServer(IntegrationSystem system, String name, String description) {
		this.system = system;
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public IntegrationSystem getSystem() {
		return system;
	}

	public void setSystem(IntegrationSystem system) {
		this.system = system;
	}
	
	public String getSystemName() {
		return system.getName();
	}

	@Override
	public String toString() {
		return "IntegrationServer [id=" + id + ", name=" + name + ", description=" + description + ", system=" + system
				+ "]";
	}
}
