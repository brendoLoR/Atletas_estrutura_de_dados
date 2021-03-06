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
package classes.persistencia_dados;

import classes.estrutura_dados.BalancedTree;
import classes.olimpiada.Atleta;
import classes.persistencia_dados.tratamento_arquivos.TraitFiles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author brendoja
 */
public class Persistencia {

    public BalancedTree tree = new BalancedTree();
    private Boolean treeStatus = false;

    public Atleta[] atletas;
    private Boolean arrayStatus = false;

    private String database;

    public Persistencia(String database) {
        this.database = database;
    }

    /**
     *
     * @param array_tree
     * @return
     */
    public Boolean open(Boolean array_tree) {
        return array_tree ? open_to_AtletaArray() : open_to_tree();
    }

    /**
     *
     * @param this.database
     * @return true se tudo for bem
     */
    public Boolean open_to_tree() {
        try {
            if (!this.treeStatus && !this.arrayStatus) {
                this.tree.addMultAtletas(TraitFiles.atletaRead(this.getDatabase()));
                this.treeStatus = true;
            } else if (this.treeStatus && !this.arrayStatus) {
                this.save();
                this.tree.addMultAtletas(TraitFiles.atletaRead(this.getDatabase()));
                this.treeStatus = true;
            } else if (this.arrayStatus && !this.treeStatus) {
                this.save();
                this.atletas = null;
                this.tree.addMultAtletas(TraitFiles.atletaRead(this.getDatabase()));
                this.treeStatus = true;
                this.arrayStatus = false;
            }
        } catch (Exception ex) {
            this.treeStatus = false;
            this.arrayStatus = false;
        }
        return this.getTreeStatus();
    }

    /**
     *
     * @param this.database
     * @return true se tudo for bem
     */
    public Boolean open_to_AtletaArray() {
        try {
            if (!this.treeStatus && !this.arrayStatus) {
                this.atletas = TraitFiles.atletaRead(this.getDatabase());
                this.arrayStatus = true;
            } else if (!this.treeStatus && this.arrayStatus) {
                TraitFiles.atletaGenerate(this.getDatabase(), this.getAtletas());
                this.atletas = TraitFiles.atletaRead(this.getDatabase());
                this.arrayStatus = true;
            } else if (!this.arrayStatus && this.treeStatus) {
                TraitFiles.atletaGenerate(this.getDatabase(), this.getTree().inOrder(getTree().getRoot(), new ArrayList<>()));
                this.tree = null;
                this.atletas = TraitFiles.atletaRead(this.getDatabase());
                this.arrayStatus = true;
                this.treeStatus = false;
            }
        } catch (Exception ex) {
            this.treeStatus = false;
            this.arrayStatus = false;
        }
        return this.getArrayStatus();
    }

    public void close() {
        try {
            if (this.arrayStatus) {
                this.save();
                this.atletas = null;
                this.arrayStatus = false;
            } else if (this.treeStatus) {
                this.save();
                this.tree = null;
                this.treeStatus = false;
            } else {
                System.out.println("you need to start the transaction before");
            }
        } catch (Exception e) {
            System.out.println("erro ao salvar");
        }
    }

    /**
     * you need to use try{}catch(Exception e){}
     *
     * @param name
     * @return
     */
    public Atleta search_by_name(String name) {
        int hashName = name.hashCode();
        if (this.treeStatus) {
            try{
                return this.tree.search(hashName).getAtleta();
            }catch(Exception e){
                throw new RuntimeException("name not found");
            }
        } else if (this.arrayStatus) {
            for (Atleta atleta : atletas) {
                if (atleta.getHash() == hashName) {
                    return atleta;
                }
            }
            throw new RuntimeException("name not found");
        } else {
            throw new RuntimeException("you need to open the transiction before search");
        }
    }

    /**
     *
     * @return true se tudo for ok
     */
    public Boolean save() {
        try {
            if (this.arrayStatus) {
                TraitFiles.atletaGenerate(this.getDatabase(), this.atletas);
                return true;
            } else if (this.treeStatus) {
                TraitFiles.atletaGenerate(this.getDatabase(), tree.inOrder(tree.getRoot(), new ArrayList<>()));
                return true;
            } else {
                System.out.println("you need to start the transaction before");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     *
     * @param args = [name, idade, tipo_esporte, ]
     * @return
     */
    public Boolean update_atleta(String name, String new_value, int position) {
        int hashName = name.hashCode();
        if (this.treeStatus) {
            this.tree.update_atleta(hashName, new_value, position);
            return this.save();
        } else if (this.arrayStatus) {
            for (int i = 0; i < this.atletas.length; i++) {
                if (atletas[i].getHash() == hashName) {
                    atletas[i].set_by_position(new_value, position);
                    return this.save();
                }
            }
            System.out.println("name not found");
            return false;
        } else {
            throw new RuntimeException("you need to open the transiction before search");
        }
    }

    /**
     *
     * @param name
     * @return
     */
    public Boolean remove(String name) {
        int hashName = name.hashCode();
        if (this.treeStatus) {
            this.tree.remove(hashName);
            return this.save();
        } else if (this.arrayStatus) {
            for (int i = 0; i < this.atletas.length; i++) {
                if (atletas[i].getHash() == hashName) {
                    atletas[i] = null;
                    return this.save() ? this.open_to_AtletaArray() : false;
                }
            }
            System.out.println("name not found");
            return false;
        } else {
            throw new RuntimeException("you need to open the transiction before search");
        }
    }

    /**
     *
     * @param atleta
     * @return
     */
    public Boolean add(Atleta atleta) {
        if (this.treeStatus) {
            this.tree.add(atleta);
            return this.save();
        } else if (this.arrayStatus) {
            List<Atleta> tmp = Arrays.asList(this.atletas);
            tmp.add(atleta);
            atletas = (Atleta[]) tmp.toArray();
            this.save();
            return true;
        } else {
            throw new RuntimeException("you need to open the transiction before");
        }
    }

    public Boolean add(Atleta[] new_atletas) { //10
        if (this.treeStatus) {
            this.tree.addMultAtletas(new_atletas);
            return this.save();
        } else if (this.arrayStatus) {
            int i = this.atletas.length; //5
            this.atletas = Arrays.copyOf(this.atletas, this.atletas.length + new_atletas.length); //5 + 10 = 15
            for (int j = 0; j < new_atletas.length; j++) { // 0 < 10 -> 5+0; 5 + 1; 5+2;... 5 + 9;
                atletas[i + j] = new_atletas[j];
            }
            this.save();
            return true;
        } else {
            throw new RuntimeException("you need to open the transiction before");
        }
    }

    /**
     * @return the tree
     */
    public BalancedTree getTree() {
        return tree;
    }

    /**
     * @return the treeStatus
     */
    public Boolean getTreeStatus() {
        return treeStatus;
    }

    /**
     * @return the atletas
     */
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public Atleta[] getAtletas() {
        return atletas;
    }

    /**
     * @return the arrayStatus
     */
    public Boolean getArrayStatus() {
        return arrayStatus;
    }

    /**
     * @return the database
     */
    public String getDatabase() {
        return this.database;
    }

    /**
     * @param database the database to set
     */
    public void setDatabase(String database) {
        this.database = database;
    }

}
