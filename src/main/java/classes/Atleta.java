/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author brendoja
 */
public class Atleta {

    private int hash;
    private String nome;
    private int idade;
    private int tipo_esporte;
    private String habilidades;

    public Atleta(String[] args, int hash) {
        this.hash = hash;
        this.idade = Integer.parseInt(args[1]);
        this.nome = args[0];
        this.tipo_esporte = Integer.parseInt(args[2]);
        this.habilidades = args[3];
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getTipo_esporte() {
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

    public void setTipo_esporte(int tipo_esporte) {
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
            String.valueOf(this.tipo_esporte), this.habilidades};
        return args;
    }
    /*
    @return String with all values of Atleta name;idade;tipo_esporte;habilidades
    
    
    */
    public String getConcatenedArgs(){
        return (this.nome.concat(";")
                        .concat(String.valueOf(this.idade))
                        .concat(";")
                        .concat(String.valueOf(this.tipo_esporte))
                        .concat(";")
                        .concat(this.habilidades));
    }

}
