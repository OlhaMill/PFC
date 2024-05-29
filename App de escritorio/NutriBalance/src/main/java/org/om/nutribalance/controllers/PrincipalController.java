package org.om.nutribalance.controllers;
import javafx.animation.FadeTransition;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.om.nutribalance.model.NutriModelPrincipal;
import org.om.nutribalance.model.entities.*;
import org.om.nutribalance.util.DiaSemana;
import org.om.nutribalance.util.TipoComida;
import org.om.nutribalance.util.TipoComidaReceta;
import org.om.nutribalance.util.ValorNutrPorSemana;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PrincipalController {
    private int nutricionista_id = 1;
    private static final int MAX_STARS = 5;
    private static final String FULL_STAR = "/org/om/nutribalance/iconos/full_star.png";
    private static final String EMPTY_STAR = "/org/om/nutribalance/iconos/empty_star.png";
    private static final String HALF_STAR = "/org/om/nutribalance/iconos/half_star.png";
    private static final int STAR_SIZE = 17;
    private int dietaId = 1;

    private final NutriModelPrincipal modelPrincipal = new NutriModelPrincipal();
    @FXML
    private TableView<Cliente> listaNuevosClientes;
    @FXML
    private TableView<Consulta> tablaUltimasConsultas;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colEmail;
    @FXML
    private TableColumn<Consulta, String> colFecha;
    @FXML
    private TableColumn<Consulta, String> colHora;
    @FXML
    private TableColumn<Consulta, String> colCliente;
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
    private Group groupElegirDieta;
    @FXML
    private ComboBox<Dieta> comboBoxElegirDieta;
    @FXML
    private AnchorPane anchorPane;

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

    @FXML
    public void initialize() {
        // Configurar las columnas
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Obtener los clientes desde el modelo
        List<Cliente> listaClientes = modelPrincipal.ultimosClientes();
        ObservableList<Cliente> listaClientesObs = FXCollections.observableArrayList();
        listaClientesObs.addAll(listaClientes);

        // Asignar la lista observable a la tabla
        listaNuevosClientes.setItems(listaClientesObs);

        Map<Double, String> valoraciones = modelPrincipal.obtenerValoraciones();
        for (Map.Entry<Double, String> entry : valoraciones.entrySet()) {
            String nombre = entry.getValue();
            double valoracion = entry.getKey();

            HBox valoracionBox = new HBox();
            valoracionBox.setPrefHeight(19);
            valoracionBox.setMaxHeight(19);// Establecer altura de cada HBox

            HBox starBox = new HBox();
            updateStarBox(starBox, valoracion);
            valoracionBox.setPrefHeight(17);
            // Establecer margen para starBox
            HBox.setMargin(starBox, new Insets(10, 0, 0, 50));

            Label nombreLabel = new Label(nombre);
            nombreLabel.getStyleClass().add("letra_principal_consulta");
            valoracionBox.setPrefHeight(17);
            // Establecer margen para nombreLabel
            HBox.setMargin(nombreLabel, new Insets(10, 0, 0, 50));
            valoracionBox.getChildren().addAll(starBox, nombreLabel); // Añadir primero la imagen y luego el nombre

            vBoxRes.getChildren().add(valoracionBox);

            List<Consulta> consultas = modelPrincipal.ultimasConsultas(nutricionista_id);

            colFecha.setCellValueFactory(cellData -> {
                LocalDateTime fechaHora = cellData.getValue().getFechaHora();
                return new ReadOnlyStringWrapper(fechaHora.toLocalDate().toString());
            });
            colHora.setCellValueFactory(cellData -> {
                LocalDateTime fechaHora = cellData.getValue().getFechaHora();
                return new ReadOnlyStringWrapper(fechaHora.toLocalTime().toString());
            });
            colCliente.setCellValueFactory(cellData -> {
                Cliente cliente = cellData.getValue().getCliente();
                return new ReadOnlyStringWrapper(cliente != null ? cliente.getNombre() : "");
            });
            ObservableList<Consulta> consultaList = FXCollections.observableArrayList(consultas);
            tablaUltimasConsultas.setItems(consultaList);
        }


        List<Dieta> listaDietas = modelPrincipal.obtenerDietas();
        ObservableList<Dieta>listaDietasObs = FXCollections.observableArrayList();
        listaDietasObs.addAll(listaDietas);
        comboBoxElegirDieta.setItems(listaDietasObs);

        comboBoxElegirDieta.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            dietaId = newValue.getId();
        });
        tablaValorNutr.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (tablaValorNutr.lookup("TableHeaderRow") != null) {
                ((Region) tablaValorNutr.lookup("TableHeaderRow")).setMaxHeight(0);
                ((Region) tablaValorNutr.lookup("TableHeaderRow")).setMinHeight(0);
                ((Region) tablaValorNutr.lookup("TableHeaderRow")).setPrefHeight(0);
            }
        });
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
    private void updateStarBox(HBox starBox, double rating) {
        starBox.getChildren().clear(); // Limpiar cualquier estrella existente
        for (int i = 1; i <= MAX_STARS; i++) {
            ImageView starImageView = new ImageView();
            starImageView.setFitWidth(STAR_SIZE); // Establecer ancho de la estrella
            starImageView.setFitHeight(STAR_SIZE); // Establecer altura de la estrella
            if (rating >= i) {
                starImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(FULL_STAR))));
            } else if (rating >= i - 0.5) {
                starImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(HALF_STAR))));
            } else {
                starImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(EMPTY_STAR))));
            }
            starBox.getChildren().add(starImageView);
        }
    }
    @FXML
    protected void clickModificarDieta() {
        groupElegirDieta.setVisible(true);
    }
    @FXML
    protected void clickCerrarDieta() {
        groupDieta.setVisible(false);
        groupElegirDieta.setVisible(false);
    }
    @FXML
    protected void clickElegirDieta() {
        groupDieta.setVisible(true);
        cargarTablasDieta();
    }
    @FXML
    private void clickPestanaCliente(MouseEvent event) {
        try {
            // Cargar el archivo FXML secundario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/om/nutribalance/views/nutribalance-clientes-view.fxml"));
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
    private void clickPestanaEstadisticas(){}
    @FXML
    private void clickPestanaAjustes() {}
    @FXML
    private void clickPestanaAlimentacion(){}

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
        tablaValorNutr.setRowFactory(PrincipalController::call);
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
        List<DietaReceta> listaRecetas = modelPrincipal.obtenerDietaReceta(dietaId);
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
}



