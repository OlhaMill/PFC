package org.om.apirestnutribalance.models.services;

import org.om.apirestnutribalance.models.entities.Dieta;
import org.om.apirestnutribalance.models.repositories.DietaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DietaService {

    @Autowired
    private DietaRepository dietaRepository;

    public List<Dieta> getAllDietas() {
        return dietaRepository.findAll();
    }

    public Optional<Dieta> getDietaById(Integer id) {
        return dietaRepository.findById(id);
    }

    public Dieta createDieta(Dieta dieta) {
        return dietaRepository.save(dieta);
    }

    public void deleteDieta(Integer id) {
        dietaRepository.deleteById(id);
    }
}
