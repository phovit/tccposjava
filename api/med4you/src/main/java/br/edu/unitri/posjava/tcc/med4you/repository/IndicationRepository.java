package br.edu.unitri.posjava.tcc.med4you.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unitri.posjava.tcc.med4you.model.Indication;

/**
 * Created by edufratari on 31/07/18.
 */
public interface IndicationRepository extends JpaRepository<Indication, Long> {

}
