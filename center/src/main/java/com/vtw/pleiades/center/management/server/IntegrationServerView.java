package com.vtw.pleiades.center.management.server;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtw.pleiades.center.management.system.IntegrationSystem;

public interface IntegrationServerView {

	Long getId();

	String getName();
	
	@JsonIgnore
	IntegrationSystem getSystem();
	
	default String getSystemName() {
		return getSystem().getName();
	}

}