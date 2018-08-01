package br.edu.unitri.posjava.tcc.med4you.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.unitri.posjava.tcc.med4you.model.Reminder;
import br.edu.unitri.posjava.tcc.med4you.repository.ReminderRepository;

/**
 * Created by edufratari on 31/07/18.
 */
@Component
public class ReminderService {

	@Autowired
	private ReminderRepository repository;

	public void save(Reminder reminder) {
		repository.save(reminder);
	}

	public void update(Reminder reminder) {
		repository.save(reminder);
	}

	public List<Reminder> findAll() {
		return repository.findAll();
	}

	public Reminder findById(Long id) {
		return repository.findById(id).get();
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
