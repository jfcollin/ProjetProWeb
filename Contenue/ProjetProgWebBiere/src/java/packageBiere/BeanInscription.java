/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package packageBiere;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Administrateur
 */
@ManagedBean
@SessionScoped
public class BeanInscription {

    /**
     * Creates a new instance of BeanInscription
     */
    private String [] jourvaleur = new String[31];
    private String jour="1";
    private String [] moisvaleur = new String[12];
    private String mois="1";
    private String [] anneevaleur = new String [83];
    private String annee="1990";
    public BeanInscription() {
    }

    /**
     * @return the mois
     */
    public String getMois() {
        return mois;
    }

    /**
     * @param mois the mois to set
     */
    public void setMois(String mois) {
        this.mois = mois;
    }

    /**
     * @return the moisvaleur
     */
    public String[] getMoisvaleur() {
        for(int i=0; i<=11; i++)
        {
            moisvaleur[i] = Integer.toString(i+1);
        }
        return moisvaleur;
    }

    /**
     * @param moisvaleur the moisvaleur to set
     */
    public void setMoisvaleur(String[] moisvaleur) {
        this.moisvaleur = moisvaleur;
    }   

    /**
     * @return the jourvaleur
     */
    public String[] getJourvaleur() {
        for(int i=0; i<=30; i++)
        {
            jourvaleur[i] = Integer.toString(i+1);
        }
        return jourvaleur;
    }

    /**
     * @param jourvaleur the jourvaleur to set
     */
    public void setJourvaleur(String[] jourvaleur) {
        this.jourvaleur = jourvaleur;
    }

    /**
     * @return the jour
     */


    /**
     * @return the anneevaleur
     */
    public String[] getAnneevaleur() {
        for(int i=0; i<=82; i++)
        {
            anneevaleur[i] = Integer.toString(i+1912);
        }
        return anneevaleur;
    }

    /**
     * @param anneevaleur the anneevaleur to set
     */
    public void setAnneevaleur(String[] anneevaleur) {
        this.anneevaleur = anneevaleur;
    }

    /**
     * @return the annee
     */
    public String getAnnee() {
        return annee;
    }

    /**
     * @param annee the annee to set
     */
    public void setAnnee(String annee) {
        this.annee = annee;
    }

    /**
     * @return the jour
     */
    public String getJour() {
        return jour;
    }

    /**
     * @param jour the jour to set
     */
    public void setJour(String jour) {
        this.jour = jour;
    }

    /**
     * @return the annee
     */
    

}

