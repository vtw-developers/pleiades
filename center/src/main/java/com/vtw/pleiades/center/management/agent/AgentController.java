package com.vtw.pleiades.center.management.agent;

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
@RequestMapping("/agents")
public class AgentController {

	@Autowired
	private AgentService service;

	@GetMapping
	public Page<AgentView> list( 
			@RequestParam(required = false, defaultValue = "") String keyword,
			Pageable pageable) {
		return service.list(keyword, pageable);
	}
	
	@PostMapping
	public ValidationResult create(@RequestBody Agent agent) {
		ValidationResult validation = validate(agent);
		if (validation.isValid()) {
			service.create(agent);
		}
		return validation;
	}
	
	@PutMapping("/{id}")
	public ValidationResult update(@PathVariable Long id, @RequestBody Agent agent) {
		ValidationResult validation = validate(id, agent);
		if (validation.isValid()) {
			service.update(id, agent);
		}
		return validation;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	@PostMapping("/validate")
	public ValidationResult validate(@RequestBody Agent agent) {
		return service.validate(agent);
	}
	
	@PostMapping("/validate/{id}")
	public ValidationResult validate(@PathVariable Long id, @RequestBody Agent agent) {
		return service.validate(id, agent);
	}
}
