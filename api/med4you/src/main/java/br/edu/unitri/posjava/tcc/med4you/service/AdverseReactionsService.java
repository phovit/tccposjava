package br.edu.unitri.posjava.tcc.med4you.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.unitri.posjava.tcc.med4you.model.AdverseReactions;
import br.edu.unitri.posjava.tcc.med4you.repository.AdverseReactionsRepository;

/**
 * Created by edufratari on 31/07/18.
 */
@Component
public class AdverseReactionsService {
	
	@Autowired
	private AdverseReactionsRepository repository;
	
	public void save(AdverseReactions adverseReactions) {
		repository.save(adverseReactions);
	}

	public void update(AdverseReactions adverseReactions) {
		repository.save(adverseReactions);
	}

	public List<AdverseReactions> findAll() {
		return repository.findAll();
	}

	public AdverseReactions findById(Long id) {
		return repository.findById(id).get();
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
