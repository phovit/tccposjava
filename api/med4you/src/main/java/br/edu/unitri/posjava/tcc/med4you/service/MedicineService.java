package br.edu.unitri.posjava.tcc.med4you.service;

import br.edu.unitri.posjava.tcc.med4you.model.Medicine;
import br.edu.unitri.posjava.tcc.med4you.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by pauloho on 16/07/18.
 */
@Component
public class MedicineService {

    @Autowired
    private MedicineRepository repository;

    public Medicine findById(Long id) {
        return repository.findById(id).get();
    }

    public List<Medicine> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void update(Medicine medicine) {
        repository.save(medicine);
    }

    public void save(Medicine medicine) {
        repository.save(medicine);
    }

    public List<Medicine> findByName(String name) {
        return repository.findByName(name);
    }
}
