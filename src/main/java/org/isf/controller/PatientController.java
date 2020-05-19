package org.isf.controller;

import org.isf.patient.model.Patient;
import org.isf.patient.service.PatientIoOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/patient")
public class PatientController {
	private final PatientIoOperationRepository repository;

	public PatientController(PatientIoOperationRepository repository) {
		this.repository = repository;
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient getById(@PathVariable Integer id) {
		return repository.findById(id).orElse(null);
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Patient update(@RequestBody Patient p, @PathVariable Integer id) {
		return repository.findById(id)
			.map(e -> {
				e.setFirstName(p.getFirstName());
				e.setSecondName(p.getSecondName());
				e.setBirthDate(p.getBirthDate());
				e.setAge(p.getAge());
				e.setAgetype(p.getAgetype());
				e.setSex(p.getSex());
				e.setAddress(p.getAddress());
				e.setCity(p.getCity());
				e.setNextKin(p.getNextKin());
				e.setTelephone(p.getTelephone());
				e.setNote(p.getNote());
				e.setMother_name(p.getMother_name());
				e.setMother(p.getMother());
				e.setFather_name(p.getFather_name());
				e.setFather(p.getFather());
				e.setBloodType(p.getBloodType());
				e.setHasInsurance(p.getHasInsurance());
				e.setParentTogether(p.getParentTogether());
				e.setTaxCode(p.getTaxCode());
				e.setMaritalStatus(p.getMaritalStatus());
				e.setProfession(p.getProfession());

				return repository.save(e);
			})
			.orElseGet(() -> {
				p.setCode(id);
				return repository.save(p);
			});
	}
}
