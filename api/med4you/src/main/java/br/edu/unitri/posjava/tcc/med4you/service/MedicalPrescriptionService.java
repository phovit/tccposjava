package br.edu.unitri.posjava.tcc.med4you.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.unitri.posjava.tcc.med4you.model.MedicalPrescription;
import br.edu.unitri.posjava.tcc.med4you.repository.MedicalPrescriptionRepository;

/**
 * Created by edufratari on 01/08/18.
 */
@Component
public class MedicalPrescriptionService {

	@Autowired
	private MedicalPrescriptionRepository repository;

	public void save(MedicalPrescription medicalPrescription) {
		repository.save(medicalPrescription);
	}

	public void update(MedicalPrescription medicalPrescription) {
		repository.save(medicalPrescription);
	}

	public List<MedicalPrescription> findAll() {
		return repository.findAll();
	}

	public MedicalPrescription findById(Long id) {
		return repository.findById(id).get();
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
