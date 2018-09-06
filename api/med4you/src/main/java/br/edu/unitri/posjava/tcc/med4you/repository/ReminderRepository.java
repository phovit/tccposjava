package br.edu.unitri.posjava.tcc.med4you.repository;

import br.edu.unitri.posjava.tcc.med4you.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by edufratari on 31/07/18.
 */
public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    @Query("SELECT r FROM Reminder r WHERE r.user.id = :userId")
    List<Reminder> findByUserId(@Param("userId") Long userId);

}
