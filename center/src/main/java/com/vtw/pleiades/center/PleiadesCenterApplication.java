package com.vtw.pleiades.center;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.vtw.pleiades.center.management.server.IntegrationServer;
import com.vtw.pleiades.center.management.server.IntegrationServerRepository;
import com.vtw.pleiades.center.management.system.IntegrationSystem;
import com.vtw.pleiades.center.management.system.IntegrationSystemRepository;

@SpringBootApplication
public class PleiadesCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PleiadesCenterApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(IntegrationSystemRepository systemRepo, IntegrationServerRepository serverRepo) throws Exception {
		return (args) -> {
			IntegrationSystem system = new IntegrationSystem("테스트 연계시스템", "시스템 설명입니다.");
			systemRepo.save(system);
			
			IntegrationServer server = new IntegrationServer(system, "테스트 연계서버", "서버 설명입니다.");
			serverRepo.save(server);
			
			PageRequest pageable = PageRequest.of(0, 10, Sort.by(Arrays.asList(Sort.Order.asc("name"))));
			System.out.println(serverRepo.findByNameContainsAndDescriptionContainsAndSystem_NameContains("연계", "설명", "시스템", pageable)); 
//			for (int i=0; i < 105; i++) {
//			IntegrationSystem system = new IntegrationSystem("테스트 연계시스템" + i, "설명한다.");
//			systemRepo.save(system);
//			}
//			
//			IntegrationSystem system = new IntegrationSystem("테스트 연계시스템" + i, "설명한다.");
//			systemRepo.save(system);
//			
//			PageRequest pageable = PageRequest.of(0, 10, Sort.by(Arrays.asList(Sort.Order.asc("name"))));
//			Page<IntegrationSystem> systems = systemRepo.findAll(IntegrationSystemSpecs.filter("연계", "설명"), pageable);
//			System.out.println(systems.getContent());
		};
	}
}
