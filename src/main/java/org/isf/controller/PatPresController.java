package org.isf.controller;

import org.isf.patpres.model.PatientPresentation;
import org.isf.patpres.service.PatPresIoOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/patpres")
public class PatPresController {
	@Autowired
	private PatPresIoOperationRepository repository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PatientPresentation> findAll() {
		return repository.findAll();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PatientPresentation getById(@PathVariable Integer id) {
		return repository.findById(id).orElse(null);
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PatientPresentation update(@RequestBody PatientPresentation patPres, @PathVariable Integer id) {
		return repository.findById(id)
			.map(e -> repository.save(patPres))
			.orElseGet(() -> {
				patPres.setCode(id);
				return repository.save(patPres);
			});
	}
}
