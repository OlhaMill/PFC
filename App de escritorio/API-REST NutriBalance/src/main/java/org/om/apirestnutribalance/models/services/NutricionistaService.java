package org.om.apirestnutribalance.models.services;

import org.om.apirestnutribalance.models.entities.Nutricionista;
import org.om.apirestnutribalance.models.repositories.NutricionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NutricionistaService {

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    public List<Nutricionista> getAllNutricionistas() {
        return nutricionistaRepository.findAll();
    }

    public Optional<Nutricionista> getNutricionistaById(Integer id) {
        return nutricionistaRepository.findById(id);
    }

    public Nutricionista createNutricionista(Nutricionista nutricionista) {
        return nutricionistaRepository.save(nutricionista);
    }

    public void deleteNutricionista(Integer id) {
        nutricionistaRepository.deleteById(id);
    }
}
