package br.edu.unitri.posjava.tcc.med4you.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.unitri.posjava.tcc.med4you.model.AgainstIndication;
import br.edu.unitri.posjava.tcc.med4you.repository.AgainstIndicationRepository;

/**
 * Created by edufratari on 31/07/18.
 */
@Component
public class AgainstIndicationService {

	@Autowired
	private AgainstIndicationRepository repository;

	public void save(AgainstIndication againstIndication) {
		repository.save(againstIndication);
	}

	public void update(AgainstIndication againstIndication) {
		repository.save(againstIndication);
	}

	public List<AgainstIndication> findAll() {
		return repository.findAll();
	}

	public AgainstIndication findById(Long id) {
		return repository.findById(id).get();
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
