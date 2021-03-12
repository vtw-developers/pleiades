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
import com.vtw.pleiades.center.constraint.type.datasource.DatasourceType;
import com.vtw.pleiades.center.constraint.type.datasource.service.DatasourceTypeService;
import com.vtw.pleiades.center.constraint.type.datasource.view.DatasourceTypeListView;

@RestController
@RequestMapping("/constraint/type/datasource")
public class DatasourceTypeController {

	@Autowired
	private DatasourceTypeService service;

	@GetMapping
	public Page<DatasourceTypeListView> list(Pageable pageable, 
			@RequestParam(required = false, defaultValue = "") String name) {
		return service.list(pageable, name);
	}
	
	@PostMapping
	public ValidationResult create(@RequestBody DatasourceType dataType) throws Exception {
		return service.createWithValidation(dataType);
	}
	
	@PutMapping("/{id}")
	public ValidationResult update(@PathVariable Long id, @RequestBody DatasourceType dataType) {
		return service.updateWithValidation(id, dataType);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@GetMapping("/all")
	public List<DatasourceType> listAll() {
		return service.listAll();
	}
	
	@GetMapping("/{id}")	
	public DatasourceType get(@PathVariable Long id) {
		return service.get(id);
	}
}
