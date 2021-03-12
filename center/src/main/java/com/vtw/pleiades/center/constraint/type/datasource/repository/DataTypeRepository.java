package com.vtw.pleiades.center.constraint.type.datasource.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.vtw.pleiades.center.constraint.type.datasource.DataType;
import com.vtw.pleiades.center.constraint.type.datasource.view.DataTypeListView;

public interface DataTypeRepository extends PagingAndSortingRepository<DataType, Long> {
	
	Page<DataTypeListView> findAllByNameContains(String name, Pageable pageable);
	
	List<DataType> findAllByName(String name);
}