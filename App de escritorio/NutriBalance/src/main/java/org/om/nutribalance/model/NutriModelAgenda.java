package org.om.nutribalance.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.om.nutribalance.model.entities.Cliente;
import org.om.nutribalance.model.entities.Consulta;
import org.om.nutribalance.model.entities.Nutricionista;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NutriModelAgenda {
    public List<Consulta> obtenerConsultas(int id) {
        List<Consulta> listaConsultas = new ArrayList<>();
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://localhost:8080/nutribalance/consultas/nutricionista/" + id);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                String response = scanner.useDelimiter("\\Z").next();
                scanner.close();
                JSONArray jsonArray = new JSONArray(response);
                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                ObjectMapper mapper = new ObjectMapper();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Consulta consulta = new Consulta();
                    consulta.setId(jsonObject.getInt("id"));

                    String fechaHoraStr = jsonObject.getString("fechaHora");
                    LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);
                    consulta.setFechaHora(fechaHora);

                    consulta.setObservaciones(jsonObject.getString("observaciones"));
                    consulta.setPeso(jsonObject.getDouble("peso"));
                    consulta.setObjetivo(jsonObject.getString("objetivo"));
                    if (jsonObject.has("objetivoPeso")) {
                        consulta.setObjeivoPeso(jsonObject.getDouble("objetivoPeso"));
                    } else {
                        consulta.setObjeivoPeso(null); // o algún valor por defecto
                    }
                    consulta.setCliente(new Cliente(jsonObject.getInt("clienteId"), jsonObject.getString("clienteNombre")));

                    if (jsonObject.has("nutricionista")) {
                        Nutricionista nutricionista = mapper.readValue(jsonObject.getJSONObject("nutricionista").toString(), Nutricionista.class);
                        consulta.setNutricionista(nutricionista);
                    }
                    listaConsultas.add(consulta);
                }
            } else {
                throw new RuntimeException("Connection failed.\n" + conn.getResponseCode() + " " + conn.getResponseMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return listaConsultas;
    }
}
