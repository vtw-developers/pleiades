package com.vtw.pleiades.center.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegrationSystemController {

	@Autowired
	private IntegrationSystemService service;

	@GetMapping("/systems")
	public Page<IntegrationSystem> getSystems(Pageable pageable, 
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String description) {
		return service.list(pageable, name, description);
	}
}
