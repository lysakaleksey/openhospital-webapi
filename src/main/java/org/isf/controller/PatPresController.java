package org.isf.controller;

import org.isf.patient.model.Patient;
import org.isf.patient.service.PatientIoOperationRepository;
import org.isf.patpres.model.PatientPresentation;
import org.isf.patpres.service.PatPresIoOperationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/api/patpres")
public class PatPresController {
	private final PatPresIoOperationRepository patPresRepo;
	private final PatientIoOperationRepository patientRepo;

	public PatPresController(PatPresIoOperationRepository patPresRepo, PatientIoOperationRepository patientRepo) {
		this.patPresRepo = patPresRepo;
		this.patientRepo = patientRepo;
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PatientPresentation getById(@PathVariable Integer id) {
		return patPresRepo.findById(id).orElse(null);
	}

	@GetMapping(value = "/patient/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PatientPresentation> findByPatientCode(@PathVariable int code) {
		return patPresRepo.findByPatientCode(code);
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PatientPresentation update(@RequestBody PatientPresentation request, @PathVariable int id) {
		Patient patient = findPatient(request);
		id = id > 0 ? id : -1; //0 is reserved
		PatientPresentation patPres = patPresRepo.findById(id).orElse(new PatientPresentation());
		if (patPres.getPatient() == null) {
			patPres.setPatient(patient);
		}
		patPres.setVitals(request.getVitals());
		patPres.setPresentationDate(request.getPresentationDate());
		patPres.setConsultationEnd(request.getConsultationEnd());
		patPres.setPreviousConsult(request.getPreviousConsult());
		patPres.setReferredFrom(request.getReferredFrom());
		patPres.setPatientAilmentDescription(request.getPatientAilmentDescription());
		patPres.setDoctorsAilmentDescription(request.getDoctorsAilmentDescription());
		patPres.setSpecificSymptoms(request.getSpecificSymptoms());
		patPres.setDiagnosis(request.getDiagnosis());
		patPres.setPrognosis(request.getPrognosis());
		patPres.setPatientAdvice(request.getPatientAdvice());
		patPres.setPrescribed(request.getPrescribed());
		patPres.setFollowUp(request.getFollowUp());
		patPres.setReferredTo(request.getReferredTo());
		patPres.setSummary(request.getSummary());
		return patPresRepo.save(patPres);
	}

	private Patient findPatient(PatientPresentation request) {
		Patient patient = request.getPatient();
		if (patient == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No patient fields provided");

		Integer code = patient.getCode();
		String firstName = patient.getFirstName();
		String secondName = patient.getSecondName();
		Date birthDate = patient.getBirthDate();
		char sex = patient.getSex();

		// Find by code if provided
		if (code != null && code > 0) {
			patient = patientRepo.findById(code).orElse(null);
			if (patient != null)
				return patient;
		}

		if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(secondName) || birthDate == null || sex == 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough patient fields provided. Either one is empty: first name, second name, birth date, sex");
		}

		// Otherwise find by combination of fields
		List<Patient> patients = patientRepo.findAllByFilter(firstName, secondName, birthDate, sex);
		if (patients == null || patients.size() == 0)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No patients found matching the data");
		else if (patients.size() > 1)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "More than one patients found for given data");

		return patients.get(0);
	}
}
