package br.edu.unitri.posjava.tcc.med4you.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unitri.posjava.tcc.med4you.model.Medicine;

/**
 * Created by pauloho on 16/07/18.
 */

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

}
