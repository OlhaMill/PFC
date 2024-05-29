package org.om.apirestnutribalance.controllers;

import org.om.apirestnutribalance.models.entities.Cliente;
import org.om.apirestnutribalance.models.entities.Consulta;
import org.om.apirestnutribalance.models.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nutribalance/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public List<Consulta> getAllConsultas() {
        return consultaService.getAllConsultas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> getConsultaById(@PathVariable Integer id) {
        Optional<Consulta> consulta = consultaService.getConsultaById(id);
        if (consulta.isPresent()) {
            return ResponseEntity.ok(consulta.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Consulta> updateConsulta(@PathVariable int id, @RequestBody Consulta consultaDetails) {
        Optional<Consulta> optionalConsulta = consultaService.getConsultaById(id);

        if (optionalConsulta.isPresent()) {
            Consulta consulta = optionalConsulta.get();
            consulta.setObservaciones(consultaDetails.getObservaciones());
            consulta.setPeso(consultaDetails.getPeso());
            consulta.setObjetivo(consultaDetails.getObjetivo());
            consulta.setObjetivoPeso(consultaDetails.getObjetivoPeso());

            Consulta updatedConsulta = consultaService.save(consulta);
            return ResponseEntity.ok(updatedConsulta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Consulta createConsulta(@RequestBody Consulta consulta) {
        return consultaService.createConsulta(consulta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Integer id) {
        consultaService.deleteConsulta(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/ultimas/{id}")
    public ResponseEntity<List<Consulta>> obtenerUltimasConsultas(@PathVariable Long id) {
        List<Consulta> ultimasConsultas = consultaService.obtenerUltimasConsultasPorId(id);
        return ResponseEntity.ok(ultimasConsultas);
    }
    @GetMapping("/nutricionista/{nutricionistaId}")
    public List<Consulta> getConsultasByNutricionistaId(@PathVariable Integer nutricionistaId) {
        return consultaService.getConsultasByNutricionistaId(nutricionistaId);
    }
}
