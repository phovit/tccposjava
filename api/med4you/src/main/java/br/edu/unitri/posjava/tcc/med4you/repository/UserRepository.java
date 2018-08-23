package br.edu.unitri.posjava.tcc.med4you.repository;

import br.edu.unitri.posjava.tcc.med4you.model.Medicine;
import br.edu.unitri.posjava.tcc.med4you.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by edufratari on 18/07/18.
 */
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("SELECT u FROM User u WHERE upper(u.username) = upper( ?1 )")
    User findByUsername(@Param("login") String login);

}
