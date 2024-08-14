/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p2_grupo3;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author RUCO HOUSE
 */
public class Juego1Controller implements Initializable {

    @FXML
    private ImageView imagen;

    @FXML
    private Button si;
    
    @FXML
    private Button no;

    @FXML
    private Label pregunta; 

    private int currentIndex = 0;
    private BinaryTree<String> arbol;
    private NodeBinaryTree<String> currentNode;
    private ArrayList<String> images;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        images = new ArrayList<>();
        String relativePath = "ec/edu/espol/ed_p2_grupo3/";
        images.add(relativePath + "akinator.png");
        images.add(relativePath + "aki2.jpg");
        images.add(relativePath + "aki3.png");

        // Mensaje para verificar la ruta de la imagen
        System.out.println("Ruta de la imagen: " + images.get(currentIndex));

        try {
            // Verificar que el archivo de preguntas y respuestas existe y se carga correctamente
            ArrayList<String> preguntas = Juego.cargarPreguntas("src/main/resources/preguntas.txt");
            System.out.println("Preguntas cargadas: " + preguntas);
            HashMap<String, ArrayList<String>> respuestas = Juego.cargarRespuestas("src/main/resources/animales.txt");
            System.out.println("Respuestas cargadas: " + respuestas);

            arbol = new BinaryTree<>();
            arbol.construirArbol(preguntas, respuestas);
            currentNode = arbol.getRoot();

            // Mensaje para verificar el nodo raíz
            if (currentNode != null) {
                System.out.println("Pregunta raíz: " + currentNode.getContent());
                pregunta.setText(currentNode.getContent());
            } else {
                pregunta.setText("No hay preguntas disponibles.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            pregunta.setText("Error al cargar archivos.");
        }
    }  

    @FXML
    private void changeImage() {
        // Incrementa el índice y verifica si se debe reiniciar
        currentIndex = (currentIndex + 1) % images.size();

        // Cambia la imagen en el ImageView
        imagen.setImage(new Image(images.get(currentIndex)));
    }

    @FXML
    private void si(ActionEvent event) {
        if (currentNode != null) {
            if (currentNode.getLeftSi() != null) {
                currentNode = currentNode.getLeftSi().getRoot();
                updateQuestion();
            } else {
                pregunta.setText("¡Has terminado o no hay más preguntas!");
            }
        } else {
            pregunta.setText("¡No hay más preguntas!");
        }
        changeImage();
    }

    @FXML
    private void no(ActionEvent event) {
        if (currentNode != null) {
            if (currentNode.getRightNo() != null) {
                currentNode = currentNode.getRightNo().getRoot();
                updateQuestion();
            } else {
                pregunta.setText("¡Has terminado o no hay más preguntas!");
            }
        } else {
            pregunta.setText("¡No hay más preguntas!");
        }
        changeImage();
    }

    private void updateQuestion() {
        if (currentNode != null) {
            pregunta.setText(currentNode.getContent());
        } else {
            pregunta.setText("¡Has terminado!");
        }
    }
}