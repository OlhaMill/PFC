package org.om.apirestnutribalance.models.services;

import org.om.apirestnutribalance.models.entities.Ingrediente;
import org.om.apirestnutribalance.models.repositories.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public List<Ingrediente> getAllIngredientes() {
        return ingredienteRepository.findAll();
    }

    public Optional<Ingrediente> getIngredienteById(Integer id) {
        return ingredienteRepository.findById(id);
    }

    public Ingrediente createIngrediente(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    public void deleteIngrediente(Integer id) {
        ingredienteRepository.deleteById(id);
    }
}
