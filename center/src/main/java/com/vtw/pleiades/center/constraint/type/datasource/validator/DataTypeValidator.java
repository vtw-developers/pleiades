package com.vtw.pleiades.center.constraint.type.datasource.validator;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtw.pleiades.center.common.web.validation.ValidationResult;
import com.vtw.pleiades.center.constraint.type.datasource.DataType;
import com.vtw.pleiades.center.constraint.type.datasource.repository.DataTypeRepository;
import com.vtw.pleiades.center.management.CrudValidator;

@Service
public class DataTypeValidator implements CrudValidator<DataType, Long> {

	@Autowired
	private DataTypeRepository repository;
	
	@Override
	public ValidationResult validate(DataType dataType) {
		ValidationResult notExistName = validateNotExistName(dataType);
		return notExistName;
	}
	
	@Override
	public ValidationResult validate(Long id, DataType dataType) {
		ValidationResult notExistName = validateNotExistName(id, dataType);
		return notExistName;
	}
	
	public ValidationResult validateNotExistName(DataType dataType) {
		boolean exist = exist(dataType.getName());
		if (exist) {
			return ValidationResult.invalid("exist,name");
		}
		return ValidationResult.valid();
	}
	
	public ValidationResult validateNotExistName(Long id, DataType dataType) {
		boolean exist = exist(id, dataType.getName());
		if (exist) {
			return ValidationResult.invalid("exist,name");
		}
		return ValidationResult.valid();
	}
	
	public boolean exist(Long id, String name) {
		// 서버명을 변경하지 않았을 경우 false를 리턴
		DataType oldSystem = repository.findById(id).get();
		if (StringUtils.equals(oldSystem.getName(), name)) {
			return false;
		}
		
		List<DataType> dataTypes = repository.findAllByName(name);
		return dataTypes.size() > 0;
	}
	
	public boolean exist(String name) {
		List<DataType> dataTypes = repository.findAllByName(name);
		return dataTypes.size() > 0;
	}
}
