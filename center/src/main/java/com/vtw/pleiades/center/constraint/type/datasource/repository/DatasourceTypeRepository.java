package com.vtw.pleiades.center.constraint.type.datasource.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.vtw.pleiades.center.constraint.type.datasource.DatasourceType;
import com.vtw.pleiades.center.constraint.type.datasource.view.DatasourceTypeListView;

public interface DatasourceTypeRepository extends PagingAndSortingRepository<DatasourceType, Long> {
	
	Page<DatasourceTypeListView> findAllByNameContains(String name, Pageable pageable);
	
	List<DatasourceType> findAllByName(String name);
}