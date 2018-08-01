package br.edu.unitri.posjava.tcc.med4you.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unitri.posjava.tcc.med4you.model.Doctor;
import br.edu.unitri.posjava.tcc.med4you.service.DoctorService;

/**
 * Created by edufratari on 01/08/18.
 */
@RestController
@RequestMapping("doctors")
public class DoctorController {

	@Autowired
	private DoctorService service;

	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody Doctor doctor) {
		service.save(doctor);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody Doctor doctor) {
		service.update(doctor);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Doctor> findAll() {
		return service.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Doctor findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(Long id) {
		service.delete(id);
	}

}
