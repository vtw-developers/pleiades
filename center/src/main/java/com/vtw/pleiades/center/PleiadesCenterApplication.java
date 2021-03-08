package com.vtw.pleiades.center;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.vtw.pleiades.center.management.agent.Agent;
import com.vtw.pleiades.center.management.agent.AgentRepository;
import com.vtw.pleiades.center.management.route.Route;
import com.vtw.pleiades.center.management.route.RouteRepository;
import com.vtw.pleiades.center.management.server.IntegrationServer;
import com.vtw.pleiades.center.management.server.IntegrationServerRepository;
import com.vtw.pleiades.center.management.system.IntegrationSystem;
import com.vtw.pleiades.center.management.system.IntegrationSystemRepository;
import com.vtw.pleiades.center.route.template.RouteTemplate;
import com.vtw.pleiades.center.route.template.RouteTemplateRepository;

@SpringBootApplication
public class PleiadesCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PleiadesCenterApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(
			IntegrationSystemRepository systemRepo, 
			IntegrationServerRepository serverRepo, 
			AgentRepository agentRepository,
			RouteRepository routeRepository,
			RouteTemplateRepository templateRepository) throws Exception {
		return (args) -> {
			create(systemRepo, serverRepo, agentRepository, routeRepository, templateRepository);
			create2(systemRepo, serverRepo, agentRepository, routeRepository, templateRepository);
			

			
//			PageRequest pageable = PageRequest.of(0, 10, Sort.by(Arrays.asList(Sort.Order.asc("name"))));
//			System.out.println(serverRepo.findAllByNameContainsAndDescriptionContainsAndSystem_NameContains("연계", "설명", "시스템", pageable));
			
			
			
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
	
	private static void create(IntegrationSystemRepository systemRepo, 
			IntegrationServerRepository serverRepo, 
			AgentRepository agentRepository,
			RouteRepository routeRepository,
			RouteTemplateRepository templateRepository) {
		IntegrationSystem system = new IntegrationSystem("테스트 연계시스템", "시스템 설명입니다.");
		systemRepo.save(system);
		
		IntegrationServer server = new IntegrationServer(system, "테스트 연계서버", "서버 설명입니다.");
		serverRepo.save(server);
		
		Agent agent = new Agent(server, "테스트 에이전트", "에이전트 설명입니다.");
		agentRepository.save(agent);
		
		Route route = new Route(agent, "테스트 라우트", "라우트 설명입니다.");
		routeRepository.save(route);
		
		Agent agent2 = new Agent(server, "테스트 에이전트2", "에이전트 설명입니다.");
		agentRepository.save(agent2);
		
		IntegrationServer server2 = new IntegrationServer(system, "테스트 연계서버2", "서버 설명입니다.");
		serverRepo.save(server2);
		
		Agent agent3 = new Agent(server2, "테스트 에이전트3", "에이전트 설명입니다.");
		agentRepository.save(agent3);
		
		Route route2 = new Route(agent3, "테스트 라우트2", "라우트 설명입니다.");
		routeRepository.save(route2);
		
		
		RouteTemplate template = new RouteTemplate("test-template-001", "테스트 라우트 템플릿", "라우트템플릿 설명.");
		templateRepository.save(template);
	}
	
	private static void create2(IntegrationSystemRepository systemRepo, 
			IntegrationServerRepository serverRepo, 
			AgentRepository agentRepository,
			RouteRepository routeRepository,
			RouteTemplateRepository templateRepository) {
		IntegrationSystem system = new IntegrationSystem("국가기준데이터 관리시스템", "시스템 설명입니다.");
		systemRepo.save(system);
		
		IntegrationServer server = new IntegrationServer(system, "수집제공서버1", "서버 설명입니다.");
		serverRepo.save(server);
		
		IntegrationServer server2 = new IntegrationServer(system, "수집제공서버2", "서버 설명입니다.");
		serverRepo.save(server2);
		
		IntegrationServer server3 = new IntegrationServer(system, "수집제공서버3", "서버 설명입니다.");
		serverRepo.save(server3);
		
		Agent agent = new Agent(server, "수집 에이전트", "에이전트 설명입니다.");
		agentRepository.save(agent);
		
		Agent agent2 = new Agent(server2, "수집 에이전트2", "에이전트 설명입니다.");
		agentRepository.save(agent2);
		
		Agent agent3 = new Agent(server3, "수집 에이전트3", "에이전트 설명입니다.");
		agentRepository.save(agent3);
		
		Route route = new Route(agent, "자격면허 수집 라우트", "라우트 설명입니다.");
		routeRepository.save(route);
		
		Route route2 = new Route(agent, "상품 수집 라우트", "라우트 설명입니다.");
		routeRepository.save(route2);
		
		Route route3 = new Route(agent, "유해위험물 수집 라우트", "라우트 설명입니다.");
		routeRepository.save(route3);
	}
}
