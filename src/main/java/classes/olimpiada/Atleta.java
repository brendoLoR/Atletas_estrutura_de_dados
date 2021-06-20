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
package classes.olimpiada;

/**
 *
 * @author brendoja
 */
public class Atleta {

    private int hash;
    private String nome;
    private int idade;
    private String tipo_esporte = "BASQUETE";
    private String habilidades;
    private int qtd_pontos;

    public Atleta(String[] args, int hash) {
        this.hash = hash;
        this.nome = args[0];
        this.idade = Integer.parseInt(args[1]);
        this.qtd_pontos = Integer.parseInt(args[2]);
        this.habilidades = args[3];
    }

    public Atleta(String[] args) {
        this.hash = args[0].hashCode();
        this.nome = args[0];
        this.idade = Integer.parseInt(args[1]);
        this.qtd_pontos = Integer.parseInt(args[2]);
        this.habilidades = args[3];
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getTipo_esporte() {
        return tipo_esporte;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setTipo_esporte(String tipo_esporte) {
        this.tipo_esporte = tipo_esporte;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public String[] getArgs() {
        String[] args = {
            this.nome,
            String.valueOf(this.idade),
            String.valueOf(this.qtd_pontos),
            this.habilidades,
            this.tipo_esporte
        };
        return args;
    }

    /**
     *
     * @return String with all values of Atleta
     * name;idade;tipo_esporte;qtd_pontos;habilidades
     */
    public String getConcatenedArgs() {
        return (this.nome.concat(";")
                .concat(String.valueOf(this.idade))
                .concat(";")
                .concat(String.valueOf(this.qtd_pontos))
                .concat(";")
                .concat(this.habilidades)
                .concat(";")
                .concat(this.tipo_esporte));
    }

    public void set_by_position(String arg, int position) {
        switch (position) {
            case 0:
                this.setNome(arg);
            case 1:
                this.setIdade(Integer.parseInt(arg));
            case 2:
                this.setQtd_pontos(Integer.parseInt(arg));
            case 3:
                this.setHabilidades(arg);
            case 4:
                this.setTipo_esporte("BASQUETE");
                
        }
    }

    /**
     * @return the qtd_pontos
     */
    public int getQtd_pontos() {
        return qtd_pontos;
    }

    /**
     * @param qtd_pontos the qtd_pontos to set
     */
    public void setQtd_pontos(int qtd_pontos) {
        this.qtd_pontos = qtd_pontos;
    }
}
