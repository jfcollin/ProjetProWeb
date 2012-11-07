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
    private String nom;
    private int nombrecaisses;
    private int format;
    private int nombreparcaisse;
    private double prix;

    /**
     * Constructeur
     */
    public Bieres (String n, int nb, int f, int npc, double p)
    {
        nom = n;
        nombrecaisses = nb;
        format = f;
        nombreparcaisse = npc;
        prix = p;
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
    
}
