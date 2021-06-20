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
package com.mycompany.cadastros;

import classes.estrutura_dados.Ordenador;

import classes.estrutura_dados.BinaryTreePrinter;
import classes.olimpiada.Atleta;
import classes.persistencia_dados.Persistencia;
import java.io.*;

import java.util.Random;
import java.util.Scanner;

public class CadastroAtletas {

    //inicializa arvore e printer
    static PrintStream ps = new PrintStream(System.out);
    static BinaryTreePrinter tPrinter = new BinaryTreePrinter();
    //inicializa convesação com base de dados
    static String database = "atletas.dat";
    static Persistencia db = new Persistencia(database);
    //utilidades
    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();
    static Boolean array_tree = false;

    @SuppressWarnings("empty-statement")
    public static void main(String args[]) {
        Boolean saida = false;
        clear();
        ps.println("-------------------------------------------------------\n\n"
                + "ATENÇÃO - Esse programa pode ser usado de duas maneiras.\n"
                + "1º -> Armazenando os dados em uma arvore balanceada;\n"
                + "2º -> Armazenando os dados em um array\n"
                + "\n\n"
                + "OBS: Os metodos de ordenação só estarão disponiveis no modo Array\n\n"
                + "-------------------------------------------------------");

        if (continuar()) {
            ps.println("Escolha o tipo de lista: \n"
                    + "0 - Arvore Binaria;"
                    + "1 - Array de atletas (permite ordenação)");
            array_tree = scan.nextLine().equals("1");
        } else {
            System.exit(0);
        }
        db.open_to_AtletaArray();
        do {
            clear();
            ps.println("-------------------------------------------------------\n"
                    + " - Escolha uma opção abaixo: \n"
                    + "0. Criar uma sequencia aleatoria de atletas \n"
                    + "1. Inserir manualmente os atletas \n"
                    + "2. Apagar um aleta da lista \n"
                    + "3. Buscar um atleta \n"
                    + "4. Imprimir todos os atletas cadastrados \n"
            );

            ps.println(menuDetail(array_tree)
                    + "9. SAIR \n" + "--------------------------------------------------------");

            saida = options(scan.nextInt());
        } while (saida);

    }

    static Boolean options(int opt) {
        switch (opt) {
            case 0 -> {
                //0. Criar uma nova arvore e, se existir, apagar a atual

                ps.println("insira a quantidade de atletas aleatorios: ");
                int nums = scan.nextInt();
                Atleta[] atletas = new Atleta[nums];
                for (int i = 0; i < nums - 1; i++) {
                    try {
                        String[] atleta = new String[4];
                        atleta[0] = "brendo" + rand.nextInt(99999);
                        atleta[1] = String.valueOf(rand.nextInt(99999));
                        atleta[2] = "esporte";
                        atleta[3] = "sei jogar de tudo";

                        atletas[i] = new Atleta(atleta);
                    } catch (Exception e) {
                        i--;
                    }
                }
                db.add(atletas);
                return continuar();
            }
            case 1 -> {
                ps.println("insira a quantidade de atletas para cadastro: ");
                int nums = Integer.parseInt(scan.nextLine());

                Atleta[] atletas = new Atleta[nums];
                for (int i = 0; i < nums; i++) {
                    try {

                        String[] atleta = new String[4];
                        ps.println("ATLETA Nº " + (i + 1) + "\n");
                        ps.println("NOME: ");
                        atleta[0] = scan.nextLine();
                        ps.println("IDADE: ");
                        atleta[1] = String.valueOf(Integer.parseInt(scan.nextLine()));
                        ps.println("ESPORTE: ");
                        atleta[2] = scan.nextLine();
                        ps.println("HABILIDADES: ");
                        atleta[3] = scan.nextLine();

                        atletas[i] = new Atleta(atleta);
                    } catch (Exception e) {
                        i--;
                    }
                }
                db.add(atletas);

                return continuar();
            }

            case 9 -> {

            }
        }
        return continuar();
    }

    static String menuDetail(Boolean array_tree) {
        if (array_tree) {
            return "5 - QuickSort\n"
                    + "6 - InsertionSort"
                    + "7 - SelectionSort"
                    + "8 - BuubleSort";
        } else {
            return "";
        }
    }

    static Boolean continuar() {
        ps.println("\ndeseja continuar? y/n");
        return scan.next().equals("y");
    }

    static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }

        } catch (Exception e) {

        }

    }
}
