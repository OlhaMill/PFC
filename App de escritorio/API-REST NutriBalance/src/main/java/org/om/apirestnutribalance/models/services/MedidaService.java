package org.om.apirestnutribalance.models.services;

import org.om.apirestnutribalance.models.entities.Medida;
import org.om.apirestnutribalance.models.repositories.MedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedidaService {

    @Autowired
    private MedidaRepository medidaRepository;

    public List<Medida> getAllMedidas() {
        return medidaRepository.findAll();
    }

    public Optional<Medida> getMedidaById(Integer id) {
        return medidaRepository.findById(id);
    }

    public Medida createMedida(Medida medida) {
        return medidaRepository.save(medida);
    }

    public void deleteMedida(Integer id) {
        medidaRepository.deleteById(id);
    }
    public Medida save(Medida medida) {
        return medidaRepository.save(medida);
    }
}
