package com.vtw.pleiades.center.management.route;

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

@RestController
@RequestMapping("/routes")
public class RouteController {

	@Autowired
	private RouteService service;

	@GetMapping
	public Page<RouteView> list( 
			@RequestParam(required = false, defaultValue = "") String keyword,
			Pageable pageable) {
		return service.list(keyword, pageable);
	}
	
	@PostMapping
	public ValidationResult create(@RequestBody Route route) {
		ValidationResult validation = validate(route);
		if (validation.isValid()) {
			service.create(route);
		}
		return validation;
	}
	
	@PutMapping("/{id}")
	public ValidationResult update(@PathVariable Long id, @RequestBody Route route) {
		ValidationResult validation = validate(id, route);
		if (validation.isValid()) {
			service.update(id, route);
		}
		return validation;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	@PostMapping("/validate")
	public ValidationResult validate(@RequestBody Route route) {
		return service.validate(route);
	}
	
	@PostMapping("/validate/{id}")
	public ValidationResult validate(@PathVariable Long id, @RequestBody Route route) {
		return service.validate(id, route);
	}
}
