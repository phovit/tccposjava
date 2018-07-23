package br.edu.unitri.posjava.tcc.med4you.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.unitri.posjava.tcc.med4you.model.City;
import br.edu.unitri.posjava.tcc.med4you.repository.CityRepository;

/**
 * Created by edufratari on 18/07/18.
 */
@Component
public class CityService {
	
	@Autowired
	private CityRepository repository;
	
	public void save(City city) {
		repository.save(city);
	}

	public void update(City city) {
		repository.save(city);
	}

	public List<City> findAll() {
		return repository.findAll();
	}

	public City findById(Long id) {
		return repository.findById(id).get();
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
