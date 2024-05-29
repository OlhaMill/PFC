package org.om.apirestnutribalance.models.services;

import org.om.apirestnutribalance.models.entities.DietaReceta;
import org.om.apirestnutribalance.models.repositories.DietaRecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DietaRecetaService {

    @Autowired
    private DietaRecetaRepository dietaRecetaRepository;

    public List<DietaReceta> getAllDietaRecetas() {
        return dietaRecetaRepository.findAll();
    }

    public Optional<DietaReceta> getDietaRecetaById(Integer id) {
        return dietaRecetaRepository.findById(id);
    }

    public DietaReceta createDietaReceta(DietaReceta dietaReceta) {
        return dietaRecetaRepository.save(dietaReceta);
    }

    public void deleteDietaReceta(Integer id) {
            dietaRecetaRepository.deleteById(id);
    }
    public List<DietaReceta> findByDietaId(Integer dietaId) {
        return dietaRecetaRepository.findByDieta_Id(dietaId);
    }
}
