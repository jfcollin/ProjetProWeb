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
    private ArrayList tBieres;
    private ArrayList m_Lignes;
            
    
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

    /**
     * @return the tBieres
     */
    public ArrayList gettBieres() 
    {
        Boolean bValide=true;
        tBieres = new ArrayList();
         try
        {
        
        ResultSet rs = null;
        Connection con;
        Statement st;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "");
        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        rs = st.executeQuery("Select * from bieresfoufoufou.biere");
        while(bValide==true)
            {
               bValide = rs.next();
               if(bValide==true)
                    {
                        tBieres.add(new packageBiere.Bieres(rs.getInt("IDBiere"), rs.getString("NomBiere"), 
                                    rs.getInt("NombreCaisse"), rs.getInt("Format"), 
                                    rs.getInt("NombreParCaisse"), rs.getDouble("Prix")));
                    }
             }
        }
        catch(Exception ex)
        {
            //out.print(ex.toString());
        }
        
        return tBieres;
    }

    /**
     * @param tBieres the tBieres to set
     */
    public void settBieres(ArrayList tBieres) {
        this.tBieres = tBieres;
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
    
    public String passercommande ()
    {
        m_Lignes = new ArrayList();
        String retour="";
        double Cout=0;
        double TPS=0;
        double TVQ=0;
        int IDCmd=0;
        int insertedKeyValue=0;
        String Erreur="";
        
        for (int i=0; i<tBieres.size(); i++)
        {
            Bieres beer = (Bieres)tBieres.get(i);
            if (beer.getNbcommande()>0)
            {
                m_Lignes.add(new packageBiere.Ligne(beer.getIdbiere(),beer.getNbcommande()));
                Cout += beer.getPrix() * beer.getNbcommande();
            }
        }
        
        if (m_Lignes.size()>0)
        {
            TPS=Cout*0.05;
            TVQ = (Cout+TPS)*0.095;
            try
            {
                
                Connection con;
                Statement st;
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "");
                st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                String RequeteSQL= "INSERT INTO bieresfoufoufou.commande(IDMembre, CoutTotal, TPS, TVQ) values ('1','"+Cout+"','"+TPS+"','"+TVQ+"')";
                st.executeUpdate(RequeteSQL, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next())
                {
                    insertedKeyValue = rs.getInt(1);
                }
                con.close();
            }
            catch(Exception ex)
            {
                Erreur = ex.toString();
            }
           
            
            for (int i=0; i < m_Lignes.size(); i++)
            {
                Ligne ligneTemp = (Ligne)m_Lignes.get(i);
                try
                {
                Connection con2;
                Statement st2;
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con2 = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "");
                st2 = con2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                String RequeteSQL= "INSERT INTO bieresfoufoufou.ligne(IDCommande, IDBiere, NbCaisse) values ('"+ insertedKeyValue +"','"+ligneTemp.getIdbiere()+"','"+ligneTemp.getNbcaisse()+"')";
                st2.executeUpdate(RequeteSQL, Statement.RETURN_GENERATED_KEYS);            
                con2.close();
                }
                catch(Exception ex)
                {
                    //out.print(ex.toString());
                }
            }
            retour = "Commander.xhtml";
            
            
        }
        
        return retour;
    }

    /**
     * @return the m_Lignes
     */
    public ArrayList getM_Lignes() {
        return m_Lignes;
    }

    /**
     * @param m_Lignes the m_Lignes to set
     */
    public void setM_Lignes(ArrayList m_Lignes) {
        this.m_Lignes = m_Lignes;
    }

    
}

