package com.vtw.pleiades.center.management.agent;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AgentRepository extends PagingAndSortingRepository<Agent, Long>, JpaSpecificationExecutor<AgentView> {
	
	Page<AgentView> findAllByNameContainsOrServer_NameContains(String name, String systemName, Pageable pageable);
	
	Page<AgentView> findAllByNameContainsAndDescriptionContainsAndServer_NameContains(String name, String description, String systemName, Pageable pageable);
	
	List<Agent> findAllByName(String name);
}