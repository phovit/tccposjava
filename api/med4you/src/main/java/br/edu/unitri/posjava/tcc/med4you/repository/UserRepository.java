package br.edu.unitri.posjava.tcc.med4you.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unitri.posjava.tcc.med4you.model.User;

/**
 * Created by edufratari on 18/07/18.
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
