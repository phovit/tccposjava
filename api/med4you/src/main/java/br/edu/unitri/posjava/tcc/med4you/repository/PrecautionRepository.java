package br.edu.unitri.posjava.tcc.med4you.repository;

import br.edu.unitri.posjava.tcc.med4you.model.Precaution;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by pauloho on 16/07/18.
 */

public interface PrecautionRepository extends JpaRepository<Precaution, Long> {

}
