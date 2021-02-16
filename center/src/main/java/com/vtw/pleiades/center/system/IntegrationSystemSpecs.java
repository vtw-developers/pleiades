package com.vtw.pleiades.center.system;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class IntegrationSystemSpecs {
	
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";

	public static Specification<IntegrationSystem> filter(String name, String description) {
        return (Specification<IntegrationSystem>) ((root, query, builder) -> {
	        List<Predicate> predicates = new ArrayList<>();
	        if (StringUtils.isNotBlank(name)) predicates.add(builder.like(root.get(NAME), StringUtils.wrap(name, "%")));
	        if (StringUtils.isNotBlank(description))  predicates.add(builder.like(root.get(DESCRIPTION), StringUtils.wrap(description, "%")));
	        return builder.and(predicates.toArray(new Predicate[0]));
        });
    }
}