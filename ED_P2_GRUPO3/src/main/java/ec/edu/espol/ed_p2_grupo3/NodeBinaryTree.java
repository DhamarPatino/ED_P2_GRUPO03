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
    private String content;
    private BinaryTree<String> leftSi;
    private BinaryTree<String> rightNo;

    public NodeBinaryTree(String contenido) {
        this.content=contenido;
        this.leftSi = null;
        this.rightNo = null;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BinaryTree<String> getLeftSi() {
        return leftSi;
    }

    public void setLeftSi(BinaryTree<String> left) {
        this.leftSi = left;
    }

    public BinaryTree<String> getRightNo() {
        return rightNo;
    }

    public void setRightNo(BinaryTree<String> right) {
        this.rightNo = right;
    }
}
