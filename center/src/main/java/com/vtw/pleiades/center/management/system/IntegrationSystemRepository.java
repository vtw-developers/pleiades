package com.vtw.pleiades.center.management.system;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IntegrationSystemRepository extends PagingAndSortingRepository<IntegrationSystem, Long>, JpaSpecificationExecutor<IntegrationSystem> {
	
	Page<IntegrationSystemView> findAllByNameContains(String name, Pageable pageable);
	
	List<IntegrationSystem> findAllByName(String name);
}