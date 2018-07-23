package br.edu.unitri.posjava.tcc.med4you.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unitri.posjava.tcc.med4you.model.Address;

/**
 * Created by edufratari on 18/07/18.
 */
public interface AddressRepository extends JpaRepository<Address, Long>{
	
}
