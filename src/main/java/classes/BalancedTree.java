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
    public String[] last_node;
    
    private int getKey(String name){
        return name.hashCode();
    }
    
    public void add(String[] key){
        int key_value = this.getKey(key[0]);
        this.last_node = key;
        this.setRoot(add(this.getRoot(), key_value));
    }
    
    public void remove(int key){
        this.setRoot(remove(this.getRoot(), key));
    }
    
    public Node search(int key){
        return this.search(this.getRoot(), key);
    }
    
    public String[] inOrder(Node node, List<String> aux){
        if(node != null){
            this.inOrder(node.getLeft(), aux);
//            aux.add(Arrays.toString(node.getAtleta().getArgs()));
            aux.add(Arrays.toString(node.getAtleta().getArgs()));
            this.inOrder(node.getRight(), aux);
        }
        return (String[])aux.toArray(new String[aux.size()]);
    }
    
    public Integer[] beforeOrder(Node node, List<Integer> aux){
        if(node != null){
            aux.add(node.getValue());
            this.beforeOrder(node.getLeft(), aux);
            this.beforeOrder(node.getRight(), aux);
        }
        return (Integer[])aux.toArray(new Integer[aux.size()]);
    }
    
    public Integer[] afterOrder(Node node, List<Integer> aux){
        if(node != null){
            this.afterOrder(node.getLeft(), aux);
            this.afterOrder(node.getRight(), aux);
            aux.add(node.getValue());
        }
        return (Integer[])aux.toArray(new Integer[aux.size()]);
    }
        
    private Node search(Node node, int key){
        if(this.isEmpty(node) || node.getValue() == key){
            return node;
        }else{
            return node.getValue() < key ? this.search(node.getRight(), key) :
                    this.search(node.getLeft(), key);
        }
    } 
    
    private Node add(Node node, int key){
        if (node == null){
            return new Node(key, this.last_node);
        }else if (node.getValue() > key){
            node.setLeft(add(node.getLeft(), key));
            
        }else if (node.getValue() < key){
            node.setRight(add(node.getRight(), key));
        }else{
            throw new RuntimeException("Duplicate key value");
        }
        return this.rebalance(node);
    }
  
    private Node remove(Node node, int key){
        Node aux;
        //search recursivaly for the right node.
        
        if(node.getValue() == key){
            if(node.getLeft().getRight() == null){
                aux = node.getLeft();
                node.setLeft(null);
            }else {
                aux = this.takeLastLeftChild(node.getLeft());
            }
            node.setValue(aux.getValue());
            if(aux.getRight() != null){
                Node r = aux.getRight();
                aux.setValue(r.getValue());
                aux.setRight(null);                
            }
        }else if(node.getValue() < key){
            if(node.getRight().getValue() == key && node.getRight().getRight() == null
                    && node.getRight().getLeft() == null ){
                node.setRight(null);
            }else{
                this.remove(node.getRight(), key);
            }
            
        }else if(node.getValue() > key){
            if(node.getLeft().getValue() == key && node.getLeft().getRight() == null
                    && node.getLeft().getLeft() == null){
                node.setLeft(null);
            }else{
                this.remove(node.getLeft(), key);
            }
        }else{
            throw new RuntimeException("key not found");
        }
        
        return this.rebalance(node);
    }
    
    private Node takeLastLeftChild(Node node) {
        if(node.getRight().getRight() == null){
            Node aux = node.getRight();
            if(node.getRight().getLeft() != null){
                node.setRight(node.getRight().getLeft());
            }else node.setRight(null);
            return aux;
        }else{
            return this.takeLastLeftChild(node.getRight());
        }
    }
    
    public Node rebalance(Node node){
        this.heightUpdate(node);
        int balancingFactor = this.blancing(node);
        if (balancingFactor > 1){
            if(this.height(node.getRight().getRight()) > this.height(node.getRight()
                    .getLeft())){
                node = this.leftRotate(node);
            }else{
                node.setRight(this.rightRotate(node.getRight()));
                node = this.leftRotate(node);
            }
        }else if (balancingFactor < -1){
            if (this.height(node.getLeft().getLeft()) > this.height(node.getLeft()
                    .getRight())){
                node = this.rightRotate(node);
            }else{
                node.setLeft(this.leftRotate(node.getLeft()));
                node = this.rightRotate(node);
            }
        }
        return node;
    }
    
    private Node leftRotate(Node node){
        Node auxR = node.getRight();
        Node auxL = auxR.getLeft();
        auxR.setLeft(node);
        node.setRight(auxL);
        this.heightUpdate(node);
        this.heightUpdate(auxR);
        return auxR;
    }
        
    private Node rightRotate(Node node){
        Node auxL = node.getLeft();
        Node auxR = auxL.getRight();
        auxL.setRight(node);
        node.setLeft(auxR);
        this.heightUpdate(node);
        this.heightUpdate(auxL);
        return auxL;
    }
     
    public int height(Node n){
        return n == null ? -1 : n.getHeight();
    }
    
    private void heightUpdate(Node n){
        n.setHeight(1 + Math.max(this.height(n.getLeft()), this.height(n.getRight())));
    }
    
    private int blancing(Node n){
        return n == null ? 0 : this.height(n.getRight()) - this.height(n.getLeft());
    }
    
    private boolean isEmpty(Node node){
        return node == null;
    }
    
    public Node getRoot() {
        return this.root;
    }
       
    private void setRoot(Node root) {
        this.root = root;
    }
    
    public void clear(int v){
        this.root.setHeight(0);
        this.root.setLeft(null);
        this.root.setRight(null);
        this.root.setValue(v);
    }
    
}