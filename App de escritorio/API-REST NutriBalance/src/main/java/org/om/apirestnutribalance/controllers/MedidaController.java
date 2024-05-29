package org.om.apirestnutribalance.controllers;

import org.om.apirestnutribalance.models.entities.Medida;
import org.om.apirestnutribalance.models.services.MedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nutribalance/medidas")
public class MedidaController {

    @Autowired
    private MedidaService medidaService;

    @GetMapping
    public List<Medida> getAllMedidas() {
        return medidaService.getAllMedidas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medida> getMedidaById(@PathVariable Integer id) {
        Optional<Medida> medida = medidaService.getMedidaById(id);
        if (medida.isPresent()) {
            return ResponseEntity.ok(medida.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Medida createMedida(@RequestBody Medida medida) {
        return medidaService.createMedida(medida);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedida(@PathVariable Integer id) {
        medidaService.deleteMedida(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Medida> updateMedida(@PathVariable int id, @RequestBody Medida medidaDetails) {
        Optional<Medida> optionalMedida = medidaService.getMedidaById(id);

        if (optionalMedida.isPresent()) {
            Medida medida = optionalMedida.get();
            medida.setPeso(medidaDetails.getPeso());
            medida.setImc(medidaDetails.getImc());
            medida.setCirCintura(medidaDetails.getCirCintura());
            medida.setCirCadera(medidaDetails.getCirCadera());
            medida.setCirAdicional(medidaDetails.getCirAdicional());

            Medida updatedMedida = medidaService.save(medida);
            return ResponseEntity.ok(updatedMedida);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
