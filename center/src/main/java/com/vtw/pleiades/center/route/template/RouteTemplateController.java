package com.vtw.pleiades.center.route.template;

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
@RequestMapping("/routetemplates")
public class RouteTemplateController {

	@Autowired
	private RouteTemplateService service;

	@GetMapping
	public Page<RouteTemplateView> list(Pageable pageable, 
			@RequestParam(required = false, defaultValue = "") String keyword) {
		return service.list(pageable, keyword);
	}
	
	@PostMapping
	public ValidationResult create(@RequestBody RouteTemplate system) throws Exception {
		return service.createWithValidation(system);
	}
	
	@PutMapping("/{id}")
	public ValidationResult update(@PathVariable String id, @RequestBody RouteTemplate system) {
		return service.updateWithValidation(id, system);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

	@GetMapping("/all")
	public List<RouteTemplate> listAll() {
		return service.listAll();
	}
	
	@GetMapping("/{id}")	
	public RouteTemplate get(@PathVariable String id) {
		return service.get(id);
	}
}
