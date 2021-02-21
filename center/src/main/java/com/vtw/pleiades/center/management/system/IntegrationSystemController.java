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

@RestController
@RequestMapping("/systems")
public class IntegrationSystemController {

	@Autowired
	private IntegrationSystemService service;

	@GetMapping
	public Page<IntegrationSystem> getSystems(Pageable pageable, 
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String description) {
		return service.getSystems(pageable, name, description);
	}
	
	@PostMapping
	public IntegrationSystem createSystem(@RequestBody IntegrationSystem system) {
		return service.createSystem(system);
	}
	
	@PutMapping("/{id}")
	public IntegrationSystem updateSystem(@PathVariable Long id, @RequestBody IntegrationSystem newSystem) {
		return service.updateSystem(id, newSystem);
	}
	
	@DeleteMapping("/{id}")
	public void deleteSystem(@PathVariable Long id) {
		service.deleteSystem(id);
	}
}
