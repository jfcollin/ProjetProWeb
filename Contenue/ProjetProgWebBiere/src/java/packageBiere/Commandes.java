/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package packageBiere;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private double total;
    private Date datecmd;
    private String dateheure;
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
        total = coutTotal + tVQ + tPS;
        
    }
    public Commandes (int M, int Com, double CT, double PS, double VQ, Date d)
    {
        iDMembre = M;
        iDCommande = Com;
        coutTotal = CT;
        tVQ = VQ;
        tPS = PS;
        total = coutTotal + tVQ + tPS;
        datecmd = d;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateheure = dateFormat.format(datecmd);
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

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the datecmd
     */
    public Date getDatecmd() {
        return datecmd;
    }

    /**
     * @param datecmd the datecmd to set
     */
    public void setDatecmd(Date datecmd) {
        this.datecmd = datecmd;
    }

    /**
     * @return the dateheure
     */
    public String getDateheure() {
            
        return dateheure;
    }

    /**
     * @param dateheure the dateheure to set
     */
    public void setDateheure(String dateheure) {
        this.dateheure = dateheure;
    }
    
}
