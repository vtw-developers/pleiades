package com.vtw.pleiades.center.management.agent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtw.pleiades.center.management.system.IntegrationSystem;

public interface AgentView {

	Long getId();

	String getName();
	
	String getDescription();
	
	@JsonIgnore
	IntegrationSystem getSystem();
	
	default Long getSystemId() {
		return getSystem().getId();
	}
	
	default String getSystemName() {
		return getSystem().getName();
	}

}