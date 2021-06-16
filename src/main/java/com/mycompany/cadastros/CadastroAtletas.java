package com.mycompany.cadastros;

import classes.BalancedTree;
import classes.BinaryTreePrinter;
import classes.Atleta;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CadastroAtletas {

    static BalancedTree tree = new BalancedTree();
    static PrintStream ps = new PrintStream(System.out);
    static BinaryTreePrinter tPrinter = new BinaryTreePrinter(tree.getRoot());

    @SuppressWarnings("empty-statement")
    public static void main(String args[]) {
        Random rand = new Random();
//        Scanner scan = new Scanner(System.in);
//        ps.println("escreva um tamanho para sua arvore: ");
//        int nums = scan.nextInt();
//        if (tree.getRoot() != null) {
//            tree.clear(rand.nextInt(9999));
//            for (int i = 0; i < nums - 1; i++) {
//                try {
//                    String[] atleta = {"brendo".concat(String.valueOf(rand.nextInt(9000))), "23", "1", "não sabe jogar nada"};
//                    tree.add(atleta);
//                } catch (Exception e) {
//                    i--;
//                }
//            }
//        } else {
//            for (int i = 0; i < nums; i++) {
//                try {
//                    String[] atleta = {"brendo Ja".concat(String.valueOf(rand.nextInt(9000))), "23", "1", "não sabe jogar nada"};
//                    tree.add(atleta);
//                } catch (Exception e) {
//                    i--;
//                }
//            }
//        }

        Atleta[] atletas = TraitFiles.atletaRead("atletas.dat");

        tree.addMultAtletas(atletas);
        ps.println("Sua arvore foi criada com os seguintes valores: ");

        //imprime no formato de arvore
        tPrinter.print(ps, tree.getRoot());
        
        atletas = tree.beforeOrder(tree.getRoot(), new ArrayList<>());
        
        //implementação dos metodos de ordenação
        for (Atleta atleta : atletas) {
            System.out.println(atleta.getConcatenedArgs());
        }
        System.out.println("quick sort");
        Atleta[] atletasArr = Ordenador.quicksort(atletas, 0, atletas.length);
        for (int i = 0; i < atletas.length; i++) {
            System.out.println(atletasArr[i].getConcatenedArgs());

        }

    }

}
