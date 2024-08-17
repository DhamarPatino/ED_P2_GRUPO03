/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ed_p2_grupo3;

/**
 *
 * @author DHAMAR
 */
public class NodeBinaryTree<E> {
    private E content;
    private BinaryTree<E> left;
    private BinaryTree<E> right;

    public NodeBinaryTree(E contenido) {
        this.content=contenido;
        this.left = null;
        this.right = null;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public BinaryTree<E> getLeft() {
        return left;
    }

    public void setLeft(BinaryTree<E> left) {
        this.left = left;
    }

    public BinaryTree<E> getRight() {
        return right;
    }

    public void setRight(BinaryTree<E> right) {
        this.right = right;
    }
}
