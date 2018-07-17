package br.edu.unitri.posjava.tcc.med4you.controller;

import br.edu.unitri.posjava.tcc.med4you.model.Precaution;
import br.edu.unitri.posjava.tcc.med4you.service.PrecautionService;
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
public class PrecautionController {

    @Autowired
    private PrecautionService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Precaution findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Precaution> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(Long id) {
        service.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody Precaution precaution) {
        service.update(precaution);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Precaution precaution) {
        service.save(precaution);
    }
}
