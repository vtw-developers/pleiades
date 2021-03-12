package com.vtw.pleiades.center.constraint.type.datasource.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtw.pleiades.center.common.web.validation.ValidationResult;
import com.vtw.pleiades.center.constraint.type.datasource.DataType;
import com.vtw.pleiades.center.constraint.type.datasource.service.DataTypeService;
import com.vtw.pleiades.center.constraint.type.datasource.view.DataTypeListView;

@RestController
@RequestMapping("/constraint/type/data")
public class DataTypeController {

	@Autowired
	private DataTypeService service;

	@GetMapping
	public Page<DataTypeListView> list(Pageable pageable, 
			@RequestParam(required = false, defaultValue = "") String name) {
		return service.list(pageable, name);
	}
	
	@GetMapping("/{id}")	
	public DataType get(@PathVariable Long id) {
		return service.get(id);
	}
	
	@PostMapping
	public ValidationResult create(@RequestBody DataType dataType) throws Exception {
		return service.createWithValidation(dataType);
	}
	
	@PutMapping("/{id}")
	public ValidationResult update(@PathVariable Long id, @RequestBody DataType dataType) {
		return service.updateWithValidation(id, dataType);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@GetMapping("/all")
	public List<DataType> listAll() {
		return service.listAll();
	}
}
