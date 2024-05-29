package org.om.apirestnutribalance.models.services;

import org.om.apirestnutribalance.models.entities.Cliente;
import org.om.apirestnutribalance.models.entities.Consulta;
import org.om.apirestnutribalance.models.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> getAllConsultas() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> getConsultaById(Integer id) {
        return consultaRepository.findById(id);
    }

    public Consulta createConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public void deleteConsulta(Integer id) {
        consultaRepository.deleteById(id);
    }
    public List<Consulta> obtenerUltimasConsultasPorId(Long id) {
        return consultaRepository.findTop6ByIdOrderByFechaHoraDesc(id);
    }
    public List<Consulta> getConsultasByNutricionistaId(Integer nutricionistaId) {
        return consultaRepository.findByNutricionistaId(nutricionistaId);
    }
    public Consulta save(Consulta consulta) {
        return consultaRepository.save(consulta);
    }
}
