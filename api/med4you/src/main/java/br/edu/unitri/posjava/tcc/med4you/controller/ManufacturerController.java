package br.edu.unitri.posjava.tcc.med4you.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unitri.posjava.tcc.med4you.model.Manufacturer;
import br.edu.unitri.posjava.tcc.med4you.service.ManufacturerService;

/**
 * Created by edufratari on 18/07/18.
 */
@RestController
@RequestMapping("manufacturers")
public class ManufacturerController {

	@Autowired
	private ManufacturerService service;

	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody Manufacturer manufacturer) {
		service.save(manufacturer);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody Manufacturer manufacturer) {
		service.update(manufacturer);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Manufacturer> findAll() {
		return service.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Manufacturer findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(Long id) {
		service.delete(id);
	}

}
