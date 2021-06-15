/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.*;
import java.util.Arrays;

/**
 *
 * @author https://www.baeldung.com/java-print-binary-tree-diagram
 */
public class BinaryTreePrinter {
    private Node tree;
    
    public BinaryTreePrinter(Node tree){
        this.tree = tree;
    }
    public void traversePreOrder(StringBuilder sb, Node node) {
        if (node != null) {
            sb.append(Arrays.toString(node.getAtleta().getArgs()));
            sb.append("\n");
            traversePreOrder(sb, node.getLeft());
            traversePreOrder(sb, node.getRight());
        }
    }
    public void print(PrintStream os, Node tree) {
        this.setTree(tree);
        os.print(traversePreOrder(this.getTree()));
    }
    public String traversePreOrder(Node root) {

    if (root == null) {
        return "";
    }

    StringBuilder sb = new StringBuilder();
    sb.append(Arrays.toString(root.getAtleta().getArgs()));

    String pointerRight = "'---r-";
    String pointerLeft = (root.getRight() != null) ? "|---l-" : "'---l-";

    traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
    traverseNodes(sb, "", pointerRight, root.getRight(), false);

    return sb.toString();
}
    public void traverseNodes(StringBuilder sb, String padding, String pointer, Node node, 
        boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(Arrays.toString(node.getAtleta().getArgs()));

            StringBuilder paddingBuilder = new StringBuilder(padding);
        if (hasRightSibling) {
            paddingBuilder.append("|   ");
        } else {
            paddingBuilder.append("   ");
        }

        String paddingForBoth = paddingBuilder.toString();
        String pointerRight = "'---r-";
        String pointerLeft = (node.getRight() != null) ? "|---l-" : "'---l-";

        traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.
                getRight() != null);
        traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
        
        }
    }

    public Node getTree() {
        return tree;
    }

    public void setTree(Node tree) {
        this.tree = tree;
    }
}
