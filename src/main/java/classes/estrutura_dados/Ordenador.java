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

import classes.olimpiada.Atleta;

/**
 *
 * @author brendoja
 */
public class Ordenador {

    public Ordenador() {

    }

    /**
     *
     * @param atletas Atleta[]
     * @return sorted Atleta[]
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
     * @code Atleta[] atletasArr = Ordenador.quicksort(atletas, 0, atletas.length);
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

    public static Atleta[] buubleSort(Atleta[] atletas) {
        for (int i = 0; i < atletas.length; i++) {
            for (int j = 0; j < atletas.length - 1; j++) {
                if (atletas[j].getHash() > atletas[j + 1].getHash()) {
                    Atleta aux = atletas[j];
                    atletas[j] = atletas[j + 1];
                    atletas[j + 1] = aux;
                }
            }
        }
        return atletas;
    }

}
