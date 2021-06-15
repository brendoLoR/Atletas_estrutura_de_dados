package com.mycompany.acesso_arquivos;

import classes.BalancedTree;
import classes.BinaryTreePrinter;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Acesso_arquivos {

    static BalancedTree tree = new BalancedTree();
    static PrintStream ps = new PrintStream(System.out);
    static BinaryTreePrinter tPrinter = new BinaryTreePrinter(tree.getRoot());

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        ps.println("escreva um tamanho para sua arvore: ");
        int nums = scan.nextInt();
        Random rand = new Random();
        if (tree.getRoot() != null) {
            tree.clear(rand.nextInt(9999));
            for (int i = 0; i < nums - 1; i++) {
                try {
                    String[] atleta = {"brendo".concat(String.valueOf(rand.nextInt(9000))), "23", "1", "não sabe jogar nada"};
                    tree.add(atleta);
                } catch (Exception e) {
                    i--;
                }
            }
        } else {
            for (int i = 0; i < nums; i++) {
                try {
                    String[] atleta = {"brendo Ja".concat(String.valueOf(rand.nextInt(9000))), "23", "1", "não sabe jogar nada"};
                    tree.add(atleta);
                } catch (Exception e) {
                    i--;
                }
            }
        }

        ps.println("Sua arvore foi criada com os seguintes valores: ");
        //imprime na ordem

        tPrinter.print(ps, tree.getRoot());

        atletaGenerate("atletas.dat");
        
        atletaRead("atletas.dat");
    }

    public static void atletaGenerate(String file) {

        BufferedWriter saida = null;

        try {
            saida = new BufferedWriter(new FileWriter(file));
            String[] atletas = tree.inOrder(tree.getRoot(), new ArrayList<>());
            for (String atleta : atletas) {
                
                String [] atleta_detail = atleta.replace("[", "")
                        .replace("]", "").split(",");

                saida.write(atleta_detail[0].concat(",")
                        .concat(atleta_detail[1].replace(" ", ""))
                        .concat(",")
                        .concat(atleta_detail[2].replace(" ", ""))
                        .concat(",")
                        .concat(atleta_detail[3]));
                saida.newLine();

            }
            saida.flush();
            saida.close();
            tree.clear(0);
        } catch (IOException ex) {
            Logger.getLogger(Acesso_arquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void atletaRead(String file) {
        BufferedReader saida = null;
        try {
            if (new File(file).exists()) {
                saida = new BufferedReader(new FileReader(file));
                String linha = saida.readLine();
                while (linha != null) {
//                    System.out.println(linha);
                    tree.add(linha.split(","));
                    linha = saida.readLine();
                }
                saida.close();
                tPrinter.print(ps, tree.getRoot());
            }

        } catch (Exception ex) {
            Logger.getLogger(Acesso_arquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void createFile() {
        Scanner scan = new Scanner(System.in);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("file.txt"));
            String line;
            while (!(line = scan.nextLine()).isEmpty()) {
                bw.write(line);
                bw.newLine();
            }
            bw.flush();
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(Acesso_arquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void readFile() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("clientes.dat"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Acesso_arquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static OutputStream FileOutputStream(String clientedat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
