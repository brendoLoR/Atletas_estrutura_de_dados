/*
 * The MIT License
 *
 * Copyright 2021 brendoja.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package classes.estrutura_dados;

import classes.olimpiada.Atleta;

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
