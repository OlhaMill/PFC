package org.om.apirestnutribalance.models.services;

import org.om.apirestnutribalance.models.entities.Alimento;
import org.om.apirestnutribalance.models.repositories.AlimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    public List<Alimento> getAllAlimentos() {
        return alimentoRepository.findAll();
    }

    public Optional<Alimento> getAlimentoById(Integer id) {
        return alimentoRepository.findById(id);
    }

    public Alimento createAlimento(Alimento alimento) {
        return alimentoRepository.save(alimento);
    }

    public void deleteAlimento(Integer id) {
        alimentoRepository.deleteById(id);
    }
}

