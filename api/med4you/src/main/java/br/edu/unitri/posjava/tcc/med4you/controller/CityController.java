package br.edu.unitri.posjava.tcc.med4you.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unitri.posjava.tcc.med4you.model.City;
import br.edu.unitri.posjava.tcc.med4you.service.CityService;

/**
 * Created by edufratari on 18/07/18.
 */
@RestController
@RequestMapping("cities")
public class CityController {

	@Autowired
	private CityService service;

	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody City city) {
		service.save(city);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody City city) {
		service.update(city);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<City> findAll() {
		return service.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public City findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(Long id) {
		service.delete(id);
	}
}
