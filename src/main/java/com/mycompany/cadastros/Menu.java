package com.mycompany.cadastros;

import java.io.PrintStream;
import java.util.Scanner;

import classes.estrutura_dados.BinaryTreePrinter;
import classes.estrutura_dados.Ordenador;
import classes.olimpiada.Atleta;
import classes.persistencia_dados.Persistencia;

public class Menu {

	static PrintStream ps = new PrintStream(System.out);
	static BinaryTreePrinter tPrinter = new BinaryTreePrinter();

	static String database = "atletas.dat";
	static Persistencia db = new Persistencia(database);

	public static void main(String[] args) {

		Menu();
	}

	private static void Menu() {

		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("\nMenu Principal\n");
			System.out.println(" 1) Cadastrar atletas");
			System.out.println(" 2) Mostrar Lista ");
			System.out.println(" 3) Mostrar Lista Ordenada (QuickSort) ");
			System.out.println(" 4) Mostrar Lista Ordenada (SelectionSort) ");
			System.out.println(" 5) Mostrar Lista Ordenada (InsertionSort) ");
			int menu = sc.nextInt();
			
			switch (menu) {
			case 1:
				CadastroAtletas.main(null);
				break;
			case 2:
				ps.println("Lista de Atletas");
				db.open_to_AtletaArray();
				for (Atleta a : db.atletas) {
					ps.println(a.getConcatenedArgs());
				}
				break;
			case 3:
				ps.println("QuickSort");
				db.open_to_AtletaArray();
				Atleta[] b = Ordenador.quicksort(db.atletas, 0, db.atletas.length);
				for (Atleta qAtletas : db.atletas) {
					ps.println(qAtletas.getConcatenedArgs());
				}
				break;
			case 4:
				ps.println("SelectionSort");
				db.open_to_AtletaArray();
				Atleta[] c = Ordenador.SelectionSort(db.atletas, db.atletas.length);
				for (Atleta sAtletas : db.atletas) {
					ps.println(sAtletas.getConcatenedArgs());
				}
				break;
			case 5:
				ps.println("InsertionSort");
				db.open_to_AtletaArray();
				Atleta[] d = Ordenador.insertionSort(db.atletas);
				for (Atleta iAtletas : db.atletas) {
					ps.println(iAtletas.getConcatenedArgs());
				}
				break;
				
			}
		}

		}
	}
