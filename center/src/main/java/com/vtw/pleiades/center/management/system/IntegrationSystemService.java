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
package com.vtw.pleiades.center.management.system;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vtw.pleiades.center.common.web.validation.ValidationResult;
import com.vtw.pleiades.center.management.server.IntegrationServer;

@Service
public class IntegrationSystemService {

	@Autowired
	private IntegrationSystemRepository repository;
	
	@Autowired
	private IntegrationSystemValidator validator;
	
	public Page<IntegrationSystem> list(Pageable pageable, String name, String description) {
		return repository.findAll(IntegrationSystemSpecs.filter(name, description), pageable);
	}
	
	public IntegrationSystem get(Long id) {
		return repository.findById(id).get();
	}
	
	public List<IntegrationSystem> listAll() {
		return IterableUtils.toList(repository.findAll());
	}

	public List<IntegrationServer> getServers(Long id) {
		return repository.findById(id).get().getServers();
	}
	
	public ValidationResult createWithValidation(IntegrationSystem system) {
		ValidationResult validation = validator.validate(system);
		if (validation.isValid()) {
			create(system);
		}
		return validation;
	}
	
	public ValidationResult updateWithValidation(Long id, IntegrationSystem system) {
		ValidationResult validation = validator.validate(id, system);
		if (validation.isValid()) {
			update(id, system);
		}
		return validation;
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public IntegrationSystem create(IntegrationSystem system) {
		return repository.save(system);
	}
	
	public IntegrationSystem update(Long id, IntegrationSystem newSystem) {
		IntegrationSystem oldSystem = repository.findById(id).orElse(newSystem);
		oldSystem.setName(newSystem.getName());
		oldSystem.setDescription(newSystem.getDescription());
		return repository.save(oldSystem);
	}
}
