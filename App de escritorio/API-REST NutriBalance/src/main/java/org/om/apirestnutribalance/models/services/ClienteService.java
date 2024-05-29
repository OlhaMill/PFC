package org.om.apirestnutribalance.models.services;

import org.om.apirestnutribalance.models.entities.Cliente;
import org.om.apirestnutribalance.models.entities.DietaReceta;
import org.om.apirestnutribalance.models.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Integer id) {
        return clienteRepository.findById(id);
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
    public List<Cliente> obtenerUltimosClientes() {
        return clienteRepository.obtenerUltimosClientes();
    }
    public List<Map<String, Object>> obtenerTop5Valoracion() {
        List<Object[]> results = clienteRepository.obtenerTop5Valoracion();
        return results.stream().map(result -> {
            Map<String, Object> map = new HashMap<>();
            map.put("nombre", result[0]);
            map.put("valoracion", result[1]);
            return map;
        }).collect(Collectors.toList());
    }
    public List<Cliente> getClientesByNutricionistaId(Integer nutricionistaId) {
        return clienteRepository.findByNutricionistaId(nutricionistaId);
    }
    public List<Cliente> searchClientes(String keyword, Integer nutrId) {
        return clienteRepository.searchByKeywordAndNutricionistaId(keyword, nutrId);
    }
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
