package org.om.apirestnutribalance.controllers;

import org.om.apirestnutribalance.models.entities.Nutricionista;
import org.om.apirestnutribalance.models.services.NutricionistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nutribalance/nutricionistas")
public class NutricionistaController {

    @Autowired
    private NutricionistaService nutricionistaService;

    @GetMapping
    public List<Nutricionista> getAllNutricionistas() {
        return nutricionistaService.getAllNutricionistas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nutricionista> getNutricionistaById(@PathVariable Integer id) {
        Optional<Nutricionista> nutricionista = nutricionistaService.getNutricionistaById(id);
        if (nutricionista.isPresent()) {
            return ResponseEntity.ok(nutricionista.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Nutricionista createNutricionista(@RequestBody Nutricionista nutricionista) {
        return nutricionistaService.createNutricionista(nutricionista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNutricionista(@PathVariable Integer id) {
        nutricionistaService.deleteNutricionista(id);
        return ResponseEntity.noContent().build();
    }
}
