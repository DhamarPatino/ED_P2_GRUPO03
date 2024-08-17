/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p2_grupo3;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RUCO HOUSE
 */
public class EleccionController implements Initializable {

    @FXML
    private VBox vbox;

    private int selectedNumber;
    private int maxnumber = 2;

    @FXML
    private Button file1Button;
    
    @FXML
    private Button file2Button;

    @FXML
    private ComboBox<String> fileChoiceComboBox;

    @FXML
    private Button empezar;

    private File file1;
    private File file2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializa el Spinner con un valor predeterminado
        Spinner<Integer> spinner = new Spinner<>(1, maxnumber, 1);
        spinner.valueProperty().addListener((observable, oldValue, newValue) -> selectedNumber = newValue);
        selectedNumber = spinner.getValue();
        vbox.getChildren().add(spinner);

        // Inicializa el ComboBox con dos opciones
        fileChoiceComboBox.getItems().addAll("Subir archivos nuevos", "Usar archivo predeterminado");
        fileChoiceComboBox.getSelectionModel().selectFirst();
        fileChoiceComboBox.getSelectionModel().select("Usar archivo predeterminado");
        file1Button.setDisable(true);
        file2Button.setDisable(true);
        cargarArchivosPredeterminados();

        // Listener para ComboBox
        fileChoiceComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if ("Subir archivos nuevos".equals(newValue)) {
                file1Button.setDisable(false);
                file2Button.setDisable(false);
                file1 = null;
                file2 = null;
            } else {
                file1Button.setDisable(true);
                file2Button.setDisable(true);
                cargarArchivosPredeterminados();
            }
        });
    }

    @FXML
    private void uploadFile1(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Subir archivo de animales");
        file1 = fileChooser.showOpenDialog(new Stage());

        if (file1 != null) {
            if (validateFile1(file1)) {
                System.out.println("Archivo 1 es válido.");
            } else {
                System.out.println("Archivo 1 no es válido.");
                file1 = null;
            }
        }
    }

    @FXML
    private void uploadFile2(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Subir archivo de preguntas");
        file2 = fileChooser.showOpenDialog(new Stage());

        if (file2 != null) {
            if (validateFile2(file2)) {
                System.out.println("Archivo 2 es válido.");
                updateMaxNumber(file2);
            } else {
                System.out.println("Archivo 2 no es válido.");
                file2 = null;
            }
        }
    }

    private boolean validateFile1(File file) {
        if (file2 == null) {
            System.out.println("Debe subir primero el archivo de preguntas.");
            return false;
        }
        try {
            List<String> linesFile1 = Files.readAllLines(file.toPath());
            List<String> linesFile2 = Files.readAllLines(file2.toPath());

            if (linesFile2.isEmpty()) {
                System.out.println("El archivo de preguntas está vacío.");
                return false;
            }

            int numberOfQuestions = linesFile2.size();
            for (String line : linesFile1) {
                String[] parts = line.split(" ");
                if (parts.length <= 1 || (parts.length - 1) != numberOfQuestions) {
                    System.out.println("Formato incorrecto o número incorrecto de respuestas en la línea: " + line);
                    return false;
                }
                for (int i = 1; i < parts.length; i++) {
                    String response = parts[i];
                    if (!response.equals("si") && !response.equals("no")) {
                        System.out.println("Respuesta inválida en la línea: " + line);
                        return false;
                    }
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean validateFile2(File file) {
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            return lines.stream().allMatch(line -> line.endsWith("?"));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void updateMaxNumber(File file) {
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            maxnumber = lines.size();

            vbox.getChildren().clear();
            Spinner<Integer> spinner = new Spinner<>(1, maxnumber, 1);
            spinner.valueProperty().addListener((observable, oldValue, newValue) -> selectedNumber = newValue);
            vbox.getChildren().add(spinner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarArchivosPredeterminados() {
        file1 = new File("src/main/resources/animales.txt");
        file2 = new File("src/main/resources/preguntas.txt");

        updateMaxNumber(file2);
        System.out.println("Archivos predeterminados cargados.");
    }

    private boolean guardarArchivos() {
        if (file1 != null && file2 != null) {
            if (validateFile1(file1) && validateFile2(file2)) {
                try {
                    Path file1Dest = Path.of("src/main/resources/animales.txt");
                    Files.copy(file1.toPath(), file1Dest, StandardCopyOption.REPLACE_EXISTING);

                    Path file2Dest = Path.of("src/main/resources/preguntas.txt");
                    Files.copy(file2.toPath(), file2Dest, StandardCopyOption.REPLACE_EXISTING);

                    System.out.println("Archivos guardados con éxito.");
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Uno o ambos archivos no son válidos.");
            }
        } else {
            System.out.println("Debe subir ambos archivos.");
        }
        return false;
    }

    @FXML
    private void empezar(ActionEvent event) throws IOException {
        if (guardarArchivos()) {
            App.setRoot("Juego1");
        }
    }
}