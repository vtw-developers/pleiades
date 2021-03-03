package com.vtw.pleiades.center.management.system;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtw.pleiades.center.common.web.validation.ValidationResult;
import com.vtw.pleiades.center.management.CrudValidator;

@Service
public class IntegrationSystemValidator implements CrudValidator<IntegrationSystem, Long> {

	@Autowired
	private IntegrationSystemRepository repository;
	
	@Override
	public ValidationResult validate(IntegrationSystem system) {
		ValidationResult notExistName = validateNotExistName(system);
		return notExistName;
	}
	
	@Override
	public ValidationResult validate(Long id, IntegrationSystem system) {
		ValidationResult notExistName = validateNotExistName(id, system);
		return notExistName;
	}
	
	public ValidationResult validateNotExistName(IntegrationSystem system) {
		boolean exist = exist(system.getName());
		if (exist) {
			return ValidationResult.invalid("exist,name");
		}
		return ValidationResult.valid();
	}
	
	public ValidationResult validateNotExistName(Long id, IntegrationSystem system) {
		boolean exist = exist(id, system.getName());
		if (exist) {
			return ValidationResult.invalid("exist,name");
		}
		return ValidationResult.valid();
	}
	
	public boolean exist(Long id, String name) {
		// 서버명을 변경하지 않았을 경우 false를 리턴
		IntegrationSystem oldSystem = repository.findById(id).get();
		if (StringUtils.equals(oldSystem.getName(), name)) {
			return false;
		}
		
		List<IntegrationSystem> systems = repository.findAllByName(name);
		return systems.size() > 0;
	}
	
	public boolean exist(String name) {
		List<IntegrationSystem> systems = repository.findAllByName(name);
		return systems.size() > 0;
	}
}
