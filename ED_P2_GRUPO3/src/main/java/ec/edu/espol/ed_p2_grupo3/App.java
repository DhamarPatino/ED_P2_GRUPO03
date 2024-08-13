package ec.edu.espol.ed_p2_grupo3;

import static ec.edu.espol.ed_p2_grupo3.Juego.cargarPreguntas;
import static ec.edu.espol.ed_p2_grupo3.Juego.cargarRespuestas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws IOException {
        //launch();
       // Leer el archivo de preguntas
        ArrayList<String> preguntas = cargarPreguntas("ArchivoPreguntas.txt");
        HashMap<String, ArrayList<String>> respuestas = cargarRespuestas("ArchivoRespuestas.txt");

        BinaryTree<String> arbol = new BinaryTree<>();
        arbol.construirArbol(preguntas, respuestas);

        System.out.println("¡Bienvenido al juego de 20 preguntas!");
        System.out.println("Piensa en un animal...");
        arbol.jugar();
    }

}