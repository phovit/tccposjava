package br.edu.unitri.posjava.tcc.med4you.controller;

import br.edu.unitri.posjava.tcc.med4you.dto.UserDTO;
import br.edu.unitri.posjava.tcc.med4you.model.User;
import br.edu.unitri.posjava.tcc.med4you.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by edufratari on 18/07/18.
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/isLogged", method = RequestMethod.GET)
    public boolean isLogged() {
        return service.isLogged();
    }

    @RequestMapping(value = "/logged", method = RequestMethod.GET)
    public UserDTO findLogged() {
        return service.findLogged();
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public UserDTO authenticate(@RequestBody User u) {

        if (service.autenticate(u)) {
            u = service.findByUsername(u.getUsername());
            UserDTO user = new UserDTO();
            user.setName(u.getName());
            user.setUsername(u.getUsername());
            user.setAddress(u.getAddress());
            user.setBirthDate(u.getBirthDate());
            user.setCellPhone(u.getCellPhone());
            user.setCpf(u.getCpf());
            user.setEmail(u.getEmail());
            user.setId(u.getId());
            user.setIdentity(u.getIdentity());
            user.setPhone(u.getPhone());

            return user;

        } else {
            return null;
        }
    }


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
