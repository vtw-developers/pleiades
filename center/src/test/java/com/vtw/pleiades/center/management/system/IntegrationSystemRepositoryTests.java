/*
 * Copyright 2002-2016 the original author or authors.
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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@DataJpaTest
public class IntegrationSystemRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private IntegrationSystemRepository repository;

	@Test
	public void testCreate() {
		IntegrationSystem savedSystem = new IntegrationSystem("테스트 연계시스템", "시스템 설명입니다.");
		repository.save(savedSystem);
		IntegrationSystem foundSystem = repository.findById(savedSystem.getId()).get();
		
		assertThat(foundSystem.getName()).isEqualTo(savedSystem.getName());
	}
	
	@Test
	public void testFindAllSimple() {
		IntegrationSystem system = new IntegrationSystem("테스트 연계시스템", "시스템 설명입니다.");
		entityManager.persist(system);

		PageRequest pageable = PageRequest.of(0, 10, Sort.by(Arrays.asList(Sort.Order.asc("name"))));
		Page<IntegrationSystemView> systems = repository.findAllByNameContains("시스템", pageable);

		assertThat(systems).extracting(IntegrationSystemView::getName).containsOnly(system.getName());
	}
	
	@Test
	public void testFindAllFiltering() {
		IntegrationSystem system = new IntegrationSystem("테스트 연계시스템", "시스템 설명입니다.");
		IntegrationSystem system2 = new IntegrationSystem("테스트 연계시스템2", "시스템 설명입니다2.");
		IntegrationSystem system3 = new IntegrationSystem("테스트 시스템3", "조회 조건에 맞지 않는 데이터");
		IntegrationSystem system4 = new IntegrationSystem("조회 조건에 맞지 않는 데이터4", "시스템 설명입니다4.");
		IntegrationSystem system5 = new IntegrationSystem("조회 조건에 맞지 않는 데이터5", "조회 조건에 맞지 않는 데이터5");
		entityManager.persist(system);
		entityManager.persist(system2);
		entityManager.persist(system3);
		entityManager.persist(system4);
		entityManager.persist(system5);

		PageRequest pageable = PageRequest.of(0, 10, Sort.by(Arrays.asList(Sort.Order.asc("name"))));
		Page<IntegrationSystemView> systems = repository.findAllByNameContains("시스템", pageable);

		assertThat(systems).size().isEqualTo(2);
	}
	
	@Test
	public void testFindAllPageing() {
		for (int i=0; i < 15; i++) {
			IntegrationSystem system = new IntegrationSystem("테스트 연계시스템" + i, "시스템 설명입니다." + i);
			entityManager.persist(system);
		}

		PageRequest pageable = PageRequest.of(0, 10, Sort.by(Arrays.asList(Sort.Order.asc("name"))));
		Page<IntegrationSystemView> systems = repository.findAllByNameContains("시스템", pageable);

		assertThat(systems).size().isEqualTo(10);
	}
	
	@Test
	public void testSave() {
		IntegrationSystem system = new IntegrationSystem("테스트 연계시스템", "시스템 설명입니다.");
		system = repository.save(system);
		system.setDescription("설명 변경");
		repository.save(system);

		IntegrationSystem foundSystem = repository.findById(system.getId()).get();
		assertThat(foundSystem.getName()).isEqualTo(system.getName());
	}
}
