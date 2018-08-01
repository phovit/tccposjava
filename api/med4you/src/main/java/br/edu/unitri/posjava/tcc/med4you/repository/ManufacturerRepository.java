package br.edu.unitri.posjava.tcc.med4you.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unitri.posjava.tcc.med4you.model.Manufacturer;

/**
 * Created by edufratari on 31/07/18.
 */
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

}
