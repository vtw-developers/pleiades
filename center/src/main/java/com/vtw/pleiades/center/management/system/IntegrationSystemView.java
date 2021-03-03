package com.vtw.pleiades.center.management.system;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtw.pleiades.center.management.server.IntegrationServer;

public interface IntegrationSystemView {

	Long getId();

	String getName();
	
	String getDescription();
	
	@JsonIgnore
	List<IntegrationServer> getServers();
	
	default int getServersCount() {
		return getServers().size();
	}

}