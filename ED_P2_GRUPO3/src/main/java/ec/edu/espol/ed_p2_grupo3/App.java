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
     private int nPreguntas;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("inicio"), 524, 540);
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
        
        ArrayList<String> preguntas = cargarPreguntas("ArchivoPreguntas.txt");
        HashMap<String, ArrayList<String>> respuestas = cargarRespuestas("ArchivoRespuestas.txt");
        for (String p: preguntas ){
            System.out.println(p);
        }
        for (HashMap.Entry<String, ArrayList<String>> entry : respuestas.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
            for(String s : value){
                System.out.println(s);
            }
        }
        

    }

    public int getnPreguntas() {
        return nPreguntas;
    }

    public void setnPreguntas(int nPreguntas) {
        this.nPreguntas = nPreguntas;
    }
    

}