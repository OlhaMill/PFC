package org.om.apirestnutribalance.controllers;

import org.om.apirestnutribalance.models.entities.Receta;
import org.om.apirestnutribalance.models.services.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nutribalance/recetas")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @GetMapping
    public List<Receta> getAllRecetas() {
        return recetaService.getAllRecetas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> getRecetaById(@PathVariable Integer id) {
        Optional<Receta> receta = recetaService.getRecetaById(id);
        if (receta.isPresent()) {
            return ResponseEntity.ok(receta.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Receta createReceta(@RequestBody Receta receta) {
        return recetaService.createReceta(receta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceta(@PathVariable Integer id) {
        recetaService.deleteReceta(id);
        return ResponseEntity.noContent().build();
    }
}
