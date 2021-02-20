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
package com.vtw.pleiades.center.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IntegrationSystemService {

	@Autowired
	private IntegrationSystemRepository repository;

	public Page<IntegrationSystem> getSystems(Pageable pageable, String name, String description) {
		return repository.findAll(IntegrationSystemSpecs.filter(name, description), pageable);
	}
	
	public IntegrationSystem getSystem(Long id) {
		return repository.findById(id).get();
	}
	
	public IntegrationSystem createSystem(IntegrationSystem system) {
		return repository.save(system);
	}
	
	public IntegrationSystem updateSystem(Long id, IntegrationSystem newSystem) {
		IntegrationSystem oldSystem = repository.findById(id).orElse(newSystem);
		oldSystem.setName(newSystem.getName());
		oldSystem.setDescription(newSystem.getDescription());
		return repository.save(oldSystem);
	}
}
