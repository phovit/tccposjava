package br.edu.unitri.posjava.tcc.med4you.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unitri.posjava.tcc.med4you.model.Address;
import br.edu.unitri.posjava.tcc.med4you.service.AddressService;

/**
 * Created by edufratari on 18/07/18.
 */
@RestController
@RequestMapping("addresses")
public class AddressController {

	@Autowired
	private AddressService service;

	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody Address address) {
		service.save(address);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody Address address) {
		service.update(address);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Address> findAll() {
		return service.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Address findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(Long id) {
		service.delete(id);
	}

}
