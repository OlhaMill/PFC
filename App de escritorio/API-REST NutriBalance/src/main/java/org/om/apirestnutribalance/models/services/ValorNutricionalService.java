package org.om.apirestnutribalance.models.services;

import org.om.apirestnutribalance.models.entities.ValorNutricional;
import org.om.apirestnutribalance.models.repositories.ValorNutricionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValorNutricionalService {

    @Autowired
    private ValorNutricionalRepository valorNutricionalRepository;

    public List<ValorNutricional> getAllValoresNutricionales() {
        return valorNutricionalRepository.findAll();
    }

    public Optional<ValorNutricional> getValorNutricionalById(Integer id) {
        return valorNutricionalRepository.findById(id);
    }

    public ValorNutricional createValorNutricional(ValorNutricional valorNutricional) {
        return valorNutricionalRepository.save(valorNutricional);
    }

    public void deleteValorNutricional(Integer id) {
        valorNutricionalRepository.deleteById(id);
    }
    public ValorNutricional save(ValorNutricional valorNutricional) {
        return valorNutricionalRepository.save(valorNutricional);
    }
}
