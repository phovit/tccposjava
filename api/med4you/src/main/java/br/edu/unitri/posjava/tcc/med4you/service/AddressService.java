package br.edu.unitri.posjava.tcc.med4you.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.unitri.posjava.tcc.med4you.model.Address;
import br.edu.unitri.posjava.tcc.med4you.repository.AddressRepository;

/**
 * Created by edufratari on 18/07/18.
 */
@Component
public class AddressService {

	@Autowired
	private AddressRepository repository;
	
	public void save(Address address) {
		repository.save(address);
	}

	public void update(Address address) {
		repository.save(address);
	}

	public List<Address> findAll() {
		return repository.findAll();
	}

	public Address findById(Long id) {
		return repository.findById(id).get();
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
