package com.vtw.pleiades.center.route.template;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RouteTemplateRepository extends PagingAndSortingRepository<RouteTemplate, String> {
	
	Page<RouteTemplateView> findAllByNameContains(String name, Pageable pageable);
	
	List<RouteTemplate> findAllByName(String name);
}