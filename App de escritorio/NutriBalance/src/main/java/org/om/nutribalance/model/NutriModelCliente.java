package org.om.nutribalance.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.om.nutribalance.model.entities.*;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class NutriModelCliente {
    public List<Cliente> obtenerClientes(int nutricionistaId) {
        List<Cliente> listaClientes = new ArrayList<>();
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://localhost:8080/nutribalance/clientes/nutricionista/" + nutricionistaId);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                String response = scanner.useDelimiter("\\Z").next();
                scanner.close();
                JSONArray jsonArray = new JSONArray(response);
                listaClientes = mapeoCliente(jsonArray);
            } else {
                throw new RuntimeException("Connection failed.\n" + conn.getResponseCode() + " " + conn.getResponseMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return listaClientes;
    }
    public List<Cliente> buscarClientes(String filtro, int nutricionistaId) {
        List<Cliente> listaClientes = new ArrayList<>();
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://localhost:8080/nutribalance/clientes/buscar/" + filtro + "/nutricionista/" + nutricionistaId);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                String response = scanner.useDelimiter("\\Z").next();
                scanner.close();
                JSONArray jsonArray = new JSONArray(response);
                listaClientes = mapeoCliente(jsonArray);
            } else {
                throw new RuntimeException("Connection failed.\n" + conn.getResponseCode() + " " + conn.getResponseMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return listaClientes;
    }

    private static List<Cliente> mapeoCliente(JSONArray jsonArray) throws JsonProcessingException {
        List<Cliente> listaClientes = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Cliente cliente = new Cliente();
            cliente.setId(jsonObject.getInt("id"));
            cliente.setNombre(jsonObject.getString("nombre"));
            cliente.setEmail(jsonObject.getString("email"));
            cliente.setTelefono(jsonObject.getString("telefono"));
            String fechaNacimientoStr = jsonObject.getString("fechaNacimiento");
            cliente.setFechaNacimiento(LocalDate.parse(fechaNacimientoStr, dateFormatter));
            if (!jsonObject.isNull("valorNutricionalRec")) {
                JSONObject valorNutricionalJson = jsonObject.getJSONObject("valorNutricionalRec");
                ValorNutricional valorNutricional = new ValorNutricional(
                        valorNutricionalJson.getInt("id"),
                        valorNutricionalJson.getDouble("calorias"),
                        valorNutricionalJson.getDouble("proteina"),
                        valorNutricionalJson.getDouble("grasa"),
                        valorNutricionalJson.getDouble("carbohidratos"),
                        valorNutricionalJson.getDouble("sal"),
                        valorNutricionalJson.getDouble("azucar")
                );
                cliente.setValorNutricionalRec(valorNutricional);
            }

            if (!jsonObject.isNull("fotos")) {
                String fotosStr = jsonObject.getJSONObject("fotos").toString();
                Map<String, Object> fotos = mapper.readValue(fotosStr, new TypeReference<Map<String, Object>>() {});
                cliente.setFotos(fotos);
            }

            cliente.setGenero(jsonObject.getString("genero"));
            cliente.setValoracion(jsonObject.getDouble("valoracion"));

            // Mapeo de progreso
            if (!jsonObject.isNull("progreso")) {
                JSONArray progresoArray = jsonObject.getJSONArray("progreso");
                List<Consulta> progresoList = new ArrayList<>();
                for (int j = 0; j < progresoArray.length(); j++) {
                    JSONObject progresoObject = progresoArray.getJSONObject(j);
                    Consulta consulta = new Consulta();
                    consulta.setId(progresoObject.getInt("id"));
                    consulta.setFechaHora(LocalDateTime.parse(progresoObject.getString("fechaHora"), dateTimeFormatter));
                    consulta.setObservaciones(progresoObject.getString("observaciones"));
                    consulta.setPeso(progresoObject.getDouble("peso"));
                    consulta.setObjetivo(progresoObject.getString("objetivo"));
                    consulta.setObjeivoPeso(progresoObject.getDouble("objetivoPeso"));

                    // Mapeo de medidas
                    if (!progresoObject.isNull("medidas")) {
                        JSONObject medidasArray = progresoObject.getJSONObject("medidas");
                        Medidas medida = new Medidas();
                        medida.setId(medidasArray.getInt("id"));
                        medida.setPeso(medidasArray.getDouble("peso"));
                        medida.setImc(medidasArray.getDouble("imc"));
                        medida.setCirCintura(medidasArray.getDouble("cirCintura"));
                        medida.setCirCadera(medidasArray.getDouble("cirCadera"));
                        medida.setCirAdicional(medidasArray.getString("cirAdicional"));
                        consulta.setMedidas(medida);
                    }
                    progresoList.add(consulta);
                    // Ordenar la lista por fecha de manera descendente
                    progresoList.sort(new Comparator<Consulta>() {
                        @Override
                        public int compare(Consulta c1, Consulta c2) {
                            return c2.getFechaHora().compareTo(c1.getFechaHora());
                        }
                    });
                }
                cliente.setProgreso(progresoList);
                if(!jsonObject.isNull("listaDietas")) {
                    JSONArray dietasArray = jsonObject.getJSONArray("listaDietas");
                    List<Dieta> dietasList = new ArrayList<>();

                    for (int j = 0; j < dietasArray.length(); j++) {
                        JSONObject dietasObject = dietasArray.getJSONObject(j);
                        Dieta dieta = new Dieta();
                        dieta.setId(dietasObject.getInt("dietaId"));
                        dietasList.add(dieta);
                        // Ordenar la lista por fecha de manera descendente
                        dietasList.sort(new Comparator<Dieta>() {
                            @Override
                            public int compare(Dieta d1, Dieta d2) {
                                return d2.getId().compareTo(d1.getId());
                            }
                        });
                    }
                    cliente.setListaDietas(dietasList);
                }
            }
            listaClientes.add(cliente);
        }
        return listaClientes;
    }
    public boolean borrarCliente(int id) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://localhost:8080/nutribalance/clientes/" + id);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            if (conn.getResponseCode() == 200)
                return true;
            else if (conn.getResponseCode() == 404){
                throw new RuntimeException("El cliente no existe");
            }
            else
                throw new RuntimeException("Fallo de conexión");
        }
        catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            if (conn != null)
                conn.disconnect();
        }
    }
    public void actualizarCliente(Cliente cliente) {
        HttpURLConnection conn = null;
        String jsonInputString = new JSONObject()
                .put("nombre", cliente.getNombre())
                .put("email", cliente.getEmail())
                .put("telefono", cliente.getTelefono())
                .put("fechaNacimiento", cliente.getFechaNacimiento().toString())
                .put("genero", cliente.getGenero()).toString();
        try {
            URL url = new URL("http://localhost:8080/nutribalance/clientes/" + cliente.getId());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json;utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            if (conn.getResponseCode() != 200)
                throw new RuntimeException("Connection failed\n" + conn.getResponseCode() + " " + conn.getResponseMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }
    public void actualizarValorNutr(ValorNutricional valorNutricional) {
        HttpURLConnection conn = null;
        String jsonInputString = new JSONObject()
                .put("calorias", valorNutricional.getCalorias())
                .put("proteina", valorNutricional.getProteina())
                .put("carbohidratos", valorNutricional.getCarbohidratos())
                .put("grasa", valorNutricional.getGrasa())
                .put("sal", valorNutricional.getSal())
                .put("azucar", valorNutricional.getAzucar()).toString();
        try {
            URL url = new URL("http://localhost:8080/nutribalance/valoresnutricional/" + valorNutricional.getId());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json;utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            if (conn.getResponseCode() != 200)
                throw new RuntimeException("Connection failed\n" + conn.getResponseCode() + " " + conn.getResponseMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }
    public void actualizarConsulta(Consulta consulta) {
        HttpURLConnection conn = null;
        String jsonInputString = new JSONObject()
                .put("observaciones", consulta.getObservaciones())
                .put("peso", consulta.getPeso())
                .put("objetivo", consulta.getObjetivo())
                .put("objetivoPeso", consulta.getObjeivoPeso()).toString();
        try {
            URL url = new URL("http://localhost:8080/nutribalance/consultas/" + consulta.getId());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json;utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            if (conn.getResponseCode() != 200)
                throw new RuntimeException("Connection failed\n" + conn.getResponseCode() + " " + conn.getResponseMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }
    public void actualizarMedida(Medidas medidas) {
        HttpURLConnection conn = null;
        String jsonInputString = new JSONObject()
                .put("peso", medidas.getPeso())
                .put("imc", medidas.getImc())
                .put("cirCintura", medidas.getCirCintura())
                .put("cirCadera", medidas.getCirCadera())
                .put("cirAdicional", medidas.getCirAdicional()).toString();
        try {
            URL url = new URL("http://localhost:8080/nutribalance/medidas/" + medidas.getId());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json;utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            if (conn.getResponseCode() != 200)
                throw new RuntimeException("Connection failed\n" + conn.getResponseCode() + " " + conn.getResponseMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }
}
