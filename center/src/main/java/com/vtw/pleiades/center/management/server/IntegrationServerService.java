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
package com.vtw.pleiades.center.management.server;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IntegrationServerService {

	@Autowired
	private IntegrationServerRepository repository;

	public Page<IntegrationServerView> list(String name, String description, String systemName, Pageable pageable) {
		return repository.findAllByNameContainsAndDescriptionContainsAndSystem_NameContains(name, description, systemName, pageable);
	}
	
	public IntegrationServer get(Long id) {
		return repository.findById(id).get();
	}
	
	public IntegrationServer create(IntegrationServer server) {
		return repository.save(server);
	}
	
	public IntegrationServer update(Long id, IntegrationServer newServer) {
		IntegrationServer oldSystem = repository.findById(id).orElse(newServer);
		oldSystem.setName(newServer.getName());
		oldSystem.setDescription(newServer.getDescription());
		return repository.save(oldSystem);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public boolean exist(String name) {
		List<IntegrationServer> systems = repository.findByName(name);
		return systems.size() > 0;
	}
	
	/**
	 * 변경 시 서버명 중복검사
	 * 
	 * @param id 변경대상 서버ID
	 * @param name 변경되는 서버명
	 * @return 이미 존재하는 서버명일 경우 true
	 */
	public boolean exist(Long id, String name) {
		// 서버명을 변경하지 않았을 경우 false를 리턴
		IntegrationServer oldServer = repository.findById(id).get();
		if (StringUtils.equals(oldServer.getName(), name)) {
			return false;
		}
		
		List<IntegrationServer> systems = repository.findByName(name);
		return systems.size() > 0;
	}
}
