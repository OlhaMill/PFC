package org.om.nutribalance.controllers;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.om.nutribalance.model.NutriModelCliente;
import org.om.nutribalance.model.NutriModelPrincipal;
import org.om.nutribalance.model.entities.*;
import org.om.nutribalance.util.DiaSemana;
import org.om.nutribalance.util.TipoComida;
import org.om.nutribalance.util.TipoComidaReceta;
import org.om.nutribalance.util.ValorNutrPorSemana;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class ClienteController {
    private int nutricionista_id = 1;
    private final NutriModelPrincipal modelPrincipal = new NutriModelPrincipal();
    private final NutriModelCliente modelCliente = new NutriModelCliente();
    private Cliente cliente;
    private boolean editando;
    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colCorreo;
    @FXML
    private TableColumn<Cliente, String> colTelefono;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblCorreo;
    @FXML
    private Label lblEdad;
    @FXML
    private Label lblTelefono;
    @FXML
    private Label lblObjetivo;
    @FXML
    private Label lblPesoDeseado;
    @FXML
    private Label lblPesoActual;
    @FXML
    private LineChart<Number, Number> grafico;
    @FXML
    private StackPane graficoConteiner;
    @FXML
    private VBox vBoxConsultasString;
    @FXML
    private VBox vBoxConsultasFecha;
    @FXML
    private TextField fieldBarraBuscar;
    @FXML
    private TableView<TipoComidaReceta> tablaDieta;
    @FXML
    private TableColumn<TipoComidaReceta, String> colTipoComida;
    @FXML
    private TableColumn<TipoComidaReceta, String> colLunes;
    @FXML
    private TableColumn<TipoComidaReceta, String> colMartes;
    @FXML
    private TableColumn<TipoComidaReceta, String> colMiercoles;
    @FXML
    private TableColumn<TipoComidaReceta, String> colJueves;
    @FXML
    private TableColumn<TipoComidaReceta, String> colViernes;
    @FXML
    private TableColumn<TipoComidaReceta, String> colSabado;
    @FXML
    private TableColumn<TipoComidaReceta, String> colDomingo;
    @FXML
    private VBox vBoxRes;
    @FXML
    private Group groupDieta;
    @FXML
    private TableView<ValorNutrPorSemana> tablaValorNutr;
    @FXML
    private TableColumn<ValorNutrPorSemana, String> colValorNutr;
    @FXML
    private TableColumn<ValorNutrPorSemana, String> colLunes1;
    @FXML
    private TableColumn<ValorNutrPorSemana, String> colMartes1;
    @FXML
    private TableColumn<ValorNutrPorSemana, String> colMiercoles1;
    @FXML
    private TableColumn<ValorNutrPorSemana, String> colJueves1;
    @FXML
    private TableColumn<ValorNutrPorSemana, String> colViernes1;
    @FXML
    private TableColumn<ValorNutrPorSemana, String> colSabado1;
    @FXML
    private TableColumn<ValorNutrPorSemana, String> colDomingo1;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Group groupBorrar;
    @FXML
    private StackPane stackBorrado;
    @FXML
    private Group groupDetalleCliente;
    @FXML
    private TextField fieldDetalleNombre;
    @FXML
    private TextField fieldDetalleCorreo;
    @FXML
    private TextField fieldDetalleTelefono;
    @FXML
    private TextField fieldDetallePeso;
    @FXML
    private TextField fieldDetalleIMC;
    @FXML
    private TextField fieldDetalleCintura;
    @FXML
    private TextField fieldDetalleCadera;
    @FXML
    private TextField fieldDetalleAdicional;
    @FXML
    private TextField fieldDetalleObservaciones;
    @FXML
    private TextField fieldDetallePesoDeseado;
    @FXML
    private TextField fieldDetalleValorEn;
    @FXML
    private TextField fieldDetalleHidratos;
    @FXML
    private TextField fieldDetalleGrasa;
    @FXML
    private TextField fieldDetalleProteina;
    @FXML
    private TextField fieldDetalleSal;
    @FXML
    private TextField fieldDetalleAzucar;
    @FXML
    private ComboBox<String> comboBoxGenero;
    @FXML
    private DatePicker datePickerFechaNac;
    @FXML
    private DatePicker datePickerFechaConsulta;
    @FXML
    private TableView<Consulta> tableDetalleConsultas;
    @FXML
    private TableColumn<Consulta, String> colFecha;
    @FXML
    private TableColumn<Consulta, String> colObservaciones;
    @FXML
    private ImageView btnCerrarWidget1;
    @FXML
    private ImageView btnCerrarWidget2;
    @FXML
    private ImageView btnCerrarWidget3;
    @FXML
    private Button btnBajarPeso;
    @FXML
    private Button btnMejorarSalud;
    @FXML
    private Button btnSubirPeso;

    @FXML
    public void initialize() {
        tablaClientes.setRowFactory(tv -> {
            TableRow<Cliente> row = new TableRow<>() {
                @Override
                protected void updateItem(Cliente item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        setPrefHeight(30);
                        setMaxHeight(30);
                    }
                }
            };
            return row;
        });
        colNombre.setCellValueFactory(cellData -> {
            Cliente cliente = cellData.getValue();
            return new ReadOnlyStringWrapper(cliente != null ? cliente.getNombre() : "");
        });
        colCorreo.setCellValueFactory(cellData -> {
            Cliente cliente = cellData.getValue();
            return new ReadOnlyStringWrapper(cliente != null ? cliente.getEmail() : "");
        });
        colTelefono.setCellValueFactory(cellData -> {
            Cliente cliente = cellData.getValue();
            return new ReadOnlyStringWrapper(cliente != null ? cliente.getTelefono() : "");
        });
        List<Cliente> listaClientes = modelCliente.obtenerClientes(nutricionista_id);
        ordenarClientesPorNombre(listaClientes);
        ObservableList<Cliente> listaClientesObs = FXCollections.observableArrayList(listaClientes);
        tablaClientes.setItems(listaClientesObs);
        // Seleccionar la primera fila por defecto y asignar a la variable cliente
        if (!listaClientesObs.isEmpty()) {
            tablaClientes.getSelectionModel().selectFirst();
            cliente = tablaClientes.getSelectionModel().getSelectedItem();
            cargarDatosCliente(cliente);
            cargarGrafico(cliente.getProgreso());
            cargarConsultas(cliente.getProgreso());
        }
        tablaClientes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Asigna el nuevo valor (fila seleccionada) a la variable
            cliente = newValue;
            if (cliente != null) {
                cargarDatosCliente(cliente);
                cargarGrafico(cliente.getProgreso());
                cargarConsultas(cliente.getProgreso());
            }
        });
        tablaValorNutr.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (tablaValorNutr.lookup("TableHeaderRow") != null) {
                ((Region) tablaValorNutr.lookup("TableHeaderRow")).setMaxHeight(0);
                ((Region) tablaValorNutr.lookup("TableHeaderRow")).setMinHeight(0);
                ((Region) tablaValorNutr.lookup("TableHeaderRow")).setPrefHeight(0);
            }
        });
        comboBoxGenero.setItems(FXCollections.observableArrayList("Femenino", "Masculino","Otro", "No identificado"));
    }
    private void cargarDatosCliente(Cliente cliente){
        lblNombre.setText(cliente.getNombre());
        lblCorreo.setText(cliente.getEmail());
        lblTelefono.setText(cliente.getTelefono());
        lblObjetivo.setText(cliente.getProgreso().get(0).getObjetivo());
        lblPesoDeseado.setText("Peso deseado: " + cliente.getProgreso().get(0).getObjeivoPeso().toString() + " kg");
        lblPesoActual.setText("Peso actual: " + cliente.getProgreso().get(0).getPeso().toString() + " kg");
        int edad = calcularEdad(cliente.getFechaNacimiento());
        lblEdad.setText(edad +" años");
    }
    public static int calcularEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
        }
        LocalDate fechaActual = LocalDate.now();
        if (fechaNacimiento.isAfter(fechaActual)) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser una fecha futura");
        }
        return Period.between(fechaNacimiento, fechaActual).getYears();
    }
    private void cargarGrafico(List<Consulta> listaConsultas) {
        // Definir los ejes del gráfico, ambos de tipo numérico
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        // Ocultar las etiquetas de los ticks, los ticks y las líneas de cuadrícula en los ejes
        xAxis.setTickLabelsVisible(false);
        yAxis.setTickLabelsVisible(false);
        xAxis.setTickMarkVisible(false);
        yAxis.setTickMarkVisible(false);
        xAxis.setOpacity(0);  // Hace el eje completamente invisible
        yAxis.setOpacity(0);  // Hace el eje completamente invisible
        yAxis.setTickLabelsVisible(true);
        yAxis.setTickMarkVisible(true);

        // Crear el gráfico de líneas
        LineChart<Number, Number> grafico = new LineChart<>(xAxis, yAxis);
        grafico.setVerticalGridLinesVisible(false);  // Oculta las líneas de cuadrícula verticales
        grafico.setHorizontalGridLinesVisible(true);  // Mostrar las líneas de cuadrícula horizontales
        grafico.setStyle("-fx-background-color: white;");  // Establecer el fondo en blanco
        grafico.setCreateSymbols(false);

        // Crear las series de datos
        XYChart.Series<Number, Number> pesoSeries = new XYChart.Series<>();
        pesoSeries.setName("Peso");

        XYChart.Series<Number, Number> imcSeries = new XYChart.Series<>();
        imcSeries.setName("IMC");

        for (int i = 0; i < listaConsultas.size(); i++) {
            Consulta consulta = listaConsultas.get(i);
            pesoSeries.getData().add(new XYChart.Data<>(i + 1, consulta.getMedidas().getPeso()));
            imcSeries.getData().add(new XYChart.Data<>(i + 1, consulta.getMedidas().getImc()));
        }

        // Agregar las series al gráfico
        grafico.getData().addAll(pesoSeries, imcSeries);

        // Limpiar el contenedor anterior y agregar el gráfico
        graficoConteiner.getChildren().clear();
        graficoConteiner.getChildren().add(grafico);
    }
    private void cargarConsultas(List<Consulta> listaConsultas) {
        vBoxConsultasFecha.getChildren().clear(); // Limpiar contenido anterior
        vBoxConsultasString.getChildren().clear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Consulta consulta : listaConsultas) {
            String estado;
            if (consulta.getFechaHora().atZone(java.time.ZoneId.systemDefault()).toLocalDate().isBefore(LocalDate.now())) {
                estado = "Realizada";
            } else {
                estado = "No realizada";
            }
            Label estadoLabel = new Label(estado); // Ejemplo de etiqueta de estado
            Label fechaLabel = new Label(formatter.format(consulta.getFechaHora().atZone(java.time.ZoneId.systemDefault()).toLocalDate()));
            // Aplicar estilos CSS
            estadoLabel.getStyleClass().add("tabla_letra");
            fechaLabel.getStyleClass().add("tabla_letra");
            estadoLabel.setPadding(new Insets(4));
            fechaLabel.setPadding(new Insets(4)); // Espacio entre etiquetas
            vBoxConsultasFecha.getChildren().add(fechaLabel);
            vBoxConsultasString.getChildren().add(estadoLabel);
        }
    }
    private static TableRow<ValorNutrPorSemana> call(TableView<ValorNutrPorSemana> tv) {
        TableRow<ValorNutrPorSemana> row = new TableRow<>() {
            @Override
            protected void updateItem(ValorNutrPorSemana item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setPrefHeight(30);
                    setMaxHeight(30);
                }
            }
        };
        return row;
    }
    private List<TipoComidaReceta> recetasPorDia(List<DietaReceta> listaRecetas) {
        TipoComidaReceta desayuno = new TipoComidaReceta();
        desayuno.setTipoComida("Desayuno");
        TipoComidaReceta almuerzo = new TipoComidaReceta();
        almuerzo.setTipoComida("Almuerzo");
        TipoComidaReceta comida = new TipoComidaReceta();
        comida.setTipoComida("Comida");
        TipoComidaReceta merienda = new TipoComidaReceta();
        merienda.setTipoComida("Merienda");
        TipoComidaReceta cena = new TipoComidaReceta();
        cena.setTipoComida("Cena");

        List<TipoComidaReceta> lista = new ArrayList<>();

        for (DietaReceta d : listaRecetas) {
            if (d.getTipoComida().equals(TipoComida.DESAYUNO)) {
                if (d.getDia().equals(DiaSemana.LUNES))
                    desayuno.setLunes(d.getReceta());
                if (d.getDia().equals(DiaSemana.MARTES))
                    desayuno.setMartes(d.getReceta());
                if (d.getDia().equals(DiaSemana.MIERCOLES))
                    desayuno.setMiercoles(d.getReceta());
                if (d.getDia().equals(DiaSemana.JUEVES))
                    desayuno.setJueves(d.getReceta());
                if (d.getDia().equals(DiaSemana.VIERNES))
                    desayuno.setViernes(d.getReceta());
                if (d.getDia().equals(DiaSemana.SABADO))
                    desayuno.setSabado(d.getReceta());
                if (d.getDia().equals(DiaSemana.DOMINGO))
                    desayuno.setDomingo(d.getReceta());
            }
            if (d.getTipoComida().equals(TipoComida.ALMUERZO)) {
                if (d.getDia().equals(DiaSemana.LUNES))
                    almuerzo.setLunes(d.getReceta());
                if (d.getDia().equals(DiaSemana.MARTES))
                    almuerzo.setMartes(d.getReceta());
                if (d.getDia().equals(DiaSemana.MIERCOLES))
                    almuerzo.setMiercoles(d.getReceta());
                if (d.getDia().equals(DiaSemana.JUEVES))
                    almuerzo.setJueves(d.getReceta());
                if (d.getDia().equals(DiaSemana.VIERNES))
                    almuerzo.setViernes(d.getReceta());
                if (d.getDia().equals(DiaSemana.SABADO))
                    almuerzo.setSabado(d.getReceta());
                if (d.getDia().equals(DiaSemana.DOMINGO))
                    almuerzo.setDomingo(d.getReceta());
            }
            if (d.getTipoComida().equals(TipoComida.COMIDA)) {
                if (d.getDia().equals(DiaSemana.LUNES))
                    comida.setLunes(d.getReceta());
                if (d.getDia().equals(DiaSemana.MARTES))
                    comida.setMartes(d.getReceta());
                if (d.getDia().equals(DiaSemana.MIERCOLES))
                    comida.setMiercoles(d.getReceta());
                if (d.getDia().equals(DiaSemana.JUEVES))
                    comida.setJueves(d.getReceta());
                if (d.getDia().equals(DiaSemana.VIERNES))
                    comida.setViernes(d.getReceta());
                if (d.getDia().equals(DiaSemana.SABADO))
                    comida.setSabado(d.getReceta());
                if (d.getDia().equals(DiaSemana.DOMINGO))
                    comida.setDomingo(d.getReceta());
            }
            if (d.getTipoComida().equals(TipoComida.MERIENDA)) {
                if (d.getDia().equals(DiaSemana.LUNES))
                    merienda.setLunes(d.getReceta());
                if (d.getDia().equals(DiaSemana.MARTES))
                    merienda.setMartes(d.getReceta());
                if (d.getDia().equals(DiaSemana.MIERCOLES))
                    merienda.setMiercoles(d.getReceta());
                if (d.getDia().equals(DiaSemana.JUEVES))
                    merienda.setJueves(d.getReceta());
                if (d.getDia().equals(DiaSemana.VIERNES))
                    merienda.setViernes(d.getReceta());
                if (d.getDia().equals(DiaSemana.SABADO))
                    merienda.setSabado(d.getReceta());
                if (d.getDia().equals(DiaSemana.DOMINGO))
                    merienda.setDomingo(d.getReceta());
            }
            if (d.getTipoComida().equals(TipoComida.CENA)) {
                if (d.getDia().equals(DiaSemana.LUNES))
                    cena.setLunes(d.getReceta());
                if (d.getDia().equals(DiaSemana.MARTES))
                    cena.setMartes(d.getReceta());
                if (d.getDia().equals(DiaSemana.MIERCOLES))
                    cena.setMiercoles(d.getReceta());
                if (d.getDia().equals(DiaSemana.JUEVES))
                    cena.setJueves(d.getReceta());
                if (d.getDia().equals(DiaSemana.VIERNES))
                    cena.setViernes(d.getReceta());
                if (d.getDia().equals(DiaSemana.SABADO))
                    cena.setSabado(d.getReceta());
                if (d.getDia().equals(DiaSemana.DOMINGO))
                    cena.setDomingo(d.getReceta());
            }
        }
        lista.add(desayuno);
        lista.add(almuerzo);
        lista.add(comida);
        lista.add(merienda);
        lista.add(cena);
        return lista;
    }
    private List<ValorNutrPorSemana> valorNutrPorSemanaList (ObservableList<TipoComidaReceta> listaRecetas){
        List<ValorNutrPorSemana> listaValorNutr = new ArrayList<>();
        double kcalDouble = 0;
        double grasaDouble = 0;
        double proteinasDouble = 0;
        double carboDouble = 0;
        ValorNutrPorSemana kcal = new ValorNutrPorSemana();
        kcal.setTipo("Kcal");
        ValorNutrPorSemana grasa = new ValorNutrPorSemana();
        grasa.setTipo("Grasa");
        ValorNutrPorSemana proteinas = new ValorNutrPorSemana();
        proteinas.setTipo("Proteinas");
        ValorNutrPorSemana hidratos = new ValorNutrPorSemana();
        hidratos.setTipo("Carbohidratos");

        for(TipoComidaReceta r : listaRecetas){
            if(!Objects.equals(r.getLunes().getNombre(), "-")) {
                kcalDouble += r.getLunes().getValorNutricional().getCalorias();
                grasaDouble += r.getLunes().getValorNutricional().getGrasa();
                proteinasDouble += r.getLunes().getValorNutricional().getProteina();
                carboDouble += r.getLunes().getValorNutricional().getCarbohidratos();
            }
        }
        kcal.setLunes(kcalDouble);
        grasa.setLunes(grasaDouble);
        proteinas.setLunes(proteinasDouble);
        hidratos.setLunes(carboDouble);
        kcalDouble = 0;
        grasaDouble = 0;
        proteinasDouble = 0;
        carboDouble = 0;
        for(TipoComidaReceta r : listaRecetas){
            if(!Objects.equals(r.getMartes().getNombre(), "-")) {
                kcalDouble += r.getMartes().getValorNutricional().getCalorias();
                grasaDouble += r.getMartes().getValorNutricional().getGrasa();
                proteinasDouble += r.getMartes().getValorNutricional().getProteina();
                carboDouble += r.getMartes().getValorNutricional().getCarbohidratos();
            }
        }
        kcal.setMartes(kcalDouble);
        grasa.setMartes(grasaDouble);
        proteinas.setMartes(proteinasDouble);
        hidratos.setMartes(carboDouble);
        kcalDouble = 0;
        grasaDouble = 0;
        proteinasDouble = 0;
        carboDouble = 0;
        for(TipoComidaReceta r : listaRecetas){
            if(!Objects.equals(r.getMiercoles().getNombre(), "-")) {
                kcalDouble += r.getMiercoles().getValorNutricional().getCalorias();
                grasaDouble += r.getMiercoles().getValorNutricional().getGrasa();
                proteinasDouble += r.getMiercoles().getValorNutricional().getProteina();
                carboDouble += r.getMiercoles().getValorNutricional().getCarbohidratos();
            }
        }
        kcal.setMiercoles(kcalDouble);
        grasa.setMiercoles(grasaDouble);
        proteinas.setMiercoles(proteinasDouble);
        hidratos.setMiercoles(carboDouble);
        kcalDouble = 0;
        grasaDouble = 0;
        proteinasDouble = 0;
        carboDouble = 0;
        for(TipoComidaReceta r : listaRecetas){
            if(!Objects.equals(r.getJueves().getNombre(), "-")) {
                kcalDouble += r.getJueves().getValorNutricional().getCalorias();
                grasaDouble += r.getJueves().getValorNutricional().getGrasa();
                proteinasDouble += r.getJueves().getValorNutricional().getProteina();
                carboDouble += r.getJueves().getValorNutricional().getCarbohidratos();
            }
        }
        kcal.setJueves(kcalDouble);
        grasa.setJueves(grasaDouble);
        proteinas.setJueves(proteinasDouble);
        hidratos.setJueves(carboDouble);
        kcalDouble = 0;
        grasaDouble = 0;
        proteinasDouble = 0;
        carboDouble = 0;
        for(TipoComidaReceta r : listaRecetas){
            if(!Objects.equals(r.getViernes().getNombre(), "-")) {
                kcalDouble += r.getViernes().getValorNutricional().getCalorias();
                grasaDouble += r.getViernes().getValorNutricional().getGrasa();
                proteinasDouble += r.getViernes().getValorNutricional().getProteina();
                carboDouble += r.getViernes().getValorNutricional().getCarbohidratos();
            }
        }
        kcal.setViernes(kcalDouble);
        grasa.setViernes(grasaDouble);
        proteinas.setViernes(proteinasDouble);
        hidratos.setViernes(carboDouble);
        kcalDouble = 0;
        grasaDouble = 0;
        proteinasDouble = 0;
        carboDouble = 0;
        for(TipoComidaReceta r : listaRecetas){
            if(!Objects.equals(r.getSabado().getNombre(), "-")) {
                kcalDouble += r.getSabado().getValorNutricional().getCalorias();
                grasaDouble += r.getSabado().getValorNutricional().getGrasa();
                proteinasDouble += r.getSabado().getValorNutricional().getProteina();
                carboDouble += r.getSabado().getValorNutricional().getCarbohidratos();
            }
        }
        kcal.setSabado(kcalDouble);
        grasa.setSabado(grasaDouble);
        proteinas.setSabado(proteinasDouble);
        hidratos.setSabado(carboDouble);
        kcalDouble = 0;
        grasaDouble = 0;
        proteinasDouble = 0;
        carboDouble = 0;
        for(TipoComidaReceta r : listaRecetas){
            if(!Objects.equals(r.getDomingo().getNombre(), "-")) {
                kcalDouble += r.getDomingo().getValorNutricional().getCalorias();
                grasaDouble += r.getDomingo().getValorNutricional().getGrasa();
                proteinasDouble += r.getDomingo().getValorNutricional().getProteina();
                carboDouble += r.getDomingo().getValorNutricional().getCarbohidratos();
            }
        }
        kcal.setDomingo(kcalDouble);
        grasa.setDomingo(grasaDouble);
        proteinas.setDomingo(proteinasDouble);
        hidratos.setDomingo(carboDouble);
        listaValorNutr.add(kcal);
        listaValorNutr.add(grasa);
        listaValorNutr.add(proteinas);
        listaValorNutr.add(hidratos);
        return listaValorNutr;
    }
    private void cargarTablasDieta() {
        tablaDieta.setRowFactory(tv -> {
            TableRow<TipoComidaReceta> row = new TableRow<>() {
                @Override
                protected void updateItem(TipoComidaReceta item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        setPrefHeight(75);
                        setMaxHeight(75);
                    }
                }
            };
            return row;
        });
        tablaValorNutr.setRowFactory(ClienteController::call);
        colTipoComida.setCellValueFactory(new PropertyValueFactory<>("tipoComida"));
        colLunes.setCellValueFactory(cellData -> {
            Receta receta = cellData.getValue().getLunes();
            return new ReadOnlyStringWrapper(receta != null ? receta.getNombre() : "");
        });
        colMartes.setCellValueFactory(cellData -> {
            Receta receta = cellData.getValue().getMartes();
            return new ReadOnlyStringWrapper(receta != null ? receta.getNombre() : "");
        });
        colMiercoles.setCellValueFactory(cellData -> {
            Receta receta = cellData.getValue().getMiercoles();
            return new ReadOnlyStringWrapper(receta != null ? receta.getNombre() : "");
        });
        colJueves.setCellValueFactory(cellData -> {
            Receta receta = cellData.getValue().getJueves();
            return new ReadOnlyStringWrapper(receta != null ? receta.getNombre() : "");
        });
        colViernes.setCellValueFactory(cellData -> {
            Receta receta = cellData.getValue().getViernes();
            return new ReadOnlyStringWrapper(receta != null ? receta.getNombre() : "");
        });
        colSabado.setCellValueFactory(cellData -> {
            Receta receta = cellData.getValue().getSabado();
            return new ReadOnlyStringWrapper(receta != null ? receta.getNombre() : "");
        });
        colDomingo.setCellValueFactory(cellData -> {
            Receta receta = cellData.getValue().getDomingo();
            return new ReadOnlyStringWrapper(receta != null ? receta.getNombre() : "");
        });
        List<DietaReceta> listaRecetas = modelPrincipal.obtenerDietaReceta(cliente.getListaDietas().get(0).getId());
        ObservableList<TipoComidaReceta> recetasPorDia = FXCollections.observableArrayList(recetasPorDia(listaRecetas));
        tablaDieta.setItems(recetasPorDia);
        colValorNutr.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colLunes1.setCellValueFactory(new PropertyValueFactory<>("lunes"));
        colMartes1.setCellValueFactory(new PropertyValueFactory<>("martes"));
        colMiercoles1.setCellValueFactory(new PropertyValueFactory<>("miercoles"));
        colJueves1.setCellValueFactory(new PropertyValueFactory<>("jueves"));
        colViernes1.setCellValueFactory(new PropertyValueFactory<>("viernes"));
        colSabado1.setCellValueFactory(new PropertyValueFactory<>("sabado"));
        colDomingo1.setCellValueFactory(new PropertyValueFactory<>("domingo"));
        List<ValorNutrPorSemana> listaValorNutr = valorNutrPorSemanaList(recetasPorDia);
        ObservableList<ValorNutrPorSemana> listaValores = FXCollections.observableArrayList(listaValorNutr);
        tablaValorNutr.setItems(listaValores);
    }
    private void cargarDetalleCliente() {
        fieldDetalleNombre.setText(cliente.getNombre() != null ? cliente.getNombre() : "-");
        fieldDetalleCorreo.setText(cliente.getEmail() != null ? cliente.getEmail() : "-");
        fieldDetalleTelefono.setText(cliente.getTelefono() != null ? cliente.getTelefono() : "-");

        if (cliente.getProgreso() != null && !cliente.getProgreso().isEmpty()) {
            fieldDetallePeso.setText(cliente.getProgreso().get(0).getPeso() != null ? cliente.getProgreso().get(0).getPeso().toString() : "-");
            fieldDetalleIMC.setText(cliente.getProgreso().get(0).getMedidas().getImc() != null ? cliente.getProgreso().get(0).getMedidas().getImc().toString() : "-");
            fieldDetalleCintura.setText(cliente.getProgreso().get(0).getMedidas().getCirCintura() != null ? cliente.getProgreso().get(0).getMedidas().getCirCintura().toString() : "-");
            fieldDetalleCadera.setText(cliente.getProgreso().get(0).getMedidas().getCirCadera() != null ? cliente.getProgreso().get(0).getMedidas().getCirCadera().toString() : "-");
            fieldDetalleAdicional.setText(cliente.getProgreso().get(0).getMedidas().getCirAdicional() != null ? cliente.getProgreso().get(0).getMedidas().getCirAdicional() : "-");
            fieldDetalleObservaciones.setText(cliente.getProgreso().get(0).getObservaciones() != null ? cliente.getProgreso().get(0).getObservaciones() : "-");
            fieldDetallePesoDeseado.setText(cliente.getProgreso().get(0).getObjeivoPeso() != null ? cliente.getProgreso().get(0).getObjeivoPeso().toString() : "-");
            if(cliente.getProgreso().get(0).getObjetivo().equals("Bajar Peso")){
                botonDetalleObjetivo(1);
            }else if(cliente.getProgreso().get(0).getObjetivo().equals("Mejorar salud")){
                botonDetalleObjetivo(2);
            } else if (cliente.getProgreso().get(0).getObjetivo().equals("Subir Peso")) {
                botonDetalleObjetivo(3);
            }
        } else {
            fieldDetallePeso.setText("-");
            fieldDetalleIMC.setText("-");
            fieldDetalleCintura.setText("-");
            fieldDetalleCadera.setText("-");
            fieldDetalleAdicional.setText("-");
            fieldDetalleObservaciones.setText("-");
            fieldDetallePesoDeseado.setText("-");
        }

        if (cliente.getValorNutricionalRec() != null) {
            fieldDetalleValorEn.setText(cliente.getValorNutricionalRec().getCalorias() != null ? cliente.getValorNutricionalRec().getCalorias().toString() : "-");
            fieldDetalleHidratos.setText(cliente.getValorNutricionalRec().getCarbohidratos() != null ? cliente.getValorNutricionalRec().getCarbohidratos().toString() : "-");
            fieldDetalleGrasa.setText(cliente.getValorNutricionalRec().getGrasa() != null ? cliente.getValorNutricionalRec().getGrasa().toString() : "-");
            fieldDetalleProteina.setText(cliente.getValorNutricionalRec().getProteina() != null ? cliente.getValorNutricionalRec().getProteina().toString() : "-");
            fieldDetalleSal.setText(cliente.getValorNutricionalRec().getSal() != null ? cliente.getValorNutricionalRec().getSal().toString() : "-");
            fieldDetalleAzucar.setText(cliente.getValorNutricionalRec().getAzucar() != null ? cliente.getValorNutricionalRec().getAzucar().toString() : "-");
        } else {
            fieldDetalleValorEn.setText("-");
            fieldDetalleHidratos.setText("-");
            fieldDetalleGrasa.setText("-");
            fieldDetalleProteina.setText("-");
            fieldDetalleSal.setText("-");
            fieldDetalleAzucar.setText("-");
        }

        comboBoxGenero.setValue(cliente.getGenero());
        datePickerFechaNac.setValue(cliente.getFechaNacimiento());
        if(!cliente.getProgreso().isEmpty()) {
            datePickerFechaConsulta.setValue(cliente.getProgreso().get(0).getFechaHora().toLocalDate());
            colFecha.setCellValueFactory(cellData -> {
                LocalDateTime fechaHora = cellData.getValue().getFechaHora();
                return new ReadOnlyStringWrapper(fechaHora.toLocalDate().toString());
            });
            colObservaciones.setCellValueFactory(cellData -> {
                String observaciones = cellData.getValue().getObservaciones();
                return new ReadOnlyStringWrapper(observaciones != null ? observaciones : "");
            });
            tableDetalleConsultas.setItems(FXCollections.observableArrayList(cliente.getProgreso()));
        }

    }
    protected void setEditField(boolean toDo, TextField... TextFields){
        for (TextField textField  : TextFields)
            textField.setEditable(toDo);
    }
    private void botonDetalleObjetivo(int boton) {
        if(boton == 1){
            btnBajarPeso.setStyle("-fx-background-color: #607D60; -fx-text-fill: #FFFFFF;");
            btnMejorarSalud.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: black;");
            btnSubirPeso.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: black;");
        }else if(boton == 2){
            btnMejorarSalud.setStyle("-fx-background-color: #607D60; -fx-text-fill: #FFFFFF;");
            btnBajarPeso.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: black;");
            btnSubirPeso.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: black;");
        }else if(boton == 3){
            btnSubirPeso.setStyle("-fx-background-color: #607D60; -fx-text-fill: #FFFFFF;");
            btnMejorarSalud.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: black;");
            btnBajarPeso.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: black;");
        }
    }
    public void ordenarClientesPorNombre(List<Cliente> listaClientes) {
        listaClientes.sort(new Comparator<Cliente>() {
            @Override
            public int compare(Cliente c1, Cliente c2) {
                return c1.getNombre().compareToIgnoreCase(c2.getNombre());
            }
        });
    }
    @FXML
    private void clickBuscarCliente(){
        if(!fieldBarraBuscar.getText().isEmpty()) {
            String filtro = fieldBarraBuscar.getText().toLowerCase();
            List<Cliente> listaFiltrada = modelCliente.buscarClientes(filtro, nutricionista_id);
            ObservableList<Cliente> listaClientesObs = FXCollections.observableArrayList(listaFiltrada);
            tablaClientes.setItems(listaClientesObs);
            if (!listaClientesObs.isEmpty()) {
                tablaClientes.getSelectionModel().selectFirst();
                cargarDatosCliente(tablaClientes.getSelectionModel().getSelectedItem());
            } else {
                lblNombre.setText("");
                lblCorreo.setText("");
                lblTelefono.setText("");
                lblObjetivo.setText("");
                lblPesoDeseado.setText("");
                lblPesoActual.setText("");
                lblEdad.setText("");
            }
        }
    }
    @FXML
    protected void clickCerrarDieta() {
        groupDieta.setVisible(false);
    }
    @FXML
    protected void clickVerDieta() {
        groupDieta.setVisible(true);
        cargarTablasDieta();
    }
    @FXML
    protected void clickBorrarCliente() {
        groupBorrar.setVisible(true);
    }
    @FXML
    protected void clickCerrarBorrar() {
        groupBorrar.setVisible(false);
    }
    @FXML
    protected void clickBorrarDef() {
        boolean borrado = modelCliente.borrarCliente(cliente.getId());
        if(borrado){
            stackBorrado.setVisible(true);
            // Create a timeline to hide the stackBorrado after 2 seconds
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.seconds(2),
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            groupBorrar.setVisible(false);
                        }
                    }
            ));
            timeline.play();
        }
    }
    @FXML
    protected void clickVerDetalleCliente() {
        groupDetalleCliente.setVisible(true);
        cargarDetalleCliente();
        comboBoxGenero.valueProperty().addListener(new ChangeListener<String>() {
            private boolean ignore = false;

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (ignore) {
                    return;
                }
                ignore = true;
                comboBoxGenero.setValue(oldValue); // Revertir el cambio de valor
                ignore = false;
            }
        });

        datePickerFechaConsulta.valueProperty().addListener(new ChangeListener<LocalDate>() {
            private boolean ignore = false;

            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (ignore) {
                    return;
                }
                ignore = true;
                datePickerFechaConsulta.setValue(oldValue); // Revertir el cambio de valor
                ignore = false;
            }
        });

        datePickerFechaNac.valueProperty().addListener(new ChangeListener<LocalDate>() {
            private boolean ignore = false;

            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (ignore) {
                    return;
                }
                ignore = true;
                datePickerFechaNac.setValue(oldValue); // Revertir el cambio de valor
                ignore = false;
            }
        });

    }
    @FXML
    protected void clickEditarDetalleWidget1() {
        setEditField(true,fieldDetalleNombre,fieldDetalleCorreo, fieldDetalleTelefono);
        comboBoxGenero.valueProperty().addListener((observable, oldValue, newValue) -> {
            comboBoxGenero.setValue(newValue); // Revertir el cambio de valor
        });
        datePickerFechaNac.valueProperty().addListener((observable, oldValue, newValue) -> {
            datePickerFechaNac.setValue(newValue); // Revertir el cambio de valor
        });
        btnCerrarWidget1.setVisible(true);
    }
    @FXML
    protected void clickGuardarDetalleWidget1() {
        btnCerrarWidget1.setVisible(false);
        cliente.setNombre(fieldDetalleNombre.getText());
        cliente.setEmail(fieldDetalleCorreo.getText());
        cliente.setTelefono(fieldDetalleTelefono.getText());
        cliente.setFechaNacimiento(datePickerFechaNac.getValue());
        cliente.setGenero(comboBoxGenero.getValue());
        setEditField(false,fieldDetalleNombre,fieldDetalleCorreo, fieldDetalleTelefono);
        comboBoxGenero.valueProperty().addListener((observable, oldValue, newValue) -> {
            comboBoxGenero.setValue(oldValue); // Revertir el cambio de valor
        });
        datePickerFechaNac.valueProperty().addListener((observable, oldValue, newValue) -> {
            datePickerFechaNac.setValue(oldValue); // Revertir el cambio de valor
        });
        modelCliente.actualizarCliente(cliente);
    }
    @FXML
    protected void clickEditarDetalleWidget2() {
        setEditField(true, fieldDetallePeso, fieldDetallePesoDeseado, fieldDetalleIMC, fieldDetalleCintura, fieldDetalleCadera, fieldDetalleAdicional, fieldDetalleObservaciones);
        datePickerFechaConsulta.valueProperty().addListener((observable, oldValue, newValue) -> {
            datePickerFechaConsulta.setValue(newValue); // Revertir el cambio de valor
        });
        editando = true;
        btnCerrarWidget2.setVisible(true);
    }
    @FXML
    protected void clickGuardarDetalleWidget2() {
        cliente.getProgreso().get(0).setPeso(Double.parseDouble(fieldDetallePeso.getText()));
        cliente.getProgreso().get(0).getMedidas().setPeso(Double.parseDouble(fieldDetallePeso.getText()));
        cliente.getProgreso().get(0).getMedidas().setImc(Double.parseDouble(fieldDetalleIMC.getText()));
        cliente.getProgreso().get(0).getMedidas().setCirCadera(Double.parseDouble(fieldDetalleCadera.getText()));
        cliente.getProgreso().get(0).getMedidas().setCirCintura(Double.parseDouble(fieldDetalleCintura.getText()));
        cliente.getProgreso().get(0).getMedidas().setCirCadera(Double.parseDouble(fieldDetalleCadera.getText()));
        cliente.getProgreso().get(0).getMedidas().setCirAdicional(fieldDetalleAdicional.getText());
        cliente.getProgreso().get(0).setObservaciones(fieldDetalleObservaciones.getText());

        setEditField(false, fieldDetallePeso, fieldDetalleIMC, fieldDetalleCintura, fieldDetalleCadera, fieldDetalleAdicional, fieldDetalleObservaciones);
        datePickerFechaConsulta.valueProperty().addListener((observable, oldValue, newValue) -> {
            datePickerFechaConsulta.setValue(oldValue); // Revertir el cambio de valor
        });
        editando = false;
        btnCerrarWidget2.setVisible(false);
        if(!cliente.getProgreso().isEmpty()) {
            modelCliente.actualizarConsulta(cliente.getProgreso().get(0));
            if(cliente.getProgreso().get(0).getMedidas() != null)
                modelCliente.actualizarMedida(cliente.getProgreso().get(0).getMedidas());
        }
    }
    @FXML
    protected void clickEditarDetalleWidget3() {
        setEditField(true, fieldDetallePesoDeseado);
        btnCerrarWidget3.setVisible(true);
        cliente.getProgreso().get(0).setObjeivoPeso(Double.parseDouble(fieldDetallePesoDeseado.getText()));
    }
    @FXML
    protected void clickGuardarDetalleWidget3() {
        setEditField(false, fieldDetallePesoDeseado);
        btnCerrarWidget3.setVisible(false);
        if(!cliente.getProgreso().isEmpty())
            modelCliente.actualizarConsulta(cliente.getProgreso().get(0));
        if(cliente.getValorNutricionalRec() != null)
            modelCliente.actualizarValorNutr(cliente.getValorNutricionalRec());
    }
    @FXML
    protected void clickCerrarDetalleCliente() {
        groupDetalleCliente.setVisible(false);
        cargarDatosCliente(cliente);
    }

    @FXML
    private void clickPestanaPrincipal(MouseEvent event) {
        try {
            // Cargar el archivo FXML secundario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/om/nutribalance/views/nutribalance-principal-view.fxml"));
            Parent root = loader.load();

            // Crear una nueva escena con el contenido del archivo FXML secundario
            Scene scene = new Scene(root);

            // Crear una nueva ventana (Stage)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            FadeTransition fadeOut = new FadeTransition(Duration.millis(500), anchorPane);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {
                // Mostrar la nueva ventana después de la animación
                stage.show();

                // Obtener el Stage actual y cerrarlo
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            });
            fadeOut.play();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void clickPestanaAgenda(MouseEvent event) {
        try {
            // Cargar el archivo FXML secundario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/om/nutribalance/views/nutribalance-agenda-view.fxml"));
            Parent root = loader.load();

            // Crear una nueva escena con el contenido del archivo FXML secundario
            Scene scene = new Scene(root);

            // Crear una nueva ventana (Stage)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            FadeTransition fadeOut = new FadeTransition(Duration.millis(500), anchorPane);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {
                // Mostrar la nueva ventana después de la animación
                stage.show();

                // Obtener el Stage actual y cerrarlo
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            });
            fadeOut.play();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void clickPestanaEstadisticas() {}
    @FXML
    private void clickPestanaAjustes() {}
    @FXML
    private void clickPestanaAlimentacion() {}

    @FXML
    protected void clickBajarPeso(){
        if(editando){
            botonDetalleObjetivo(1);
            cliente.getProgreso().get(0).setObjetivo("Bajar peso");
        }
    }
    @FXML
    protected void clickMejorarSalud(){
        if(editando){
            botonDetalleObjetivo(2);
            cliente.getProgreso().get(0).setObjetivo("Mejorar salud");
        }
    }
    @FXML
    protected void clickSubirPeso(){
        if(editando){
            botonDetalleObjetivo(3);
            cliente.getProgreso().get(0).setObjetivo("Subir peso");
        }
    }
}

