/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package packageBiere;

import java.sql.*;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
    private ArrayList tMembres;
    //Information pour la bd
    private String motpassebd="";
    private String stringconnection = "jdbc:mysql://localhost/bieresfoufoufou";
    private String userbd = "root";
    private Connection m_con;
    
    
    public BeanInscription() {
    }
    
    private Connection sconnection ()
    {
        
        try
        {
            m_con = DriverManager.getConnection(stringconnection, userbd, motpassebd);
        }
        catch (Exception ex)
        {
             
        }
      
        return m_con;
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
        Boolean bValide=true;
        int i=0;
        String retour = "";
        String valideuser="";
        if (!MotdePasse.equals(ConfMotdePasse))
        {
            m_Erreur = ("*Mot de passe différent de celui inscrit");
        }
        else
        {
            try
            {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            PreparedStatement pst3=null;
            ResultSet rs3 = null;
            String Requete3 = "Select NomUtilisateur from bieresfoufoufou.membre where NomUtilisateur=?";
            String Param = UserName;                    
                    

            pst3 = sconnection().prepareStatement(Requete3, 1005, 1008);
            pst3.clearParameters();
            pst3.setString(1, Param);
            rs3 = pst3.executeQuery(); 
            while(bValide==true)
            {
               bValide = rs3.next();
               try
               {
               valideuser = rs3.getString("NomUtilisateur");
               if (UserName.equals(valideuser))
               {
                    bValide = false;
                    i++;
               }
               }
               catch (Exception ex)
               {
                  i=0 ;        
               }
            }
            sconnection().close();
            
                if (i>0)
                {
                    m_Erreur = "*Nom d'utilisateur déjà utilisé";
                }
                else
                {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                PreparedStatement pst4=null;
                String Requete4 = "INSERT INTO bieresfoufoufou.membre(Nom,Prenom,NomUtilisateur,MotPasse,Ville,CodePostal,Courriel) values (?,?,?,?,?,?,?)";
                String Params4[] = new String[7];// parce que tout les parameters sont des String...
                    Params4[0] = Nom;
                    Params4[1] = Prenom;
                    Params4[2] = UserName;
                    Params4[3] = MotdePasse;
                    Params4[4] = Ville;
                    Params4[5] = CodePostale;
                    Params4[6] = Courriel;
                    

                pst4 = sconnection().prepareStatement(Requete4, 1005, 1008);
                pst4.clearParameters();
                
                for (int k=0; k < Params4.length;k++)
                {
                    pst4.setString(k+1, Params4[k]);
                }
                
                pst4.executeUpdate();
                
                sconnection().close(); 
                m_Erreur ="";
                retour = "index.xhtml";
                }
                }
                catch(Exception ex)
                {
                    m_Erreur = ex.toString();
                }        
            }
            return retour;
    } 

    /**
     * @return the tMembres
     */
    public ArrayList gettMembres() {
        String erreur="";
        Boolean bValide=true;
        tMembres = new ArrayList();
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            PreparedStatement pst3=null;
            ResultSet rs3 = null;
            String Requete3 = "Select * from bieresfoufoufou.membre";
                                        
                    

            pst3 = sconnection().prepareStatement(Requete3, 1005, 1008);
            pst3.clearParameters();
            rs3 = pst3.executeQuery(); 
        while(bValide==true)
            {
               bValide = rs3.next();
               if(bValide==true)
                    {
                        if (!rs3.getBoolean("Admin"))
                        {
                            tMembres.add(new packageBiere.Membre(rs3.getInt("IDMembre"), 
                                        rs3.getString("NomUtilisateur"), rs3.getString("Nom"), 
                                        rs3.getString("Prenom"), rs3.getString("MotPasse"),
                                        rs3.getString("MotPasse"), rs3.getString("Ville"),
                                        rs3.getString("CodePostal"), rs3.getString("Courriel")));
                        }
                    }
             }
        sconnection().close();
        }
        
        catch(Exception ex)
        {
            erreur = ex.toString();
        }
        return tMembres;
    }

    /**
     * @param tMembres the tMembres to set
     */
    public void settMembres(ArrayList tMembres) {
        this.tMembres = tMembres;
    }
    public String connexion ()
    {
        
        String retour="";
        Boolean Admin=false;
        int idmembre=-1;
        try
        {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
            PreparedStatement pst3=null;
            ResultSet rs3 = null;
            String Requete3 = "Select * from bieresfoufoufou.membre where NomUtilisateur=? AND MotPasse=?";
            String Params4[] = new String[2];// parce que tout les parameters sont des String...
                    Params4[0] = UserName;
                    Params4[1] = MotdePasse;
            
            pst3 = sconnection().prepareStatement(Requete3, 1005, 1008);
                      
            pst3.clearParameters();
            
            for (int k=0; k < Params4.length;k++)
                {
                    pst3.setString(k+1, Params4[k]);
                }
            
            rs3 = pst3.executeQuery();
            if (rs3.next());
            {
            Admin = rs3.getBoolean("Admin");
            idmembre = rs3.getInt("idmembre");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isadmin", Admin);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idmem", idmembre);
            }
            
            if (Admin)
            {
                retour = "AdminListeBeer.xhtml";
            }
            else
            {
                retour = "ListeBeer.xhtml";
            }
            sconnection().close();
        }
        catch(Exception ex)
        {
            m_Erreur ="nom d'utilisateur ou mot de passe non valide.";
            retour ="";
        }
        return retour;
        
    }
}


