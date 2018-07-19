package br.edu.unitri.posjava.tcc.med4you.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unitri.posjava.tcc.med4you.model.State;
import br.edu.unitri.posjava.tcc.med4you.service.StateService;

/**
 * Created by edufratari on 18/07/18.
 */
@RestController
@RequestMapping("states")
public class StateController {

	@Autowired
	private StateService service;

	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody State state) {
		service.save(state);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody State state) {
		service.update(state);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<State> findAll() {
		return service.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public State findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(Long id) {
		service.delete(id);
	}

}
