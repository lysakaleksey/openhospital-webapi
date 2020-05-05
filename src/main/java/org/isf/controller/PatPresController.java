package org.isf.controller;

import org.isf.patpres.model.PatientPresentation;
import org.isf.patpres.service.PatPresIoOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/v1/api")
public class PatPresController {
	@Autowired
	private PatPresIoOperationRepository repository;

	@GetMapping("/patpres")
	public Page<PatientPresentation> getAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
