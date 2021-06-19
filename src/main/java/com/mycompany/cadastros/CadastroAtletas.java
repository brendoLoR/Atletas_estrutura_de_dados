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

	static PrintStream ps = new PrintStream(System.out);
	static BinaryTreePrinter tPrinter = new BinaryTreePrinter();

	static String database = "atletas.dat";
	static Persistencia db = new Persistencia(database);

	@SuppressWarnings("empty-statement")
	public static void main(String args[]) {

		db.open_to_AtletaArray();

		Random rand = new Random();
		Scanner scan = new Scanner(System.in);
		ps.println("insira a quantidade de atletas para cadastro: ");
		int nums = Integer.parseInt(scan.nextLine());

		Atleta[] atletas = new Atleta[nums];
		for (int i = 0; i < nums; i++) {
			try {

				String[] atleta = new String[4];
				ps.println("ATLETA Nº " + i + "\n");
				ps.println("NOME: ");
				atleta[0] = scan.nextLine();
//				atleta[0] = "brendo" + rand.nextInt(99999);

				ps.println("IDADE: ");
				atleta[1] = String.valueOf(Integer.parseInt(scan.nextLine()));
//                atleta[1] = String.valueOf(rand.nextInt(99999));
				ps.println("ESPORTE: ");
				atleta[2] = scan.nextLine();
//                atleta[2] = "esporte";
				ps.println("HABILIDADES: ");
				atleta[3] = scan.nextLine();
//                atleta[3] = "sei jogar de tudo";

				atletas[i] = new Atleta(atleta);
			} catch (Exception e) {
				i--;
			}
		}
		
/*
		for (Atleta a : db.atletas) {
			ps.println(a.getConcatenedArgs());
		}

		db.add(atletas);
		db.save();
		ps.println();
		ps.println();
		ps.println();

		for (Atleta a : db.atletas) {
			ps.println(a.getConcatenedArgs());
		}

		ps.println();
		ps.println();
		ps.println();
		
		Atleta[] b = Ordenador.quicksort(db.atletas, 0, db.atletas.length);
	for (Atleta natleta : db.atletas) {
			ps.println(natleta.getConcatenedArgs());
		}
*/

		db.close();

	}

}
