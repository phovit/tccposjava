package br.edu.unitri.posjava.tcc.med4you.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unitri.posjava.tcc.med4you.model.MedicalPrescription;
import br.edu.unitri.posjava.tcc.med4you.service.MedicalPrescriptionService;

/**
 * Created by edufratari on 01/08/18.
 */
@RestController
@RequestMapping("medicalPrescriptions")
public class MedicalPrescriptionController {

	@Autowired
	private MedicalPrescriptionService service;

	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody MedicalPrescription medicalPrescription) {
		service.save(medicalPrescription);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody MedicalPrescription medicalPrescription) {
		service.update(medicalPrescription);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<MedicalPrescription> findAll() {
		return service.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public MedicalPrescription findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(Long id) {
		service.delete(id);
	}

}
