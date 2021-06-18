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

/**
 *
 * @author brendoja
 */
public class Persistencia {

    private BalancedTree tree = new BalancedTree();
    private Boolean treeStatus;

    private Atleta[] atletas;
    private Boolean arrayStatus;

    public Persistencia() {

    }

    /**
     *
     * @param database_file
     * @return true se tudo for bem
     */
    private Boolean open_to_tree(String database_file) {
        try {
            if (!this.treeStatus && !this.arrayStatus) {
                this.tree.addMultAtletas(TraitFiles.atletaRead(database_file));
                this.treeStatus = true;
            } else if (this.treeStatus && !this.arrayStatus) {
                TraitFiles.atletaGenerate(database_file, this.getTree().inOrder(getTree().getRoot(), new ArrayList<>()));
                this.tree.addMultAtletas(TraitFiles.atletaRead(database_file));
                this.treeStatus = true;
            } else if (this.arrayStatus && !this.treeStatus) {
                TraitFiles.atletaGenerate(database_file, this.getAtletas());
                this.atletas = null;
                this.tree.addMultAtletas(TraitFiles.atletaRead(database_file));
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
     * @param database_file
     * @return true se tudo for bem
     */
    private Boolean open_to_AtletaArray(String database_file) {
        try {
            if (!this.treeStatus && !this.arrayStatus) {
                this.atletas = TraitFiles.atletaRead(database_file);
                this.arrayStatus = true;
            } else if (!this.treeStatus && this.arrayStatus) {
                TraitFiles.atletaGenerate(database_file, this.getAtletas());
                this.atletas = TraitFiles.atletaRead(database_file);
                this.arrayStatus = true;
            } else if (!this.arrayStatus && this.treeStatus) {
                TraitFiles.atletaGenerate(database_file, this.getTree().inOrder(getTree().getRoot(), new ArrayList<>()));
                this.tree = null;
                this.atletas = TraitFiles.atletaRead(database_file);
                this.arrayStatus = true;
                this.treeStatus = false;
            }
        } catch (Exception ex) {
            this.treeStatus = false;
            this.arrayStatus = false;
        }
        return this.getTreeStatus();
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

}
