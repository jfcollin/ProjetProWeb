/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package packageBiere;

/**
 *
 * @author Administrateur
 */
public class Bieres {
    private int idbiere;
    private String nom;
    private int nombrecaisses;
    private int format;
    private int nombreparcaisse;
    private double prix;
    private int nbcommande;
    private int nouvnbcaisses;
    private double nouvprix;

    /**
     * Constructeur
     */
    public Bieres (int idb, String n, int nb, int f, int npc, double p)
    {
        idbiere = idb;
        nom = n;
        nombrecaisses = nb;
        format = f;
        nombreparcaisse = npc;
        prix = p;
        nbcommande=0;
        nouvnbcaisses=0;
        nouvprix=0;
    }
    
    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }   

    
    /**
     * @return the Prix
     */
    

    /**
     * @return the nombrecaisses
     */
    public int getNombrecaisses() {
        return nombrecaisses;
    }

    /**
     * @param nombrecaisses the nombrecaisses to set
     */
    public void setNombrecaisses(int nombrecaisses) {
        this.nombrecaisses = nombrecaisses;
    }

    /**
     * @return the format
     */
    public int getFormat() {
        return format;
    }

    /**
     * @param format the format to set
     */
    public void setFormat(int format) {
        this.format = format;
    }

    /**
     * @return the nombreparcaisse
     */
    public int getNombreparcaisse() {
        return nombreparcaisse;
    }

    /**
     * @param nombreparcaisse the nombreparcaisse to set
     */
    public void setNombreparcaisse(int nombreparcaisse) {
        this.nombreparcaisse = nombreparcaisse;
    }

    /**
     * @return the prix
     */
    public double getPrix() {
        return prix;
    }

    /**
     * @param prix the prix to set
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * @return the nbcommande
     */
    public int getNbcommande() {
        return nbcommande;
    }

    /**
     * @param nbcommande the nbcommande to set
     */
    public void setNbcommande(int nbcommande) {
        this.nbcommande = nbcommande;
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
     * @return the nouvnbcaisses
     */
    public int getNouvnbcaisses() {
        return nouvnbcaisses;
    }

    /**
     * @param nouvnbcaisses the nouvnbcaisses to set
     */
    public void setNouvnbcaisses(int nouvnbcaisses) {
        this.nouvnbcaisses = nouvnbcaisses;
    }

    /**
     * @return the nouvprix
     */
    public double getNouvprix() {
        return nouvprix;
    }

    /**
     * @param nouvprix the nouvprix to set
     */
    public void setNouvprix(double nouvprix) {
        this.nouvprix = nouvprix;
    }
    
}
