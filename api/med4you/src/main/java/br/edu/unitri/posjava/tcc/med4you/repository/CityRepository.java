package br.edu.unitri.posjava.tcc.med4you.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unitri.posjava.tcc.med4you.model.City;

/**
 * Created by edufratari on 18/07/18.
 */
public interface CityRepository extends JpaRepository<City, Long> {
	
}
