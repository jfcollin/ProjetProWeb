/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package packageBiere;

import java.sql.*;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
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
    private String UserName = "";
    private String Nom = "";
    private String Prenom = "";
    private String MotdePasse = "";
    private String ConfMotdePasse = "";
    private String Ville = "";
    private String CodePostale = "";
    private String Courriel = "";
    private String m_Erreur;            
    
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
     * @return the UserName
     */
    public String getUserName() {
        return UserName;
    }

    /**
     * @param UserName the UserName to set
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    /**
     * @return the CodePostale
     */
    public String getCodePostale() {
        return CodePostale;
    }

    /**
     * @param CodePostale the CodePostale to set
     */
    public void setCodePostale(String CodePostale) {
        this.CodePostale = CodePostale;
    }

    /**
     * @return the Nom
     */
    public String getNom() {
        return Nom;
    }

    /**
     * @param Nom the Nom to set
     */
    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    /**
     * @return the Prenom
     */
    public String getPrenom() {
        return Prenom;
    }

    /**
     * @param Prenom the Prenom to set
     */
    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    /**
     * @return the MotdePasse
     */
    public String getMotdePasse() {
        return MotdePasse;
    }

    /**
     * @param MotdePasse the MotdePasse to set
     */
    public void setMotdePasse(String MotdePasse) {
        this.MotdePasse = MotdePasse;
    }

    /**
     * @return the ConfMotdePasse
     */
    public String getConfMotdePasse() {
        return ConfMotdePasse;
    }

    /**
     * @param ConfMotdePasse the ConfMotdePasse to set
     */
    public void setConfMotdePasse(String ConfMotdePasse) {
        this.ConfMotdePasse = ConfMotdePasse;
    }

    /**
     * @return the Ville
     */
    public String getVille() {
        return Ville;
    }

    /**
     * @param Ville the Ville to set
     */
    public void setVille(String Ville) {
        this.Ville = Ville;
    }

    /**
     * @return the Courriel
     */
    public String getCourriel() {
        return Courriel;
    }

    /**
     * @param Courriel the Courriel to set
     */
    public void setCourriel(String Courriel) {
        this.Courriel = Courriel;
    }
    
        /**
     * @return the m_Erreur
     */
    public String getM_Erreur() {
        return m_Erreur;
    }

    /**
     * @param m_Erreur the m_Erreur to set
     */
    public void setM_Erreur(String m_Erreur) {
        this.m_Erreur = m_Erreur;
    }    
    
    public String creemembre()
    {
        String retour = "";
        if (!MotdePasse.equals(ConfMotdePasse))
        {
            m_Erreur = ("*Mot de passe différent de celui inscrit");
        }
        else
        {
            try
            {
            Connection con;
            Statement st;
            //ResultSet rs = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "");
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String RequeteSQL= "INSERT INTO bieresfoufoufou.membre(Nom,Prenom,NomUtilisateur,MotPasse,Ville,CodePostal,Courriel) values ('" +Nom+"','"+Prenom+"','"+UserName+"','"+MotdePasse+"','"+Ville+"','"+CodePostale+"','"+Courriel+"')";

            st.executeUpdate(RequeteSQL);
            //rs = st.executeQuery("INSERT INTO test.dedolle(Nom,Description,Lien) values ('" + request.getParameter("sAjouterNom")+ "','"+request.getParameter("sAjouterDes")+"','"+request.getParameter("sAjouterLien")+"')");
            //out.print("Ajout réussis");
            con.close();
            retour = "index.xhtml";
            }
            catch(Exception ex)
            {
                //out.print(ex.toString());
            }        
        }
        return retour;
    } 
}

