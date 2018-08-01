package br.edu.unitri.posjava.tcc.med4you.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unitri.posjava.tcc.med4you.model.DrugStore;

/**
 * Created by edufratari on 01/08/18.
 */
public interface DrugStoreRepository extends JpaRepository<DrugStore, Long> {

}
