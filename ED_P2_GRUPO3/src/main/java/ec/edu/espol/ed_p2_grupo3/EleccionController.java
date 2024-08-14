/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p2_grupo3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author RUCO HOUSE
 */
public class EleccionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox vbox;

    private int selectedNumber; // Campo para guardar el número seleccionado
    int maxnumber = 10;
    
    private Button inicio;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Define el rango del Spinner (de 1 a 10 en este ejemplo)
        Spinner<Integer> spinner = new Spinner<>(1, maxnumber, 1);

        // Añade un listener para detectar cambios en el valor seleccionado
        spinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                selectedNumber = newValue; // Actualiza el campo con el nuevo valor
            }
        });

        // Inicializa el campo con el valor por defecto del Spinner
        selectedNumber = spinner.getValue();

        // Añade el Spinner al VBox
        vbox.getChildren().add(spinner);
    }

    // Método para obtener el valor seleccionado si se necesita en otro lugar
    public int getSelectedNumber() {
        return selectedNumber;
    }   
    @FXML
    private void empezar(ActionEvent event) throws IOException {
        App.setRoot("Juego1");
    }
    
}
