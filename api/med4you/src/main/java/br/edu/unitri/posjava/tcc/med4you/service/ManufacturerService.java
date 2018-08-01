package br.edu.unitri.posjava.tcc.med4you.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.unitri.posjava.tcc.med4you.model.AgainstIndication;
import br.edu.unitri.posjava.tcc.med4you.model.Manufacturer;
import br.edu.unitri.posjava.tcc.med4you.repository.AgainstIndicationRepository;
import br.edu.unitri.posjava.tcc.med4you.repository.ManufacturerRepository;

/**
 * Created by edufratari on 31/07/18.
 */
@Component
public class ManufacturerService {

	@Autowired
	private ManufacturerRepository repository;

	public void save(Manufacturer manufacturer) {
		repository.save(manufacturer);
	}

	public void update(Manufacturer manufacturer) {
		repository.save(manufacturer);
	}

	public List<Manufacturer> findAll() {
		return repository.findAll();
	}

	public Manufacturer findById(Long id) {
		return repository.findById(id).get();
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
