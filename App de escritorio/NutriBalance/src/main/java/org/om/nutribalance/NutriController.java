package org.om.nutribalance;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class NutriController {
    @FXML
    private Label welcomeText;
    @FXML
    private ImageView img_receta;
    @FXML
    public void initialize() {
        // Cargar la imagen
        Image image = new Image("src/main/resources/org/om/nutribalance/iconos/fondo_img.png");
        img_receta.setImage(image);

        // Crear un rectángulo con esquinas redondeadas
        Rectangle clip = new Rectangle(
                img_receta.getFitWidth(), img_receta.getFitHeight()
        );
        clip.setArcWidth(10); // Ajusta este valor para redondear más o menos las esquinas
        clip.setArcHeight(10);

        // Aplicar el clip al ImageView
        img_receta.setClip(clip);
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}