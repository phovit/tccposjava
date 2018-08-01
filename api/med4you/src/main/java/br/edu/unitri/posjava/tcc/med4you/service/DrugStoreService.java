package br.edu.unitri.posjava.tcc.med4you.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.unitri.posjava.tcc.med4you.model.DrugStore;
import br.edu.unitri.posjava.tcc.med4you.repository.DrugStoreRepository;

/**
 * Created by edufratari on 01/08/18.
 */
@Component
public class DrugStoreService {

	@Autowired
	private DrugStoreRepository repository;

	public void save(DrugStore drugStore) {
		repository.save(drugStore);
	}

	public void update(DrugStore drugStore) {
		repository.save(drugStore);
	}

	public List<DrugStore> findAll() {
		return repository.findAll();
	}

	public DrugStore findById(Long id) {
		return repository.findById(id).get();
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
