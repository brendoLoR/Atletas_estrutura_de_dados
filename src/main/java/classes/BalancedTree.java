/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.*;

/**
 *
 * @author brendoja
 */
public class BalancedTree {

    private Node root;

    private int getKey(String name) {
        return name.hashCode();
    }

    public void addMultAtletas(Atleta[] atletas) {
        for (Atleta atleta : atletas) {
            int key_value = atleta.getHash();
            this.setRoot(add(this.getRoot(), key_value, atleta));
        }
    }

    public void add(String[] atleta_str) {
        int key_value = this.getKey(atleta_str[0]);
        this.setRoot(add(this.getRoot(), key_value, new Atleta(atleta_str)));
    }

    public void remove(int key) {
        this.setRoot(remove(this.getRoot(), key));
    }

    public Node search(int key) {
        return this.search(this.getRoot(), key);
    }

    public Atleta[] inOrder(Node node, List<Atleta> aux) {
        if (node != null) {
            this.inOrder(node.getLeft(), aux);
//            aux.add(Arrays.toString(node.getAtleta().getArgs()));
            aux.add(node.getAtleta());
            this.inOrder(node.getRight(), aux);
        }
        return (Atleta[]) aux.toArray(new Atleta[aux.size()]);
    }

    public Atleta[] beforeOrder(Node node, List<Atleta> aux) {
        if (node != null) {
            aux.add(node.getAtleta());
            this.beforeOrder(node.getLeft(), aux);
            this.beforeOrder(node.getRight(), aux);
        }
        return (Atleta[]) aux.toArray(new Atleta[aux.size()]);
    }

    public Atleta[] afterOrder(Node node, List<Atleta> aux) {
        if (node != null) {
            this.afterOrder(node.getLeft(), aux);
            this.afterOrder(node.getRight(), aux);
            aux.add(node.getAtleta());
        }
        return (Atleta[]) aux.toArray(new Atleta[aux.size()]);
    }

    private Node search(Node node, int key) {
        if (this.isEmpty(node) || node.getHash_id() == key) {
            return node;
        } else {
            return node.getHash_id() < key ? this.search(node.getRight(), key)
                    : this.search(node.getLeft(), key);
        }
    }

    private Node add(Node node, int hash_id, Atleta atleta) {
        if (node == null) {
            return new Node(hash_id, atleta);
        } else if (node.getHash_id() > hash_id) {
            node.setLeft(add(node.getLeft(), hash_id, atleta));

        } else if (node.getHash_id() < hash_id) {
            node.setRight(add(node.getRight(), hash_id, atleta));
        } else {
            throw new RuntimeException("Duplicate key value");
        }
        return this.rebalance(node);
    }

    private Node remove(Node node, int key) {
        Node aux;
        //search recursivaly for the right node.

        if (node.getHash_id() == key) {
            if (node.getLeft().getRight() == null) {
                aux = node.getLeft();
                node.setLeft(null);
            } else {
                aux = this.takeLastLeftChild(node.getLeft());
            }
            node.setHash_id(aux.getHash_id());
            if (aux.getRight() != null) {
                Node r = aux.getRight();
                aux.setHash_id(r.getHash_id());
                aux.setRight(null);
            }
        } else if (node.getHash_id() < key) {
            if (node.getRight().getHash_id() == key && node.getRight().getRight() == null
                    && node.getRight().getLeft() == null) {
                node.setRight(null);
            } else {
                this.remove(node.getRight(), key);
            }

        } else if (node.getHash_id() > key) {
            if (node.getLeft().getHash_id() == key && node.getLeft().getRight() == null
                    && node.getLeft().getLeft() == null) {
                node.setLeft(null);
            } else {
                this.remove(node.getLeft(), key);
            }
        } else {
            throw new RuntimeException("key not found");
        }

        return this.rebalance(node);
    }

    private Node takeLastLeftChild(Node node) {
        if (node.getRight().getRight() == null) {
            Node aux = node.getRight();
            if (node.getRight().getLeft() != null) {
                node.setRight(node.getRight().getLeft());
            } else {
                node.setRight(null);
            }
            return aux;
        } else {
            return this.takeLastLeftChild(node.getRight());
        }
    }

    public Node rebalance(Node node) {
        this.heightUpdate(node);
        int balancingFactor = this.blancing(node);
        if (balancingFactor > 1) {
            if (this.height(node.getRight().getRight()) > this.height(node.getRight()
                    .getLeft())) {
                node = this.leftRotate(node);
            } else {
                node.setRight(this.rightRotate(node.getRight()));
                node = this.leftRotate(node);
            }
        } else if (balancingFactor < -1) {
            if (this.height(node.getLeft().getLeft()) > this.height(node.getLeft()
                    .getRight())) {
                node = this.rightRotate(node);
            } else {
                node.setLeft(this.leftRotate(node.getLeft()));
                node = this.rightRotate(node);
            }
        }
        return node;
    }

    private Node leftRotate(Node node) {
        Node auxR = node.getRight();
        Node auxL = auxR.getLeft();
        auxR.setLeft(node);
        node.setRight(auxL);
        this.heightUpdate(node);
        this.heightUpdate(auxR);
        return auxR;
    }

    private Node rightRotate(Node node) {
        Node auxL = node.getLeft();
        Node auxR = auxL.getRight();
        auxL.setRight(node);
        node.setLeft(auxR);
        this.heightUpdate(node);
        this.heightUpdate(auxL);
        return auxL;
    }

    public int height(Node n) {
        return n == null ? -1 : n.getHeight();
    }

    private void heightUpdate(Node n) {
        n.setHeight(1 + Math.max(this.height(n.getLeft()), this.height(n.getRight())));
    }

    private int blancing(Node n) {
        return n == null ? 0 : this.height(n.getRight()) - this.height(n.getLeft());
    }

    private boolean isEmpty(Node node) {
        return node == null;
    }

    public Node getRoot() {
        return this.root;
    }

    private void setRoot(Node root) {
        this.root = root;
    }

    public void clear(int v) {
        this.root.setHeight(0);
        this.root.setLeft(null);
        this.root.setRight(null);
        this.root.setHash_id(v);
    }

}
