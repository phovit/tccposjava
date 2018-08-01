package br.edu.unitri.posjava.tcc.med4you.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.unitri.posjava.tcc.med4you.model.Indication;
import br.edu.unitri.posjava.tcc.med4you.repository.IndicationRepository;

/**
 * Created by edufratari on 31/07/18.
 */
@Component
public class IndicationService {
	
	@Autowired
	private IndicationRepository repository;
	
	public void save(Indication indication) {
		repository.save(indication);
	}

	public void update(Indication indication) {
		repository.save(indication);
	}

	public List<Indication> findAll() {
		return repository.findAll();
	}

	public Indication findById(Long id) {
		return repository.findById(id).get();
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
