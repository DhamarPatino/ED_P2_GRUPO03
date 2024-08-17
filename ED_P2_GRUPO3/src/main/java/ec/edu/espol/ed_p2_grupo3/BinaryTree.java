/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ed_p2_grupo3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author DHAMAR
 */
public class BinaryTree<E> {

    private NodeBinaryTree<E> root;

    // Constructor que inicializa el árbol con una raíz
    public BinaryTree(NodeBinaryTree<E> root) {
        this.root = root;
    }

    // Constructor que inicializa un árbol vacío
    public BinaryTree() {
        this.root = null;
    }

    // Devuelve la raíz del árbol
    public NodeBinaryTree<E> getRoot() {
        return root;
    }

    // Verifica si el árbol está vacío
    public boolean isEmpty() {
        return this.root == null;
    }

    // Establece la raíz del árbol
    public void setRoot(NodeBinaryTree<E> root) {
        this.root = root;
    }

    // Verifica si el nodo es una hoja (no tiene hijos)
    public boolean isLeaf() {
        if (!this.isEmpty()) {
            return this.root.getLeftSi() == null && this.root.getRightNo() == null;
        }
        return false;
    }

    // Construye el árbol binario a partir de las preguntas y respuestas dadas
    public void construirArbol(ArrayList<String> preguntas, HashMap<String, ArrayList<String>> respuestas) {
        this.root = (NodeBinaryTree<E>) construirNodo(preguntas, respuestas, 0);
    }

    // Método recursivo para construir el árbol binario
    private NodeBinaryTree<String> construirNodo(ArrayList<String> preguntas, HashMap<String, ArrayList<String>> respuestas, int profundidad) {
        if (preguntas == null || profundidad >= preguntas.size() || respuestas.isEmpty()) {
            // Si hemos llegado al final de las preguntas o la lista está vacía, devolver null
            return null;
        }

        // Crear un nuevo nodo para la pregunta en esta profundidad
        String pregunta = preguntas.get(profundidad);
        NodeBinaryTree<String> nodo = new NodeBinaryTree<>(pregunta);

        // Crear listas filtradas para las respuestas de sí y no
        HashMap<String, ArrayList<String>> respuestasSi = new HashMap<>();
        HashMap<String, ArrayList<String>> respuestasNo = new HashMap<>();

        for (Map.Entry<String, ArrayList<String>> entry : respuestas.entrySet()) {
            String animal = entry.getKey();
            ArrayList<String> respuestaList = entry.getValue();
            if (respuestaList.size() > profundidad) {
                if (respuestaList.get(profundidad).equals("sí")) {
                    respuestasSi.put(animal, respuestaList);
                } else if (respuestaList.get(profundidad).equals("no")) {
                    respuestasNo.put(animal, respuestaList);
                }
            }
        }

        // Si no hay más preguntas y solo queda un animal, el nodo se convierte en una hoja con el nombre del animal
        if (respuestasSi.size() == 1 && respuestasNo.isEmpty()) {
            nodo.setContent(respuestasSi.keySet().iterator().next());
            return nodo;
        } else if (respuestasNo.size() == 1 && respuestasSi.isEmpty()) {
            nodo.setContent(respuestasNo.keySet().iterator().next());
            return nodo;
        }

        // Recursivamente construir los subárboles izquierdo (sí) y derecho (no)
        nodo.setLeftSi(new BinaryTree<>(construirNodo(preguntas, respuestasSi, profundidad + 1)));
        nodo.setRightNo(new BinaryTree<>(construirNodo(preguntas, respuestasNo, profundidad + 1)));

        return nodo;
    }

    // Recorrido preorden del árbol binario
    public void recorrerPreorden() {
        if (!this.isEmpty()) {
            // 1. Imprimir a la raiz
            System.out.println(this.root.getContent());

            // 2. Recorrer preorden en hijo izquierdo
            if (root.getLeftSi() != null) {
                root.getLeftSi().recorrerPreorden();
            }

            // 3. Recorrer preorden en hijo derecho
            if (root.getRightNo() != null) {
                root.getRightNo().recorrerPreorden();
            }
        }
    }

    // Recorrido postorden del árbol binario
    public void recorrerPostorden() {
        if (!this.isEmpty()) {
            // 1. Recorrer postorden en hijo izquierdo
            if (root.getLeftSi() != null) {
                root.getLeftSi().recorrerPostorden();
            }

            // 2. Recorrer postorden en hijo derecho
            if (root.getRightNo() != null) {
                root.getRightNo().recorrerPostorden();
            }

            // 3. Imprimir a la raiz
            System.out.println(this.root.getContent());
        }
    }

    // Recorrido enorden del árbol binario
    public void recorrerEnorden() {
        if (!this.isEmpty()) {
            // 1. Recorrer enorden en hijo izquierdo
            if (root.getLeftSi() != null) {
                root.getLeftSi().recorrerEnorden();
            }

            // 2. Imprimir a la raiz
            System.out.println(this.root.getContent());

            // 3. Recorrer enorden en hijo derecho
            if (root.getRightNo() != null) {
                root.getRightNo().recorrerEnorden();
            }
        }
    }
}