package com.vtw.pleiades.center.management.server;

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

@RestController
@RequestMapping("/servers")
public class IntegrationServerController {

	@Autowired
	private IntegrationServerService service;

	@GetMapping
	public Page<IntegrationServerView> list( 
			@RequestParam(required = false, defaultValue = "") String keyword,
			Pageable pageable) {
		return service.list(keyword, pageable);
	}
	
	@PostMapping
	public ValidationResult create(@RequestBody IntegrationServer server) {
		ValidationResult validation = validate(server);
		if (validation.isValid()) {
			service.create(server);
		}
		return validation;
	}
	
	@PutMapping("/{id}")
	public ValidationResult update(@PathVariable Long id, @RequestBody IntegrationServer server) {
		ValidationResult validation = validate(id, server);
		if (validation.isValid()) {
			service.update(id, server);
		}
		return validation;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	@PostMapping("/validate")
	public ValidationResult validate(@RequestBody IntegrationServer server) {
		return service.validate(server);
	}
	
	@PostMapping("/validate/{id}")
	public ValidationResult validate(@PathVariable Long id, @RequestBody IntegrationServer server) {
		return service.validate(id, server);
	}
}
