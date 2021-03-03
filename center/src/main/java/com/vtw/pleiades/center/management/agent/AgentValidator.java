package com.vtw.pleiades.center.management.agent;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtw.pleiades.center.common.web.validation.ValidationResult;
import com.vtw.pleiades.center.management.CrudValidator;
import com.vtw.pleiades.center.management.server.IntegrationServerRepository;

@Service
public class AgentValidator implements CrudValidator<Agent, Long> {

	@Autowired
	private AgentRepository repository;
	
	@Autowired
	private IntegrationServerRepository serverRepository;
	
	@Override
	public ValidationResult validate(Agent server) {
		// 부모인 시스템이 존재하는지 검사. 없으면 오류
		ValidationResult hasParent = validateHasParent(server);
		if (hasParent.isInvalid()) {
			return hasParent;
		}
		
		// 서버명이 이미 존재하는지 검사. 있으면 오류
		return validateNotExistName(server);
	}
	
	@Override
	public ValidationResult validate(Long id, Agent server) {
		ValidationResult hasParent = validateHasParent(server);
		if (hasParent.isInvalid()) {
			return hasParent;
		}
		ValidationResult notExistName = validateNotExistName(id, server);
		return notExistName;
	}
	
	private ValidationResult validateHasParent(Agent agent) {
		if (agent.getServer() == null || agent.getServer().getId() == null) {
			return ValidationResult.invalid("orphan,server");
		}
		
		boolean exist = serverRepository.existsById(agent.getServer().getId());
		if (!exist) {
			return ValidationResult.invalid("orphan,server");
		}
		return ValidationResult.valid();
	}
	
	private ValidationResult validateNotExistName(Agent server) {
		boolean exist = exist(server.getName());
		if (exist) {
			return ValidationResult.invalid("exist,name");
		}
		return ValidationResult.valid();
	}
	
	private ValidationResult validateNotExistName(Long id, Agent server) {
		boolean exist = exist(id, server.getName());
		if (exist) {
			return ValidationResult.invalid("exist,name");
		}
		return ValidationResult.valid();
	}
	
	private boolean exist(String name) {
		List<Agent> servers = repository.findAllByName(name);
		return servers.size() > 0;
	}
	
	/**
	 * 변경 시 서버명 중복검사
	 * 
	 * @param id 변경대상 서버ID
	 * @param name 변경되는 서버명
	 * @return 이미 존재하는 서버명일 경우 true
	 */
	private boolean exist(Long id, String name) {
		// 서버명을 변경하지 않았을 경우 false를 리턴
		Agent oldServer = repository.findById(id).get();
		if (StringUtils.equals(oldServer.getName(), name)) {
			return false;
		}
		
		List<Agent> servers = repository.findAllByName(name);
		return servers.size() > 0;
	}
}
