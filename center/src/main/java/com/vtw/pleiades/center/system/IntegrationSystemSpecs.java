package com.vtw.pleiades.center.system;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class IntegrationSystemSpecs {
	
    public static Specification<IntegrationSystem> like(String name, String description) {
        return (Specification<IntegrationSystem>) ((root, query, builder) -> {
	        List<Predicate> predicates = new ArrayList<>();
	        if (StringUtils.isBlank(name)) predicates.add(builder.like(root.get("name"), "%" + name + "%"));
	        if (StringUtils.isBlank(description))  predicates.add(builder.like(root.get("description"), "%" + description + "%"));
	        return builder.and(predicates.toArray(new Predicate[0]));
        });
    }
}