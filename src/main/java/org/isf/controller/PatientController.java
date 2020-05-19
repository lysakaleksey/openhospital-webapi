package org.isf.controller;

import org.isf.patient.model.Patient;
import org.isf.patient.service.PatientIoOperationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<?> update(@RequestBody Patient request, @PathVariable int id) {
		id = id > 0 ? id : -1; //0 is reserved
		HttpStatus status = repository.findById(id)
			.map(record -> {
				record.setFirstName(request.getFirstName());
				record.setSecondName(request.getSecondName());
				record.setBirthDate(request.getBirthDate());
				record.setAge(request.getAge());
				record.setAgetype(request.getAgetype());
				record.setSex(request.getSex());
				record.setAddress(request.getAddress());
				record.setCity(request.getCity());
				record.setNextKin(request.getNextKin());
				record.setTelephone(request.getTelephone());
				record.setNote(request.getNote());
				record.setMother_name(request.getMother_name());
				record.setMother(request.getMother());
				record.setFather_name(request.getFather_name());
				record.setFather(request.getFather());
				record.setBloodType(request.getBloodType());
				record.setHasInsurance(request.getHasInsurance());
				record.setParentTogether(request.getParentTogether());
				record.setTaxCode(request.getTaxCode());
				record.setMaritalStatus(request.getMaritalStatus());
				record.setProfession(request.getProfession());
				repository.save(record);
				return HttpStatus.OK;
			})
			.orElseGet(() -> {
				request.setCode(null);
				repository.save(request);
				return HttpStatus.CREATED;
			});
		return new ResponseEntity<>(status);
	}
}
