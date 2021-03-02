package com.vtw.pleiades.center.management.route;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.vtw.pleiades.center.management.agent.Agent;

@Entity
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String description;
	
	@ManyToOne(targetEntity = Agent.class)
	private Agent agent;

	public Route() {
	}
	
	public Route(Agent agent, String name, String description) {
		this.agent = agent;
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

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	@Override
	public String toString() {
		return "Route [id=" + id + ", name=" + name + ", description=" + description + ", agent=" + agent + "]";
	}
}
