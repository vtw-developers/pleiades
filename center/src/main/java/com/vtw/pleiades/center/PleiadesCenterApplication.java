package com.vtw.pleiades.center;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.vtw.pleiades.center.system.IntegrationSystem;
import com.vtw.pleiades.center.system.IntegrationSystemRepository;
import com.vtw.pleiades.center.system.IntegrationSystemSpecs;

@SpringBootApplication
public class PleiadesCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PleiadesCenterApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner run(IntegrationSystemRepository systemRepo) throws Exception {
//		return (args) -> {
//			IntegrationSystem system = new IntegrationSystem();
//			system.setName("테스트 연계시스템");
//			system.setDescription("설명한다.");
//			systemRepo.save(system);
//			
//			PageRequest pageable = PageRequest.of(0, 10, Sort.by(Arrays.asList(Sort.Order.asc("name"))));
//			Page<IntegrationSystem> systems = systemRepo.findAll(IntegrationSystemSpecs.filter("연계", "설명"), pageable);
//			System.out.println(systems.getContent());
//		};
//	}
}
