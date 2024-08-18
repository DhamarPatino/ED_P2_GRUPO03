/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p2_grupo3;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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
    public int preguntas = App.getPreguntas();
    private int preguntaCounter = 0;  // Contador de preguntas
    @FXML
    private Text volver;
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
            System.out.println("arbol: ");
            arbol = new BinaryTree<>();
            arbol.construirArbolDePreguntas(preguntas);
            currentNode = arbol.getRoot();
            arbol.insertIntoTree(respuestas);
            arbol.recorrerPreorden();
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
        volver.setOnMouseClicked(event -> {
            try {
                volverLink(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    @FXML
    void volverLink(MouseEvent event) throws IOException {
        App.setRoot("Eleccion");
    }

    private void changeImage() {
        // Incrementa el índice y verifica si se debe reiniciar
        currentIndex = (currentIndex + 1) % images.size();

        // Cambia la imagen en el ImageView
        imagen.setImage(new Image(images.get(currentIndex)));
    }

    @FXML
    private void si(ActionEvent event) {
        if (currentNode != null && preguntaCounter < preguntas) {
            preguntaCounter++;
            if (currentNode.getLeft() != null) {
                currentNode = currentNode.getLeft().getRoot();
                updateQuestion();
            } else {
                pregunta.setText("¡Has terminado o no hay más preguntas!");
            }
            if (preguntaCounter >= preguntas) {
                showPossibleAnimals();
            }
        }
        System.out.println("preguntas: "+preguntas);
        System.out.println(preguntaCounter);
        changeImage();
    }

    @FXML
    private void no(ActionEvent event) {
        if (currentNode != null && preguntaCounter < preguntas) {
            preguntaCounter++;
            if (currentNode.getRight() != null) {
                currentNode = currentNode.getRight().getRoot();
                updateQuestion();
            } else {
                pregunta.setText("¡Has terminado o no hay más preguntas!");
            }
            if (preguntaCounter >= preguntas) {
                showPossibleAnimals();
            }
        }
        System.out.println(preguntaCounter);
        changeImage();
    }

    private void updateQuestion() {
        if (currentNode != null) {
            pregunta.setText(currentNode.getContent());
        } else {
            pregunta.setText("¡Has terminado!");
        }
    }

    private void showPossibleAnimals() {
        System.out.println("mostrar respuestas");
        List<String> possibleAnimals = collectAnimals(currentNode);
        pregunta.setText("Animales posibles: " + String.join(", ", possibleAnimals));
        si.setDisable(true);
        no.setDisable(true);
    }

    private List<String> collectAnimals(NodeBinaryTree<String> node) {
        List<String> animals = new ArrayList<>();
        if (node == null) {
            return animals;
        }

        // Si es una hoja, añadir el contenido
        if (node.getLeft() == null && node.getRight() == null) {
            animals.add(node.getContent());
        } else {
            // Recorrer ambos subárboles si existen
            if (node.getLeft() != null) {
                animals.addAll(collectAnimals(node.getLeft().getRoot()));
            }
            if (node.getRight() != null) {
                animals.addAll(collectAnimals(node.getRight().getRoot()));
            }
        }
        return animals;
    }
}