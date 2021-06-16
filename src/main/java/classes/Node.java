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
    private int hash_id;
    private int Height;
    private Node left;
    private Node right;
    private Node father;
    
    private Atleta atleta;
    
    public Node(int hash_id, Node left, Node right) {
        this.hash_id = hash_id;
        this.left = left;
        this.right = right;
    }
    
    public Node(int hash_id, String[] args) {
        this.hash_id = hash_id;
        this.left = null;
        this.right = null;
        
        this.setAtleta(args);
    }

    public Node(int hash_id, Atleta atleta) {
        this.hash_id = hash_id;
        this.left = null;
        this.right = null;
        
        this.atleta = atleta;
    }
    
    public int getHash_id() {
        return hash_id;
    }
    
    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setHash_id(int hash_id) {
        this.hash_id = hash_id;
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
        this.atleta = new Atleta(args, this.getHash_id());
    }
}
