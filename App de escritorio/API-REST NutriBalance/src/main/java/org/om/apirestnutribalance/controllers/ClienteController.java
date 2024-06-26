package org.om.apirestnutribalance.controllers;

import org.om.apirestnutribalance.models.entities.Cliente;
import org.om.apirestnutribalance.models.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/nutribalance/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteService.getClienteById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void createCliente(@RequestBody Cliente cliente) {
        clienteService.createCliente(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/ultimos")
    public List<Cliente> obtenerUltimosClientes() {
        return clienteService.obtenerUltimosClientes();
    }
    @GetMapping("/ultimas-valoraciones")
    public List<Map<String, Object>> getUltimasValoraciones() {
        return clienteService.obtenerTop5Valoracion();
    }
    @GetMapping("/nutricionista/{nutricionistaId}")
    public List<Cliente> getClientesByNutricionistaId(@PathVariable Integer nutricionistaId) {
        return clienteService.getClientesByNutricionistaId(nutricionistaId);
    }
    @GetMapping("/buscar/{keyword}/nutricionista/{nutrId}")
    public List<Cliente> buscarClientes(@PathVariable String keyword, @PathVariable Integer nutrId) {
        return clienteService.searchClientes(keyword, nutrId);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable int id, @RequestBody Cliente clienteDetails) {
        Optional<Cliente> optionalCliente = clienteService.getClienteById(id);

        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            cliente.setNombre(clienteDetails.getNombre());
            cliente.setEmail(clienteDetails.getEmail());
            cliente.setTelefono(clienteDetails.getTelefono());
            cliente.setFechaNacimiento(clienteDetails.getFechaNacimiento());
            cliente.setGenero(clienteDetails.getGenero());

            Cliente updatedCliente = clienteService.save(cliente);
            return ResponseEntity.ok(updatedCliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
