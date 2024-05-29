package org.om.apirestnutribalance.controllers;

import org.om.apirestnutribalance.models.entities.Dieta;
import org.om.apirestnutribalance.models.services.DietaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nutribalance/dietas")
public class DietaController {

    @Autowired
    private DietaService dietaService;

    @GetMapping
    public List<Dieta> getAllDietas() {
        return dietaService.getAllDietas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dieta> getDietaById(@PathVariable Integer id) {
        Optional<Dieta> dieta = dietaService.getDietaById(id);
        if (dieta.isPresent()) {
            return ResponseEntity.ok(dieta.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Dieta createDieta(@RequestBody Dieta dieta) {
        return dietaService.createDieta(dieta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDieta(@PathVariable Integer id) {
        dietaService.deleteDieta(id);
        return ResponseEntity.noContent().build();
    }
}
