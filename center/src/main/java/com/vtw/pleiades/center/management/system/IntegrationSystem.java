package com.vtw.pleiades.center.management.system;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtw.pleiades.center.management.server.IntegrationServer;

@Entity
public class IntegrationSystem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "system")
	private final List<IntegrationServer> servers = new ArrayList<>();

	public IntegrationSystem() {
	}
	
	public IntegrationSystem(String name, String description) {
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
	
	@JsonIgnore
	public List<IntegrationServer> getServers() {
		return servers;
	}
	
	public int getServersCount() {
		return servers.size();
	}

	@Override
	public String toString() {
		return "IntegrationSystem [id=" + id + ", name=" + name + ", description=" + description + ", servers="
				+ servers + "]";
	}
}
