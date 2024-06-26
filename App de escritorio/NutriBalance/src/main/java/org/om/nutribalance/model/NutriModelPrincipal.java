package org.om.nutribalance.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.om.nutribalance.model.entities.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.om.nutribalance.util.DiaSemana;
import org.om.nutribalance.util.TipoComida;

public class NutriModelPrincipal {
    public List<Cliente> ultimosClientes(){
                    List<Cliente> listaClientes = new ArrayList<>();
                    HttpURLConnection conn = null;
                    try {
                        URL url = new URL(
                                "http://localhost:8080/nutribalance/clientes/ultimos");
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestProperty("Accept", "application/json");
                        if (conn.getResponseCode() == 200) {
                            Scanner scanner = new Scanner(conn.getInputStream());
                String response = scanner.useDelimiter("\\Z").next();
                scanner.close();
                JSONArray jsonArray = new JSONArray(response);
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
                ObjectMapper mapper = new ObjectMapper();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject)
                            jsonArray.get(i);
                    Cliente cliente = new Cliente();
                    cliente.setId(jsonObject.getInt("id"));
                    cliente.setNombre(jsonObject.getString("nombre"));
                    cliente.setEmail(jsonObject.getString("email"));
                    cliente.setTelefono(jsonObject.getString("telefono"));
                    String fechaNacimientoStr = jsonObject.getString("fechaNacimiento");
                    cliente.setFechaNacimiento(LocalDate.parse(fechaNacimientoStr, formatter));
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
                    if (!jsonObject.isNull("valoracion"))
                        cliente.setValoracion(jsonObject.getDouble("valoracion"));

                    listaClientes.add(cliente);
                }
            }
            else
                throw new RuntimeException("Connection failed.\n" + conn.getResponseCode() + " " + conn.getResponseMessage());
        }
        catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            if (conn != null)
                conn.disconnect();
        }
        return listaClientes;
    }
    public Map<Double, String> obtenerValoraciones() {
        Map<Double, String> valoracionesMap = new HashMap<>();
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://localhost:8080/nutribalance/clientes/ultimas-valoraciones");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                String response = scanner.useDelimiter("\\A").next();
                scanner.close();
                int count = 0;
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    if (count >= 5) {
                        break; // Salir del bucle si ya se han añadido cinco registros
                    }
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String nombre = jsonObject.getString("nombre");
                    if (jsonObject.has("valoracion") && !jsonObject.isNull("valoracion")) {
                        double valoracion = jsonObject.getDouble("valoracion");
                        valoracionesMap.put(valoracion, nombre);
                        count++;
                    }
                }
            } else {
                throw new RuntimeException("Error: " + conn.getResponseCode() + " " + conn.getResponseMessage());
            }
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            if (conn != null)
                conn.disconnect();
        }
        return valoracionesMap;
    }
    public List<Consulta> ultimasConsultas(int id) {
        List<Consulta> listaConsultas = new ArrayList<>();
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://localhost:8080/nutribalance/consultas/ultimas/" + id);
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
    public List<DietaReceta> obtenerDietaReceta(int dietaId) {
        List<DietaReceta> lista = new ArrayList<>();
        HttpURLConnection conn = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            URL url = new URL("http://localhost:8080/nutribalance/dieta-recetas/dieta/" + dietaId);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                String response = scanner.useDelimiter("\\Z").next();
                scanner.close();

                JSONArray jsonArray = new JSONArray(response);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Integer id = jsonObject.getInt("id");
                    DiaSemana dia = DiaSemana.valueOf(jsonObject.getString("dia").toUpperCase());
                    int dieta = jsonObject.getInt("dietaId");
                    // Convertir el objeto receta a un mapa y luego mapear manualmente
                    Map<String, Object> recetaMap = mapper.readValue(jsonObject.getJSONObject("receta").toString(), Map.class);
                    Receta receta = mapearReceta(recetaMap);
                    TipoComida tipoComida = TipoComida.valueOf(jsonObject.getString("tipoComida").toUpperCase());

                    DietaReceta dietaReceta = new DietaReceta(id, dia, dieta, receta, tipoComida);
                    lista.add(dietaReceta);
                }
            } else {
                throw new RuntimeException("Connection failed. Response code: " + conn.getResponseCode());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return lista;
    }
    public List<Dieta> obtenerDietas(){
        List<Dieta> listaDietas = new ArrayList<>();
        HttpURLConnection conn = null;
        try {
            URL url = new URL(
                    "http://localhost:8080/nutribalance/dietas");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                String response = scanner.useDelimiter("\\Z").next();
                scanner.close();
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject)
                            jsonArray.get(i);
                    Dieta dieta = new Dieta();
                    dieta.setId(jsonObject.getInt("id"));
                    dieta.setNombre(jsonObject.getString("nombre"));
                    listaDietas.add(dieta);
                }
            }
            else
                throw new RuntimeException("Connection failed.\n" + conn.getResponseCode() + " " + conn.getResponseMessage());
        }
        catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            if (conn != null)
                conn.disconnect();
        }
        return listaDietas;
    }
    public Receta mapearReceta(Map<String, Object> datosReceta) {
        Receta receta = new Receta();

        receta.setId((Integer) datosReceta.get("id"));
        receta.setNombre((String) datosReceta.get("nombre"));

        List<Map<String, Object>> listaIngredientesMap = (List<Map<String, Object>>) datosReceta.get("ingredientes");
        List<Ingrediente> listaIngredientes = new ArrayList<>();
        for (Map<String, Object> ingredienteMap : listaIngredientesMap) {
            Ingrediente ingrediente = new Ingrediente();
            ingrediente.setId((Integer) ingredienteMap.get("id"));
            ingrediente.setCantidad(convertToDouble(ingredienteMap.get("cantidad")));
            Alimento alimento = new Alimento();
            alimento.setId((Integer) ((Map<String, Object>) ingredienteMap.get("alimento")).get("id"));
            alimento.setNombre((String) ((Map<String, Object>) ingredienteMap.get("alimento")).get("nombre"));
            ingrediente.setAlimento(alimento);
            listaIngredientes.add(ingrediente);
        }
        receta.setListaIngredientes(listaIngredientes);

        receta.setInstrucciones((List<String>) datosReceta.get("instrucciones"));

        Map<String, Object> valorNutricionalMap = (Map<String, Object>) datosReceta.get("valorNutricional");
        ValorNutricional valorNutricional = new ValorNutricional();
        valorNutricional.setGrasa(convertToDouble(valorNutricionalMap.get("grasa")));
        valorNutricional.setCalorias(convertToDouble(valorNutricionalMap.get("calorias")));
        valorNutricional.setProteina(convertToDouble(valorNutricionalMap.get("proteina")));
        valorNutricional.setCarbohidratos(convertToDouble(valorNutricionalMap.get("carbohidratos")));
        valorNutricional.setAzucar(convertToDouble(valorNutricionalMap.get("azucar")));
        valorNutricional.setSal(convertToDouble(valorNutricionalMap.get("sal")));
        receta.setValorNutricional(valorNutricional);

        receta.setFoto((String) datosReceta.get("foto"));
        receta.setDificultad(convertToDouble(datosReceta.get("dificultad")));
        receta.setTiempoPreparacion((Integer) datosReceta.get("tiempoPreparacion"));
        receta.setCreadoPor((Integer) datosReceta.get("creadoPorId"));

        return receta;
    }
    private static Double convertToDouble(Object obj) {
        if (obj instanceof Double) {
            return (Double) obj;
        } else if (obj instanceof Integer) {
            return ((Integer) obj).doubleValue();
        } else if (obj instanceof String) {
            return Double.parseDouble((String) obj);
        } else {
            return null;
        }
    }
}
