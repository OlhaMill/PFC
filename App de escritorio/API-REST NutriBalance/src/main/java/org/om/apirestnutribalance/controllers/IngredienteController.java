package org.om.apirestnutribalance.controllers;

import org.om.apirestnutribalance.models.entities.Ingrediente;
import org.om.apirestnutribalance.models.services.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nutribalance/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public List<Ingrediente> getAllIngredientes() {
        return ingredienteService.getAllIngredientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingrediente> getIngredienteById(@PathVariable Integer id) {
        Optional<Ingrediente> ingrediente = ingredienteService.getIngredienteById(id);
        if (ingrediente.isPresent()) {
            return ResponseEntity.ok(ingrediente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Ingrediente createIngrediente(@RequestBody Ingrediente ingrediente) {
        return ingredienteService.createIngrediente(ingrediente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngrediente(@PathVariable Integer id) {
        ingredienteService.deleteIngrediente(id);
        return ResponseEntity.noContent().build();
    }
}
