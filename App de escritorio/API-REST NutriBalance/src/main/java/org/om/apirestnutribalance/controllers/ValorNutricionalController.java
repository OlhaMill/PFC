package org.om.apirestnutribalance.controllers;

import org.om.apirestnutribalance.models.entities.ValorNutricional;
import org.om.apirestnutribalance.models.services.ValorNutricionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nutribalance/valoresnutricional")
public class ValorNutricionalController {

    @Autowired
    private ValorNutricionalService valorNutricionalService;

    @GetMapping
    public List<ValorNutricional> getAllValoresNutricionales() {
        return valorNutricionalService.getAllValoresNutricionales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ValorNutricional> getValorNutricionalById(@PathVariable Integer id) {
        Optional<ValorNutricional> valorNutricional = valorNutricionalService.getValorNutricionalById(id);
        if (valorNutricional.isPresent()) {
            return ResponseEntity.ok(valorNutricional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ValorNutricional createValorNutricional(@RequestBody ValorNutricional valorNutricional) {
        return valorNutricionalService.createValorNutricional(valorNutricional);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteValorNutricional(@PathVariable Integer id) {
        valorNutricionalService.deleteValorNutricional(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ValorNutricional> updateValorNutricional(@PathVariable int id, @RequestBody ValorNutricional valorNutricionalDetails) {
        Optional<ValorNutricional> optionalValorNutricional = valorNutricionalService.getValorNutricionalById(id);

        if (optionalValorNutricional.isPresent()) {
            ValorNutricional valorNutricional = optionalValorNutricional.get();
            valorNutricional.setCalorias(valorNutricionalDetails.getCalorias());
            valorNutricional.setProteina(valorNutricionalDetails.getProteina());
            valorNutricional.setGrasa(valorNutricionalDetails.getGrasa());
            valorNutricional.setCarbohidratos(valorNutricionalDetails.getCarbohidratos());
            valorNutricional.setSal(valorNutricionalDetails.getSal());
            valorNutricional.setAzucar(valorNutricionalDetails.getAzucar());

            ValorNutricional updatedValorNutricional = valorNutricionalService.save(valorNutricional);
            return ResponseEntity.ok(updatedValorNutricional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
