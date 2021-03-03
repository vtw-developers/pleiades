package com.vtw.pleiades.center.management.agent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtw.pleiades.center.management.server.IntegrationServer;

public interface AgentView {

	Long getId();

	String getName();
	
	String getDescription();
	
	@JsonIgnore
	IntegrationServer getServer();
	
	default String getServerName() {
		return getServer().getName();
	}
	
	default String getSystemName() {
		return getServer().getSystem().getName();
	}
}