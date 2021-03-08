package com.vtw.pleiades.center.route.template;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtw.pleiades.center.common.web.validation.ValidationResult;
import com.vtw.pleiades.center.management.CrudValidator;

@Service
public class RouteTemplateValidator implements CrudValidator<RouteTemplate, String> {

	@Autowired
	private RouteTemplateRepository repository;
	
	@Override
	public ValidationResult validate(RouteTemplate system) {
		ValidationResult notExistName = validateNotExistName(system);
		if (notExistName.isValid()) {
			return validateNotExistId(system);
		}
		return notExistName;
	}
	
	@Override
	public ValidationResult validate(String id, RouteTemplate system) {
		ValidationResult notExistName = validateNotExistName(id, system);
		return notExistName;
	}
	
	public ValidationResult validateNotExistId(RouteTemplate system) {
		boolean exist = existId(system.getId());
		if (exist) {
			return ValidationResult.invalid("exist,id");
		}
		return ValidationResult.valid();
	}
	
	public ValidationResult validateNotExistName(RouteTemplate system) {
		boolean exist = exist(system.getName());
		if (exist) {
			return ValidationResult.invalid("exist,name");
		}
		return ValidationResult.valid();
	}
	
	public ValidationResult validateNotExistName(String id, RouteTemplate system) {
		boolean exist = exist(id, system.getName());
		if (exist) {
			return ValidationResult.invalid("exist,name");
		}
		return ValidationResult.valid();
	}
	
	public boolean existId(String id) {
		return repository.findById(id).isPresent();
	}
	
	public boolean exist(String id, String name) {
		// 서버명을 변경하지 않았을 경우 false를 리턴
		RouteTemplate oldSystem = repository.findById(id).get();
		if (StringUtils.equals(oldSystem.getName(), name)) {
			return false;
		}
		
		List<RouteTemplate> systems = repository.findAllByName(name);
		return systems.size() > 0;
	}
	
	public boolean exist(String name) {
		List<RouteTemplate> systems = repository.findAllByName(name);
		return systems.size() > 0;
	}
}
