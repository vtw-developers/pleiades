package com.vtw.pleiades.center.management.system;

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
@RequestMapping("/systems")
public class IntegrationSystemController {

	@Autowired
	private IntegrationSystemService service;

	@GetMapping
	public Page<IntegrationSystem> list(Pageable pageable, 
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String description) {
		return service.list(pageable, name, description);
	}
	
	@PostMapping
	public IntegrationSystem create(@RequestBody IntegrationSystem system) throws Exception {
		boolean exist = service.exist(system.getName());
		if (exist) throw new Exception("Duplicated Name");
		return service.create(system);
	}
	
	@PutMapping("/{id}")
	public IntegrationSystem update(@PathVariable Long id, @RequestBody IntegrationSystem newSystem) {
		return service.update(id, newSystem);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	@PostMapping("/validate")
	public ValidationResult validate(@RequestBody IntegrationSystem system) {
		boolean exist = service.exist(system.getName());
		if (exist) {
			return ValidationResult.invalid("exist,name");
		}
		return ValidationResult.valid();
	}
}
