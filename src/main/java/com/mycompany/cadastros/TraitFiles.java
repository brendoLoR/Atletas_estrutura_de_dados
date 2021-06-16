/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cadastros;

import classes.Atleta;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brendoja
 */
public class TraitFiles {

    public TraitFiles() {

    }

    /**
     * Create a file from a array of Atletas[]
     *
     * @param file String contendo o caminho do arquivo base
     */
    public static void atletaGenerate(String file, Atleta[] atletas) {

        BufferedWriter saida = null;

        try {
            saida = new BufferedWriter(new FileWriter(file));

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
     * @param file String contendo o caminho do arquivo base;
     * @return Atletas[] atleta;
     */
    public static Atleta[] atletaRead(String file) {
        List<Atleta> atletas = new ArrayList<Atleta>();
        BufferedReader saida = null;
        try {
            if (new File(file).exists()) {
                saida = new BufferedReader(new FileReader(file));
                String linha = saida.readLine();
                while (linha != null) {
//                    System.out.println(linha);
                    atletas.add(new Atleta(linha.split(";")));
                    linha = saida.readLine();
                }
                saida.close();
                //tPrinter.print(ps, tree.getRoot());
            }

        } catch (Exception ex) {
            Logger.getLogger(CadastroAtletas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (Atleta[]) atletas.toArray(new Atleta[atletas.size()]);
    }
}
