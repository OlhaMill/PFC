package org.om.apirestnutribalance.controllers;

import org.om.apirestnutribalance.models.entities.DietaReceta;
import org.om.apirestnutribalance.models.services.DietaRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nutribalance/dieta-recetas")
public class DietaRecetaController {

    @Autowired
    private DietaRecetaService dietaRecetaService;

    @GetMapping
    public List<DietaReceta> getAllDietaRecetas() {
        return dietaRecetaService.getAllDietaRecetas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietaReceta> getDietaRecetaById(@PathVariable Integer id) {
        Optional<DietaReceta> dietaReceta = dietaRecetaService.getDietaRecetaById(id);
        if (dietaReceta.isPresent()) {
            return ResponseEntity.ok(dietaReceta.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public DietaReceta createDietaReceta(@RequestBody DietaReceta dietaReceta) {
        return dietaRecetaService.createDietaReceta(dietaReceta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDietaReceta(@PathVariable Integer id) {
        dietaRecetaService.deleteDietaReceta(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/dieta/{dietaId}")
    public List<DietaReceta> getByDietaId(@PathVariable Integer dietaId) {
        return dietaRecetaService.findByDietaId(dietaId);
    }
}
