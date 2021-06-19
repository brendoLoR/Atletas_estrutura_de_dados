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
package classes.persistencia_dados.tratamento_arquivos;

import classes.olimpiada.Atleta;
import com.mycompany.cadastros.CadastroAtletas;
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
