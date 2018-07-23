package br.edu.unitri.posjava.tcc.med4you.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unitri.posjava.tcc.med4you.model.User;
import br.edu.unitri.posjava.tcc.med4you.service.UserService;

/**
 * Created by edufratari on 18/07/18.
 */
@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
    private UserService service;
	
	@RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody User user) {
        service.save(user);
    }
	
	@RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody User user) {
        service.update(user);
    }
	
	@RequestMapping(method = RequestMethod.GET)
    public List<User> findAll() {
        return service.findAll();
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(Long id) {
        service.delete(id);
    }
}
