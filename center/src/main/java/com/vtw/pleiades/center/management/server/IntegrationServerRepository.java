package com.vtw.pleiades.center.management.server;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IntegrationServerRepository extends PagingAndSortingRepository<IntegrationServer, Long>, JpaSpecificationExecutor<IntegrationServerView> {
	
	Page<IntegrationServerView> findAllByNameContainsOrSystem_NameContains(String name, String systemName, Pageable pageable);
	
	Page<IntegrationServerView> findAllByNameContainsAndSystem_NameContains(String name, String systemName, Pageable pageable);
	
	List<IntegrationServer> findAllByName(String name);
}