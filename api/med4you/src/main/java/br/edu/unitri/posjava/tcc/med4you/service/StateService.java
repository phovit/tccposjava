package br.edu.unitri.posjava.tcc.med4you.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.unitri.posjava.tcc.med4you.model.State;
import br.edu.unitri.posjava.tcc.med4you.repository.StateRepository;

/**
 * Created by edufratari on 18/07/18.
 */
@Component
public class StateService {

	@Autowired
	private StateRepository repository;

	public void save(State state) {
		repository.save(state);
	}

	public void update(State state) {
		repository.save(state);
	}

	public List<State> findAll() {
		return repository.findAll();
	}

	public State findById(Long id) {
		return repository.findById(id).get();
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
