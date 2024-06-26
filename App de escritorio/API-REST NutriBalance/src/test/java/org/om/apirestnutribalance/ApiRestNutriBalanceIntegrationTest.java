package org.om.apirestnutribalance;
import org.junit.jupiter.api.Test;
import org.om.apirestnutribalance.models.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiRestNutriBalanceIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllClientes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/nutribalance/clientes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    public void testCreateAndGetCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNombre("Nombre");
        cliente.setEmail("email@example.com");
        cliente.setTelefono("123456789");
        cliente.setFechaNacimiento(LocalDate.of(1990, 1, 1));
        cliente.setGenero("Masculino");

        String content = "{\"nombre\": \"Nombre\", \"email\": \"email@example.com\", \"telefono\": \"123456789\", \"fechaNacimiento\": \"1990-01-01\", \"genero\": \"Masculino\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/nutribalance/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/nutribalance/clientes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(11)))
                .andExpect(jsonPath("$[-1].nombre").value("Nombre"));
    }

}
