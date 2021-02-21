package com.vtw.pleiades.center.management.system;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IntegrationSystemRepository extends PagingAndSortingRepository<IntegrationSystem, Long>, JpaSpecificationExecutor<IntegrationSystem> {
}