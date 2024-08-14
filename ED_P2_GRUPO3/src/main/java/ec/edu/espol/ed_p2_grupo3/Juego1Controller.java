/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p2_grupo3;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author RUCO HOUSE
 */
public class Juego1Controller implements Initializable {
    private ArrayList<String> images;
    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView imagen;

    @FXML
    private Button si;
    @FXML
    private Button no;
    
    private int currentIndex = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        images = new ArrayList<>();
        String relativePath = "ec/edu/espol/ed_p2_grupo3/";
        images.add(relativePath+"akinator.png");
        images.add(relativePath+"aki2.jpg");
        images.add(relativePath+"aki3.png");
        imagen.setImage(new Image(images.get(currentIndex)));
    }    
     @FXML
    private void changeImage() {
        // Incrementa el índice y verifica si se debe reiniciar
        currentIndex = (currentIndex + 1) % images.size();

        // Cambia la imagen en el ImageView
        imagen.setImage(new Image(images.get(currentIndex)));
    }
    @FXML
    private void si(ActionEvent event) throws IOException {
        changeImage();
    }
    @FXML
    private void no(ActionEvent event) throws IOException {
        changeImage();
    }
}
