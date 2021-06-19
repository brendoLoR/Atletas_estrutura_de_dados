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

import java.io.*;
import java.util.Arrays;

/**
 *
 * @author https://www.baeldung.com/java-print-binary-tree-diagram
 */
public class BinaryTreePrinter {
    private Node tree;
    
    public BinaryTreePrinter(){
        this.tree = null;
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
