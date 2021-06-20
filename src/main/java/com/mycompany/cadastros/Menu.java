package com.mycompany.cadastros;

import java.io.PrintStream;
import java.util.Scanner;
import classes.estrutura_dados.BinaryTreePrinter;
import classes.estrutura_dados.Ordenador;
import classes.olimpiada.Atleta;
import classes.persistencia_dados.Persistencia;
import java.util.Random;

public class Menu {

    static PrintStream ps = new PrintStream(System.out);
    static BinaryTreePrinter tPrinter = new BinaryTreePrinter();

    static String database = "atletas.dat";
    static Persistencia db = new Persistencia(database);

    //utilidades
    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();
    static Boolean array_tree = false;

    public static void main(String[] args) {
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
                    + "0 - Arvore Binaria;\n"
                    + "1 - Array de atletas (permite ordenação)");
            array_tree = scan.nextLine().equals("1");
        } else {
            System.exit(0);
        }
        if (db.open(array_tree)) {
            ps.println("operacao realizada com sucesso");
        } else {
            ps.println("houve um erro");
        }

        mainMenu();
    }

    private static void mainMenu() {
        Boolean saida = false;
        Scanner sc = new Scanner(System.in);

        do {
            clear();
            ps.println("-------------------------------------------------------\n"
                    + " - Escolha uma opção abaixo: \n"
                    + "0. Criar uma sequencia aleatoria de atletas \n"
                    + "1. Inserir manualmente os atletas \n"
                    + "2. Apagar um aleta da lista \n"
                    + "3. Buscar um atleta \n"
                    + "4. Imprimir todos os atletas cadastrados"
            );

            ps.println(menuDetail(array_tree)
                    + "9. Alternar modo(Array <-> Arvore) / SAIR\n" + "--------------------------------------------------------");
            try {
                saida = options(scan.nextInt());
            } catch (Exception e) {
                opcao_errada();
            }
        } while (saida);

    }

    static Boolean options(int opt) {
        switch (opt) {
            case 0 -> {
                //0. Criar uma nova arvore e, se existir, apagar a atual

                ps.println("insira a quantidade de atletas aleatorios: ");
                int nums = scan.nextInt();
                Atleta[] atletas = new Atleta[nums];
                for (int i = 0; i < nums; i++) {
                    try {
                        String[] atleta = new String[4];
                        atleta[0] = "atleta" + rand.nextInt(99999);
                        atleta[1] = String.valueOf(rand.nextInt(99999));
                        atleta[2] = String.valueOf(rand.nextInt(99));
                        atleta[3] = "JOGA MUITO MAL";

                        atletas[i] = new Atleta(atleta);
                    } catch (Exception e) {
                        i--;
                    }
                }
                if (db.add(atletas)) {
                    ps.println("operacao realizada com sucesso");
                } else {
                    ps.println("houve um erro");
                }

                return continuar();
            }
            case 1 -> {

                ps.println("insira a quantidade de atletas para cadastro: ");
                int nums = scan.nextInt();

                Atleta[] atletas = new Atleta[nums];
                scan.nextLine();
                for (int i = 0; i < nums; i++) {
                    try {

                        String[] atleta = new String[4];
                        ps.println("ATLETA Nº " + (i + 1) + "\n");
                        ps.println("NOME: ");
                        atleta[0] = scan.nextLine();
                        ps.println("IDADE: ");
                        atleta[1] = String.valueOf(Integer.parseInt(scan.nextLine()));
                        ps.println("QUANTIDADE DE PONTOS: ");
                        atleta[2] = String.valueOf(Integer.parseInt(scan.nextLine()));
                        ps.println("HABILIDADES: ");
                        atleta[3] = scan.nextLine();

                        atletas[i] = new Atleta(atleta);
                    } catch (Exception e) {
                        i--;
                    }
                }
                if (db.add(atletas)) {
                    ps.println("operacao realizada com sucesso");
                } else {
                    ps.println("houve um erro");
                }
            }

            case 2 -> {
                ps.println("-------------------------------------------------------\n"
                        + "ATENÇÃO - Serão listados todos os atletas cadastrados.\n"
                        + "Você deverá inserir o nome exato do que deseja remover.\n"
                        + "-------------------------------------------------------");
                continuar();
                list();
                ps.println("-------------------------------------------------------\n"
                        + "ESCREVA O NOME EXATO DE QUEM DESEJA REMOVER DO CADASTRO");
                if (db.remove(scan.nextLine())) {
                    ps.println("atleta removido com sucesso");
                } else {
                    ps.println("houve um erro");
                }
                break;
            }

            case 3 -> {
                ps.println("-------------------------------------------------------\n"
                        + "ATENÇÃO - Serão listados todos os atletas cadastrados.\n"
                        + "Você deverá inserir o nome exato do que deseja buscar.\n"
                        + "-------------------------------------------------------");
                continuar();
                list();
                ps.println("-------------------------------------------------------\n"
                        + "ESCREVA O NOME EXATO DE QUEM DESEJA");
                try {
                    ps.println(db.search_by_name(scan.nextLine()).getConcatenedArgs());
                }catch(Exception e){
                    ps.println(e.getMessage());
                }
                break;
            }

            case 4 -> {
                list();
                break;
            }

            case 5 -> {
                if (array_tree) {
                    ps.println("QuickSort");
                    for (Atleta qAtletas : Ordenador.quicksort(db.atletas, 0, db.atletas.length)) {
                        ps.println(qAtletas.getConcatenedArgs());
                    }
                } else {
                    ps.println("Altere para o modo Array antes");
                }
                break;
            }
            case 6 -> {
                if (array_tree) {
                    ps.println("SelectionSort");
                    for (Atleta sAtletas : Ordenador.SelectionSort(db.atletas, db.atletas.length)) {
                        ps.println(sAtletas.getConcatenedArgs());
                    }
                } else {
                    ps.println("Altere para o modo Array antes");
                }

                break;
            }
            case 7 -> {
                if (array_tree) {
                    ps.println("InsertionSort");
                    Atleta[] d = Ordenador.insertionSort(db.atletas);
                    for (Atleta iAtletas : Ordenador.insertionSort(db.atletas)) {
                        ps.println(iAtletas.getConcatenedArgs());
                    }
                } else {
                    ps.println("Altere para o modo Array antes");
                }

                break;
            }
            case 8 -> {
                if (array_tree) {
                    ps.println("BuubleSort");
                    for (Atleta iAtletas : Ordenador.buubleSort(db.atletas)) {
                        ps.println(iAtletas.getConcatenedArgs());
                    }
                } else {
                    ps.println("Altere para o modo Array antes");
                }

                break;
            }

            case 9 -> {
                ps.println("0 - Alternar entre Array e Arvore;\n"
                        + "9 - SAIR");
                int op = scan.nextInt();
                if (op == 0) {
                    array_tree = !array_tree;
                    db.open(array_tree);
                    ps.println("Modo Alterado");
                } else if (op == 9) {
                    return continuar();
                } else {
                    opcao_errada();
                }

            }
            default -> {
                opcao_errada();
            }
        }
        return continuar();
    }

    static void list() {
        if (array_tree) {
            ps.println("Lista de Atletas");
            for (Atleta a : db.atletas) {
                ps.println(a.getConcatenedArgs());
            }
        } else {
            tPrinter.print(ps, db.getTree().getRoot());
        }
    }

    static String menuDetail(Boolean array_tree) {
        if (array_tree) {
            return "5 - QuickSort\n"
                    + "6 - SelectionSort\n"
                    + "7 - InsertionSort\n"
                    + "8 - BuubleSort\n";
        } else {
            return "";
        }
    }

    static void opcao_errada() {
        ps.println("\nOPÇÃO INVALIDA");
        mainMenu();
    }

    static Boolean continuar() {

        ps.println("\nPressione 'enter'");
        scan.nextLine();
        ps.println("\ndeseja continuar? y/n");
        return scan.nextLine().equals("y");
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
