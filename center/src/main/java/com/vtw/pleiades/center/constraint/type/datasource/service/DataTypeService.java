/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vtw.pleiades.center.constraint.type.datasource.service;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.vtw.pleiades.center.common.web.validation.ValidationResult;
import com.vtw.pleiades.center.constraint.type.datasource.DataType;
import com.vtw.pleiades.center.constraint.type.datasource.repository.DataTypeRepository;
import com.vtw.pleiades.center.constraint.type.datasource.validator.DataTypeValidator;
import com.vtw.pleiades.center.constraint.type.datasource.view.DataTypeListView;

@Service
public class DataTypeService {

	@Autowired
	private DataTypeRepository repository;
	
	@Autowired
	private DataTypeValidator validator;
	
	public Page<DataTypeListView> list(Pageable pageable, @RequestParam(required = false, defaultValue = "") String name) {
		return repository.findAllByNameContains(name, pageable);
	}
	
	public DataType get(Long id) {
		return repository.findById(id).get();
	}
	
	public List<DataType> listAll() {
		return IterableUtils.toList(repository.findAll());
	}
	
	public ValidationResult createWithValidation(DataType dataType) {
		ValidationResult validation = validator.validate(dataType);
		if (validation.isValid()) {
			create(dataType);
		}
		return validation;
	}
	
	public ValidationResult updateWithValidation(Long id, DataType dataType) {
		ValidationResult validation = validator.validate(id, dataType);
		if (validation.isValid()) {
			update(id, dataType);
		}
		return validation;
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public DataType create(DataType dataType) {
		return repository.save(dataType);
	}
	
	public DataType update(Long id, DataType newOne) {
		DataType oldOne = repository.findById(id).orElse(newOne);
		oldOne.setName(newOne.getName());
		oldOne.setAttrs(newOne.getAttrs());
		return repository.save(oldOne);
	}
}
