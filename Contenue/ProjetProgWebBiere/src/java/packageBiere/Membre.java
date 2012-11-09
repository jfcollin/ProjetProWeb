/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package packageBiere;

/**
 *
 * @author Administrateur
 */
public class Membre {
    private int idMembre;
    private String userName;
    private String nom;
    private String prenom;
    private String motdePasse;
    private String confMotdePasse;
    private String ville;
    private String codePostale;
    private String courriel;
    
    
    
    public Membre (int id, String idb, String n, String nb, String f, String npc, String p, String h, String k)
    {
    idMembre = id;    
    userName = idb;
    nom = n;
    prenom = nb;
    motdePasse = f;
    confMotdePasse = npc;
    ville = p;
    codePostale = h;
    courriel = k;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the motdePasse
     */
    public String getMotdePasse() {
        return motdePasse;
    }

    /**
     * @param motdePasse the motdePasse to set
     */
    public void setMotdePasse(String motdePasse) {
        this.motdePasse = motdePasse;
    }

    /**
     * @return the confMotdePasse
     */
    public String getConfMotdePasse() {
        return confMotdePasse;
    }

    /**
     * @param confMotdePasse the confMotdePasse to set
     */
    public void setConfMotdePasse(String confMotdePasse) {
        this.confMotdePasse = confMotdePasse;
    }

    /**
     * @return the ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * @return the codePostale
     */
    public String getCodePostale() {
        return codePostale;
    }

    /**
     * @param codePostale the codePostale to set
     */
    public void setCodePostale(String codePostale) {
        this.codePostale = codePostale;
    }

    /**
     * @return the courriel
     */
    public String getCourriel() {
        return courriel;
    }

    /**
     * @param courriel the courriel to set
     */
    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    /**
     * @return the idMembre
     */
    public int getIdMembre() {
        return idMembre;
    }

    /**
     * @param idMembre the idMembre to set
     */
    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

   
}
