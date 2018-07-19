package br.edu.unitri.posjava.tcc.med4you.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.unitri.posjava.tcc.med4you.model.User;
import br.edu.unitri.posjava.tcc.med4you.repository.UserRepository;

/**
 * Created by edufratari on 18/07/18.
 */
@Component
public class UserService {

	@Autowired
	private UserRepository repository;

	public void save(User user) {
		repository.save(user);
	}

	public void update(User user) {
		repository.save(user);
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		return repository.findById(id).get();
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
