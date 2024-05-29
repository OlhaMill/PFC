package org.om.apirestnutribalance.models.services;

import org.om.apirestnutribalance.models.entities.Receta;
import org.om.apirestnutribalance.models.repositories.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    public List<Receta> getAllRecetas() {
        return recetaRepository.findAll();
    }

    public Optional<Receta> getRecetaById(Integer id) {
        return recetaRepository.findById(id);
    }

    public Receta createReceta(Receta receta) {
        return recetaRepository.save(receta);
    }

    public void deleteReceta(Integer id) {
        recetaRepository.deleteById(id);
    }
}
