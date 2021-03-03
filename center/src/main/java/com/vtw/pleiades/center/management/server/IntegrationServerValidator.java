package com.vtw.pleiades.center.management.server;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtw.pleiades.center.common.web.validation.ValidationResult;
import com.vtw.pleiades.center.management.CrudValidator;
import com.vtw.pleiades.center.management.system.IntegrationSystemRepository;

@Service
public class IntegrationServerValidator implements CrudValidator<IntegrationServer, Long> {

	@Autowired
	private IntegrationServerRepository repository;
	
	@Autowired
	private IntegrationSystemRepository systemRepository;
	
	@Override
	public ValidationResult validate(IntegrationServer server) {
		// 부모인 시스템이 존재하는지 검사. 없으면 오류
		ValidationResult hasParent = validateHasParent(server);
		if (hasParent.isInvalid()) {
			return hasParent;
		}
		
		// 서버명이 이미 존재하는지 검사. 있으면 오류
		return validateNotExistName(server);
	}
	
	@Override
	public ValidationResult validate(Long id, IntegrationServer server) {
		ValidationResult hasParent = validateHasParent(server);
		if (hasParent.isInvalid()) {
			return hasParent;
		}
		ValidationResult notExistName = validateNotExistName(id, server);
		return notExistName;
	}
	
	private ValidationResult validateHasParent(IntegrationServer server) {
		if (server.getSystem() == null || server.getSystem().getId() == null) {
			return ValidationResult.invalid("orphan,system");
		}
		
		boolean exist = systemRepository.existsById(server.getSystem().getId());
		if (!exist) {
			return ValidationResult.invalid("orphan,system");
		}
		return ValidationResult.valid();
	}
	
	private ValidationResult validateNotExistName(IntegrationServer server) {
		boolean exist = exist(server.getName());
		if (exist) {
			return ValidationResult.invalid("exist,name");
		}
		return ValidationResult.valid();
	}
	
	private ValidationResult validateNotExistName(Long id, IntegrationServer server) {
		boolean exist = exist(id, server.getName());
		if (exist) {
			return ValidationResult.invalid("exist,name");
		}
		return ValidationResult.valid();
	}
	
	private boolean exist(String name) {
		List<IntegrationServer> servers = repository.findAllByName(name);
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
		IntegrationServer oldServer = repository.findById(id).get();
		if (StringUtils.equals(oldServer.getName(), name)) {
			return false;
		}
		
		List<IntegrationServer> servers = repository.findAllByName(name);
		return servers.size() > 0;
	}
}
