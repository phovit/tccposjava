package br.edu.unitri.posjava.tcc.med4you.controller;

import br.edu.unitri.posjava.tcc.med4you.model.Medicine;
import br.edu.unitri.posjava.tcc.med4you.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by pauloho on 17/07/18.
 */
@RestController
@RequestMapping("medicine")
public class MedicineController {

    @Autowired
    private MedicineService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Medicine findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Medicine> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(Long id) {
        service.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody Medicine medicine) {
        service.update(medicine);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Medicine medicine) {
        service.save(medicine);
    }

    @RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET)
    public List<Medicine> findByName(@PathVariable("name") String name) {
        return service.findByName(name);
    }

}
