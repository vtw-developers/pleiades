package com.vtw.pleiades.center.management.route;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RouteRepository extends PagingAndSortingRepository<Route, Long>, JpaSpecificationExecutor<RouteView> {
	
	Page<RouteView> findAllByNameContainsOrAgent_NameContains(String name, String systemName, Pageable pageable);
	
	Page<RouteView> findAllByNameContainsAndDescriptionContainsAndAgent_NameContains(String name, String description, String systemName, Pageable pageable);
	
	List<Route> findAllByName(String name);
}