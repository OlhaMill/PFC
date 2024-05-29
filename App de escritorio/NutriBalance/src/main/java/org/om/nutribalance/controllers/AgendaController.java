package org.om.nutribalance.controllers;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.om.nutribalance.model.NutriModelAgenda;
import org.om.nutribalance.model.entities.Consulta;

import java.time.LocalDateTime;
import java.util.List;

public class AgendaController {
    NutriModelAgenda modelAgenda = new NutriModelAgenda();
    private int nutricionistaId = 1;

    @FXML
    private GridPane paneCalendar;
    @FXML
    private CalendarView calendarView;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void initialize() {
        calendarView = new CalendarView();
        paneCalendar.getChildren().add(calendarView);

        // Obtener la lista de consultas desde el modelo
        List<Consulta> consultas = modelAgenda.obtenerConsultas(nutricionistaId);

        Calendar calendar = new Calendar("Consultas");

        for (Consulta consulta : consultas) {
            Entry<String> entry = new Entry<>(consulta.getObjetivo());
            LocalDateTime start = consulta.getFechaHora();
            LocalDateTime end = start.plusHours(1); // Suponiendo que cada consulta dura 1 hora

            entry.setInterval(start, end);
            entry.setTitle("Cliente: " + consulta.getCliente().getNombre() + "\nObservaciones: " + consulta.getObservaciones());
            calendar.addEntry(entry);
        }

        CalendarSource myCalendarSource = new CalendarSource("My Calendars");
        myCalendarSource.getCalendars().add(calendar);

        calendarView.getCalendarSources().add(myCalendarSource);
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
    private void clickPestanaEstadisticas() {}
    @FXML
    private void clickPestanaAjustes() {}
    @FXML
    private void clickPestanaAlimentacion() {}
}
