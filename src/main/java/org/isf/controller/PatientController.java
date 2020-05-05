package org.isf.controller;

import org.isf.patient.model.Patient;
import org.isf.patient.service.PatientIoOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class PatientController {
	@Autowired
	private PatientIoOperationRepository repository;

	@GetMapping("/patient")
	public Page<Patient> getAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
