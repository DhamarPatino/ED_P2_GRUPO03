/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ed_p2_grupo3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author DHAMAR
 */
public class BinaryTree<E> {

    private NodeBinaryTree<String> root;

    public BinaryTree(NodeBinaryTree<String> root) {
        this.root = root;
    }
    public BinaryTree() {
        this.root = null;
    }

    public NodeBinaryTree<String> getRoot() {
        return root;
    }
    public boolean isEmpty(){
        return this.root==null ;
    }
    
    public boolean isLeaf(){
        if(!this.isEmpty()){
            return this.root.getLeftSi()==null && this.root.getRightNo()==null;
        }
        return false;
    }

    public void construirArbol(ArrayList<String> preguntas, HashMap<String, ArrayList<String>> respuestas) {
        this.root = construirNodo(preguntas, respuestas, 0);
    }

    private NodeBinaryTree<String> construirNodo(ArrayList<String> preguntas, HashMap<String, ArrayList<String>> respuestas, int profundidad) {
        if (profundidad >= preguntas.size()) {
            return null; // No más preguntas para hacer, debería ser una hoja
        }

        // Crear un nuevo nodo para la pregunta en esta profundidad
        String pregunta = preguntas.get(profundidad);
        NodeBinaryTree<String> nodo = new NodeBinaryTree<>(pregunta);

        // Recursivamente construir los subárboles izquierdo (sí) y derecho (no)
        nodo.setLeftSi(new BinaryTree<>(construirNodo(preguntas, respuestas, profundidad + 1)));
        nodo.setRightNo(new BinaryTree<>(construirNodo(preguntas, respuestas, profundidad + 1)));

        return nodo;
    }
   

   public void recorrerPreorden(){
        
        // 1. Imprimir a la raiz
        // 2. recorrer preorden en hijo izquierdo
        // 3. recorrer preorden en hijo derecho
        
        if (!this.isEmpty()) {
            
            // 1. Imprimir a la raiz
            System.out.println(this.root.getContent());
            
            // 2. recorrer preorden en hijo izquierdo
            if (root.getLeftSi()!=null) {
                root.getLeftSi().recorrerPreorden(); 
            }
        
            // 3. recorrer preorden en hijo derecho
            if (root.getRightNo()!=null) {
                root.getRightNo().recorrerPreorden(); 
            }
        }
    }
    
    public void recorrerPostorden(){
        
        if (!this.isEmpty()){
            
            // 1. recorrer postorden en hijo izquierdo
            if (root.getLeftSi()!=null){
                root.getLeftSi().recorrerPostorden(); 
            }
        
            // 2. recorrer postorden en hijo derecho
            if (root.getRightNo()!=null){
                root.getRightNo().recorrerPostorden(); 
            }
            
            // 3. Imprimir a la raiz
            System.out.println(this.root.getContent());
        }
    }
    
    public void recorrerEnorden(){
        
        if (!this.isEmpty()){
            
            // 1. recorrer enorden en hijo izquierdo
            if (root.getLeftSi()!=null){
                root.getLeftSi().recorrerEnorden(); 
            }
        
            // 2. Imprimir a la raiz
            System.out.println(this.root.getContent());
            
            // 3. recorrer enorden en hijo derecho
            if (root.getRightNo()!=null){
                root.getRightNo().recorrerEnorden(); 
            }
            
        }
    }
    

}
