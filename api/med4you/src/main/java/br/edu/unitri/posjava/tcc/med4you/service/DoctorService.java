package br.edu.unitri.posjava.tcc.med4you.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.unitri.posjava.tcc.med4you.model.Doctor;
import br.edu.unitri.posjava.tcc.med4you.repository.DoctorRepository;

/**
 * Created by edufratari on 01/08/18.
 */
@Component
public class DoctorService {

	@Autowired
	private DoctorRepository repository;

	public void save(Doctor doctor) {
		repository.save(doctor);
	}

	public void update(Doctor doctor) {
		repository.save(doctor);
	}

	public List<Doctor> findAll() {
		return repository.findAll();
	}

	public Doctor findById(Long id) {
		return repository.findById(id).get();
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
