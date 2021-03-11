package com.vtw.pleiades.center.route.template.attribute;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface RouteTemplateAttributeRepository extends PagingAndSortingRepository<RouteTemplateAttribute, String> {
	List<RouteTemplateAttribute> findAllByName(String name);
}