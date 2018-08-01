package br.edu.unitri.posjava.tcc.med4you.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unitri.posjava.tcc.med4you.model.AgainstIndication;
import br.edu.unitri.posjava.tcc.med4you.service.AgainstIndicationService;

/**
 * Created by edufratari on 31/07/18.
 */
@RestController
@RequestMapping("againstindications")
public class AgainstIndicationController {

	@Autowired
	private AgainstIndicationService service;

	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody AgainstIndication againstIndication) {
		service.save(againstIndication);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody AgainstIndication againstIndication) {
		service.update(againstIndication);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<AgainstIndication> findAll() {
		return service.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public AgainstIndication findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(Long id) {
		service.delete(id);
	}

}
