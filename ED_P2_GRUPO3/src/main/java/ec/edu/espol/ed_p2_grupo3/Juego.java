/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ed_p2_grupo3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author DHAMAR
 */
public class Juego {
    
    public void Game(){
        
    }
    
    public static HashMap<String, ArrayList<String>> cargarRespuestas(String archivoRespuestas) {
        HashMap<String, ArrayList<String>> respuestas = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoRespuestas))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                String animal = partes[0];
                ArrayList<String> respuestasAnimal = new ArrayList<>();
                for (int i=1; i<partes.length; i++){
                    respuestasAnimal.add(partes[i]);
                    System.out.println(partes[i]);
                }   
                respuestas.put(animal, respuestasAnimal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respuestas;
    }
    
    public static ArrayList<String> cargarPreguntas (String nombreArchivo) throws IOException{
        ArrayList<String> preguntas = new ArrayList<>();
        try(BufferedReader br=new BufferedReader(new FileReader(nombreArchivo));){
            String line;
            while((line = br.readLine()) != null){
                preguntas.add(line);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return preguntas;
    }

   
}
