package br.edu.unitri.posjava.tcc.med4you.service;

import br.edu.unitri.posjava.tcc.med4you.model.Precaution;
import br.edu.unitri.posjava.tcc.med4you.repository.PrecautionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by pauloho on 16/07/18.
 */
@Component
public class PrecautionService {

    @Autowired
    private PrecautionRepository repository;

    public Precaution findById(Long id) {
        return repository.findById(id).get();
    }

    public List<Precaution> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void update(Precaution precaution) {
        repository.save(precaution);
    }

    public void save(Precaution precaution) {
        repository.save(precaution);
    }
}
