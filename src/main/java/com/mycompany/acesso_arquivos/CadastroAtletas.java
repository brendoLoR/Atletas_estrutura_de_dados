package com.mycompany.acesso_arquivos;

import classes.BalancedTree;
import classes.BinaryTreePrinter;
import classes.Atleta;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroAtletas {

    static BalancedTree tree = new BalancedTree();
    static PrintStream ps = new PrintStream(System.out);
    static BinaryTreePrinter tPrinter = new BinaryTreePrinter(tree.getRoot());

    @SuppressWarnings("empty-statement")
    public static void main(String args[]) {
//        Scanner scan = new Scanner(System.in);
//        ps.println("escreva um tamanho para sua arvore: ");
//        int nums = scan.nextInt();
        Random rand = new Random();
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
//
//        ps.println("Sua arvore foi criada com os seguintes valores: ");
//        //imprime na ordem
//
//        tPrinter.print(ps, tree.getRoot());
//
//        atletaGenerate("atletas.dat");
//
//        atletaRead("atletas.dat");
        Atleta[] atletas = new Atleta[10];
        for (int i = 0; i < 10; i++) {;;

            String[] atleta_detail = {"brendo".concat(String.valueOf(rand.nextInt(9000))), "23", "1", "não sabe jogar nada"};
            atletas[i] = new Atleta(atleta_detail);

        }

        for (int i = 0; i < atletas.length; i++) {
            System.out.println(atletas[i].getConcatenedArgs());

        }
        System.out.println("quick sort");
        Atleta[] atletasArr = quicksort(atletas, 0, atletas.length);
        for (int i = 0; i < atletas.length; i++) {
            System.out.println(atletasArr[i].getConcatenedArgs());

        }

    }

    /**
     *
     * @param file String contendo o caminho do arquivo base
     */
    public static void atletaGenerate(String file) {

        BufferedWriter saida = null;

        try {
            saida = new BufferedWriter(new FileWriter(file));
            Atleta[] atletas = tree.inOrder(tree.getRoot(), new ArrayList<>());
            for (Atleta atleta : atletas) {
                saida.write(atleta.getConcatenedArgs());
                saida.newLine();
            }
            saida.flush();
            saida.close();
        } catch (IOException ex) {
            Logger.getLogger(CadastroAtletas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param file String contendo o caminho do arquivo base
     */
    public static void atletaRead(String file) {
        tree.clear(0);
        BufferedReader saida = null;
        try {
            if (new File(file).exists()) {
                saida = new BufferedReader(new FileReader(file));
                String linha = saida.readLine();
                while (linha != null) {
//                    System.out.println(linha);
                    tree.add(linha.split(";"));
                    linha = saida.readLine();
                }
                saida.close();
                //tPrinter.print(ps, tree.getRoot());
            }

        } catch (Exception ex) {
            Logger.getLogger(CadastroAtletas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param atletas <= Atleta[]
     * @re
     * turn sorted Atleta[]
     */
    public static Atleta[] insertionSort(Atleta[] atletas) {

        for (int i = 1; i < atletas.length; i++) {

            Atleta aux = atletas[i];
            int j = i;

            while ((j > 0) && (atletas[j - 1].getHash() > aux.getHash())) {
                atletas[j] = atletas[j - 1];
                j -= 1;
            }
            atletas[j] = aux;

        }
        return atletas;
    }

    public static Atleta[] SelectionSort(Atleta[] atleta, int tam) {
        for (int indice = 0; indice < tam; ++indice) {
            int indiceMenor = indice;
            for (int indiceSeguinte = indice + 1; indiceSeguinte < tam; ++indiceSeguinte) {
                if (atleta[indiceSeguinte].getHash() < atleta[indiceMenor].getHash()) {
                    indiceMenor = indiceSeguinte;
                }
            }
            Atleta aux = atleta[indice];
            atleta[indice] = atleta[indiceMenor];
            atleta[indiceMenor] = aux;
        }
        return atleta;
    }

    /**
     *
     * @param atleta <Atleta[]>
     * @param began
     * @param end
     * @return
     */
    public static Atleta[] quicksort(Atleta[] atleta, int began, int end) {
        int i, j, pivo;
        Atleta aux;
        i = began;
        j = end - 1;
        pivo = atleta[Math.round((began + end) / 2)].getHash();
        while (i <= j) {
            while (atleta[i].getHash() < pivo && i < end) {
                i++;
            }
            while (atleta[j].getHash() > pivo && j > began) {
                j--;
            }
            if (i <= j) {
                aux = atleta[i];
                atleta[i] = atleta[j];
                atleta[j] = aux;
                i++;
                j--;
            }
        }
        if (j > began) {
            quicksort(atleta, began, j + 1);
        }
        if (i < end) {
            quicksort(atleta, i, end);
        }
        return atleta;
    }

}
