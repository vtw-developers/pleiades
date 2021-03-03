package com.vtw.pleiades.center.management.agent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.vtw.pleiades.center.management.server.IntegrationServer;

@Entity
public class Agent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String description;
	
	@ManyToOne(targetEntity = IntegrationServer.class)
	private IntegrationServer server;
	
	public Agent() {
	}
	
	public Agent(IntegrationServer server, String name, String description) {
		this.server = server;
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

	public IntegrationServer getServer() {
		return server;
	}

	public void setServer(IntegrationServer server) {
		this.server = server;
	}

	@Override
	public String toString() {
		return "Agent [id=" + id + ", name=" + name + ", description=" + description + ", server=" + server + "]";
	}
}
