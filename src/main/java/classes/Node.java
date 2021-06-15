/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author brendoja
 */
public class Node {
    private int value;
    private int Height;
    private Node left;
    private Node right;
    private Node father;
    
private Atleta atleta;
    
    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
    
    public Node(int value, String[] args) {
        this.value = value;
        this.left = null;
        this.right = null;
        
        this.setAtleta(args);
    }

    public int getValue() {
        return value;
    }
    
    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getHeight() {
        return Height;
    }
       
    public void setHeight(int Height) {
        this.Height = Height;
    }

    public Node getFather() {
        return father;
    }

    public void setFather(Node father) {
        this.father = father;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    private void setAtleta(String[] args) {
        this.atleta = new Atleta(args, this.getValue());
    }
}
