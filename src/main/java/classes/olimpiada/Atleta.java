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
    private String tipo_esporte;
    private String habilidades;

    public Atleta(String[] args, int hash) {
        this.hash = hash;
        this.idade = Integer.parseInt(args[1]);
        this.nome = args[0];
        this.tipo_esporte = args[2];
        this.habilidades = args[3];
    }
    
    public Atleta(String[] args) {
        this.hash = args[0].hashCode();
        this.idade = Integer.parseInt(args[1]);
        this.nome = args[0];
        this.tipo_esporte = args[2];
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
        String[] args = {this.nome, String.valueOf(this.idade),
            this.tipo_esporte, this.habilidades};
        return args;
    }
    /**
     * 
     * @return String with all values of Atleta name;idade;tipo_esporte;habilidades 
     */
    public String getConcatenedArgs(){
        return (this.nome.concat(";")
                        .concat(String.valueOf(this.idade))
                        .concat(";")
                        .concat(this.tipo_esporte)
                        .concat(";")
                        .concat(this.habilidades));
    }
    public void set_by_position(String arg, int position){
        switch (position){
            case 0:
                this.setNome(arg);
            case 1:
                this.setIdade(Integer.parseInt(arg));
            case 2:
                this.setTipo_esporte(arg);
            case 3:
                this.setHabilidades(arg);
        }
    }
}
