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
            return this.root.getLeft() == null && this.root.getRight() == null;
        }
        return false;
    }

    // Construye el árbol binario a partir de las preguntas y respuestas dadas
    
      public void construirArbolDePreguntas(ArrayList<E> preguntas) {
        this.setRoot(construirNodo(preguntas, 0));
    }

    private NodeBinaryTree<E> construirNodo(ArrayList<E> preguntas, int index) {
        if (index >= preguntas.size()) {
            return null;
        }

        // Crear un nodo para la pregunta actual
        NodeBinaryTree<E> nodo;
        nodo = new NodeBinaryTree<>(preguntas.get(index));

        // Construir los hijos solo si hay más preguntas disponibles
        if (index + 1 < preguntas.size()) {
            nodo.setLeft(new BinaryTree<>(construirNodo(preguntas, index + 1)));
            nodo.setRight(new BinaryTree<>(construirNodo(preguntas, index + 1)));
        }

        return nodo;
    }
    // Recorrido preorden del árbol binario
    public void recorrerPreorden() {
        if (!this.isEmpty()) {
            // 1. Imprimir a la raiz
            System.out.println(this.root.getContent());

            // 2. Recorrer preorden en hijo izquierdo
            if (root.getLeft() != null) {
                root.getLeft().recorrerPreorden();
            }

            // 3. Recorrer preorden en hijo derecho
            if (root.getRight() != null) {
                root.getRight().recorrerPreorden();
            }
        }
    }

    // Recorrido postorden del árbol binario
    public void recorrerPostorden() {
        if (!this.isEmpty()) {
            // 1. Recorrer postorden en hijo izquierdo
            if (root.getLeft() != null) {
                root.getLeft().recorrerPostorden();
            }

            // 2. Recorrer postorden en hijo derecho
            if (root.getRight() != null) {
                root.getRight().recorrerPostorden();
            }

            // 3. Imprimir a la raiz
            System.out.println(this.root.getContent());
        }
    }

    // Recorrido enorden del árbol binario
    public void recorrerEnorden() {
        if (!this.isEmpty()) {
            // 1. Recorrer enorden en hijo izquierdo
            if (root.getLeft() != null) {
                root.getLeft().recorrerEnorden();
            }

            // 2. Imprimir a la raiz
            System.out.println(this.root.getContent());

            // 3. Recorrer enorden en hijo derecho
            if (root.getRight() != null) {
                root.getRight().recorrerEnorden();
            }
        }
    }
    public void insertIntoTree(HashMap<String, ArrayList<String>> data) {
        for (String key : data.keySet()) {
            ArrayList<String> path = data.get(key);
            NodeBinaryTree<E> current = root;
            for (String direction : path) {
                if (direction.equals("si")) {
                    if (current.getLeft() == null) {
                        current.setLeft( new BinaryTree<E>((NodeBinaryTree<E>) new NodeBinaryTree<String>(key))); // Crear nodo vacío si no existe
                    }
                    current = current.getLeft().getRoot();
                } else if (direction.equals("no")) {
                    if (current.getRight() == null) {
                        current.setRight( new BinaryTree<E>((NodeBinaryTree<E>) new NodeBinaryTree<String>(key))); // Crear nodo vacío si no existe
                    }
                    current = current.getRight().getRoot();
                }
            }
        }
    }
}