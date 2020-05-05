package org.isf.controller;

import org.isf.patient.model.Patient;
import org.isf.patient.service.PatientIoOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/patient")
public class PatientController {
	@Autowired
	private PatientIoOperationRepository repository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Patient> findAll() {
		return repository.findAll();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient getById(@PathVariable Integer id) {
		return repository.findById(id).orElse(null);
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Patient update(@RequestBody Patient p, @PathVariable Integer id) {
		return repository.findById(id)
			.map(e -> {
				e.setTaxCode(p.getTaxCode());
				return repository.save(e);
			})
			.orElseGet(() -> {
				p.setCode(id);
				return repository.save(p);
			});
	}
}
