/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package packageBiere;

/**
 *
 * @author Administrateur
 */
public class Commandes {
    private int iDMembre;
    private int iDCommande;
    private double coutTotal;
    private double tVQ;
    private double tPS;

    /**
     * Constructeur
     */
    public Commandes (int M, int Com, double CT, double PS, double VQ)
    {
        iDMembre = M;
        iDCommande = Com;
        coutTotal = CT;
        tVQ = VQ;
        tPS = PS;
    }

    /**
     * @return the iDMembre
     */
    public int getiDMembre() {
        return iDMembre;
    }

    /**
     * @param iDMembre the iDMembre to set
     */
    public void setiDMembre(int iDMembre) {
        this.iDMembre = iDMembre;
    }

    /**
     * @return the iDCommande
     */
    public int getiDCommande() {
        return iDCommande;
    }

    /**
     * @param iDCommande the iDCommande to set
     */
    public void setiDCommande(int iDCommande) {
        this.iDCommande = iDCommande;
    }

    /**
     * @return the coutTotal
     */
    public double getCoutTotal() {
        return coutTotal;
    }

    /**
     * @param coutTotal the coutTotal to set
     */
    public void setCoutTotal(double coutTotal) {
        this.coutTotal = coutTotal;
    }

    /**
     * @return the tVQ
     */
    public double gettVQ() {
        return tVQ;
    }

    /**
     * @param tVQ the tVQ to set
     */
    public void settVQ(double tVQ) {
        this.tVQ = tVQ;
    }

    /**
     * @return the tPS
     */
    public double gettPS() {
        return tPS;
    }

    /**
     * @param tPS the tPS to set
     */
    public void settPS(double tPS) {
        this.tPS = tPS;
    }
    
}
