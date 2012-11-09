/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package packageBiere;

/**
 *
 * @author Administrateur
 */
public class Ligne {
    private int idcommande;
    private int idbiere;
    private int nbcaisse;
    
    public Ligne (int idb,int nb)
    {
        idbiere = idb;
        nbcaisse = nb;
    }

    /**
     * @return the idcommande
     */
    public int getIdcommande() {
        return idcommande;
    }

    /**
     * @param idcommande the idcommande to set
     */
    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    /**
     * @return the idbiere
     */
    public int getIdbiere() {
        return idbiere;
    }

    /**
     * @param idbiere the idbiere to set
     */
    public void setIdbiere(int idbiere) {
        this.idbiere = idbiere;
    }

    /**
     * @return the nbcaisse
     */
    public int getNbcaisse() {
        return nbcaisse;
    }

    /**
     * @param nbcaisse the nbcaisse to set
     */
    public void setNbcaisse(int nbcaisse) {
        this.nbcaisse = nbcaisse;
    }
    
    
}
