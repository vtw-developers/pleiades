package com.vtw.pleiades.center.management.server;

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
	public Page<IntegrationServerView> getSystems( 
			@RequestParam(required = false, defaultValue = "") String name,
			@RequestParam(required = false, defaultValue = "") String description,
			Pageable pageable) {
		return service.list(name, description, pageable);
	}
	
	@PostMapping
	public ValidationResult createSystem(@RequestBody IntegrationServer server) {
		ValidationResult validation = validate(server);
		if (validation.isValid()) {
			service.create(server);
		}
		return validation;
	}
	
	@PutMapping("/{id}")
	public ValidationResult updateSystem(@PathVariable Long id, @RequestBody IntegrationServer server) {
		ValidationResult validation = validate(server);
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
		boolean exist = service.exist(server.getName());
		if (exist) {
			return ValidationResult.invalid("exist,name");
		}
		return ValidationResult.valid();
	}
}
