package org.om.apirestnutribalance.controllers;

import org.om.apirestnutribalance.models.entities.Alimento;
import org.om.apirestnutribalance.models.services.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nutribalance/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @GetMapping
    public List<Alimento> getAllAlimentos() {
        return alimentoService.getAllAlimentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alimento> getAlimentoById(@PathVariable Integer id) {
        Optional<Alimento> alimento = alimentoService.getAlimentoById(id);
        if (alimento.isPresent()) {
            return ResponseEntity.ok(alimento.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Alimento createAlimento(@RequestBody Alimento alimento) {
        return alimentoService.createAlimento(alimento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlimento(@PathVariable Integer id) {
        alimentoService.deleteAlimento(id);
        return ResponseEntity.noContent().build();
    }
}

