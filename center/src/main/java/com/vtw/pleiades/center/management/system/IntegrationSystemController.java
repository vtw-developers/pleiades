package com.vtw.pleiades.center.management.system;

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
import com.vtw.pleiades.center.management.server.IntegrationServer;

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
	public ValidationResult create(@RequestBody IntegrationSystem system) throws Exception {
		return service.createWithValidation(system);
	}
	
	@PutMapping("/{id}")
	public ValidationResult update(@PathVariable Long id, @RequestBody IntegrationSystem system) {
		return service.updateWithValidation(id, system);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@GetMapping("/all")
	public List<IntegrationSystem> listAll() {
		return service.listAll();
	}
	
	@GetMapping("/{id}/servers")
	public List<IntegrationServer> getServers(@PathVariable Long id) {
		return service.getServers(id);
	}
}
