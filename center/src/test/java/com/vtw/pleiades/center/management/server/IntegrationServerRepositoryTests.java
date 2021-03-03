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

package com.vtw.pleiades.center.management.server;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.vtw.pleiades.center.management.system.IntegrationSystem;

@DataJpaTest
public class IntegrationServerRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private IntegrationServerRepository repository;

	@Test
	public void testSave() {
		IntegrationSystem system = new IntegrationSystem("테스트 연계시스템", "시스템 설명입니다.");
		entityManager.persist(system);
		
		IntegrationServer savedServer = new IntegrationServer(system, "테스트 연계서버", "서버 설명입니다.");
		repository.save(savedServer);
		
		IntegrationServer foundServer = repository.findById(savedServer.getId()).get();
		
		assertThat(foundServer.getName()).isEqualTo("테스트 연계서버");
	}
	
	@Test
	public void testFindByFilterSimple() {
		IntegrationSystem system = new IntegrationSystem("테스트 연계시스템", "시스템 설명입니다.");
		entityManager.persist(system);
		
		IntegrationServer server = new IntegrationServer(system, "테스트 연계서버", "서버 설명입니다.");
		entityManager.persist(server);

		PageRequest pageable = PageRequest.of(0, 10, Sort.by(Arrays.asList(Sort.Order.asc("name"))));
		Page<IntegrationServerView> servers = repository.findAllByNameContainsAndSystem_NameContains("서버", "시스템", pageable);
		
		assertThat(servers).extracting(IntegrationServerView::getName).containsOnly(server.getName());
	}
//	
//	@Test
//	public void testFindAllFiltering() {
//		IntegrationServer server = new IntegrationServer("테스트 연계시스템", "시스템 설명입니다.");
//		IntegrationServer server2 = new IntegrationServer("테스트 연계시스템2", "시스템 설명입니다2.");
//		IntegrationServer server3 = new IntegrationServer("테스트 시스템3", "조회 조건에 맞지 않는 데이터");
//		IntegrationServer server4 = new IntegrationServer("조회 조건에 맞지 않는 데이터4", "시스템 설명입니다4.");
//		IntegrationServer server5 = new IntegrationServer("조회 조건에 맞지 않는 데이터5", "조회 조건에 맞지 않는 데이터5");
//		entityManager.persist(server);
//		entityManager.persist(server2);
//		entityManager.persist(server3);
//		entityManager.persist(server4);
//		entityManager.persist(server5);
//
//		PageRequest pageable = PageRequest.of(0, 10, Sort.by(Arrays.asList(Sort.Order.asc("name"))));
//		Page<IntegrationServer> servers = repository.findAll(IntegrationServerSpecs.filter("연계", "설명"), pageable);
//
//		assertThat(servers).size().isEqualTo(2);
//	}
//	
//	@Test
//	public void testFindAllPageing() {
//		for (int i=0; i < 15; i++) {
//			IntegrationServer server = new IntegrationServer("테스트 연계시스템" + i, "시스템 설명입니다." + i);
//			entityManager.persist(server);
//		}
//
//		PageRequest pageable = PageRequest.of(0, 10, Sort.by(Arrays.asList(Sort.Order.asc("name"))));
//		Page<IntegrationServer> servers = repository.findAll(IntegrationServerSpecs.filter("연계", "설명"), pageable);
//
//		assertThat(servers).size().isEqualTo(10);
//	}
}
