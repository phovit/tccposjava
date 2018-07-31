package br.edu.unitri.posjava.tcc.med4you.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unitri.posjava.tcc.med4you.model.Indication;
import br.edu.unitri.posjava.tcc.med4you.service.IndicationService;

/**
 * Created by edufratari on 31/07/18.
 */
@RestController
@RequestMapping("indications")
public class IndicationController {

	@Autowired
	private IndicationService service;

	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody Indication indication) {
		service.save(indication);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody Indication indication) {
		service.update(indication);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Indication> findAll() {
		return service.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Indication findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(Long id) {
		service.delete(id);
	}

}
