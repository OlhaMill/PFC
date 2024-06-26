package org.om.apirestnutribalance;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.om.apirestnutribalance.controllers.ClienteController;
import org.om.apirestnutribalance.models.entities.Cliente;
import org.om.apirestnutribalance.models.services.ClienteService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class ApiRestNutriBalanceApplicationTests {
    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllClientes() {
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        when(clienteService.getAllClientes()).thenReturn(Arrays.asList(cliente1, cliente2));

        List<Cliente> result = clienteController.getAllClientes();
        assertEquals(2, result.size());
    }

    @Test
    void testGetClienteById() {
        Cliente cliente = new Cliente();
        when(clienteService.getClienteById(anyInt())).thenReturn(Optional.of(cliente));

        ResponseEntity<Cliente> response = clienteController.getClienteById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testCreateCliente() {
        doNothing().when(clienteService).createCliente(any(Cliente.class));

        clienteController.createCliente(new Cliente());
        verify(clienteService, times(1)).createCliente(any(Cliente.class));
    }

    @Test
    void testDeleteCliente() {
        doNothing().when(clienteService).deleteCliente(anyInt());

        ResponseEntity<Void> response = clienteController.deleteCliente(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(clienteService, times(1)).deleteCliente(anyInt());
    }

    @Test
    void testUpdateCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Nombre");
        cliente.setEmail("email@example.com");
        cliente.setTelefono("123456789");
        cliente.setFechaNacimiento(LocalDate.parse("1990-01-01"));
        cliente.setGenero("Masculino");

        when(clienteService.getClienteById(anyInt())).thenReturn(Optional.of(cliente));
        when(clienteService.save(any(Cliente.class))).thenReturn(cliente);

        ResponseEntity<Cliente> response = clienteController.updateCliente(1, cliente);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Nombre", response.getBody().getNombre());
    }
}
