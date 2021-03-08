package com.vtw.pleiades.center.management.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtw.pleiades.center.management.agent.Agent;
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
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "server")
	private final List<Agent> agents = new ArrayList<>();

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
	
	@JsonIgnore
	public List<Agent> getAgents() {
		return agents;
	}

	@Override
	public String toString() {
		return "IntegrationServer [id=" + id + ", name=" + name + ", description=" + description + ", system=" + system
				+ "]";
	}
}
