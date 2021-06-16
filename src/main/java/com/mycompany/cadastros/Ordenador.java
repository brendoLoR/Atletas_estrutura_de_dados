/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cadastros;

import classes.Atleta;

/**
 *
 * @author brendoja
 */
public class Ordenador {
    
    public Ordenador(){
        
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
    
}
