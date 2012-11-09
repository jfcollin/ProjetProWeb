package packageBiere;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Administrateur
 */
@ManagedBean
@RequestScoped
public class BeanBieres {
    
    private ArrayList tBieres;
    private ArrayList tCommandes;
    private ArrayList m_Lignes;

    /**
     * Creates a new instance of BeanMembres
     */
    public BeanBieres() {
    }
        
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

    /**
     * @return the tCommandes
     */
    public ArrayList gettCommandes() {
        
        Boolean bValide=true;
        tCommandes = new ArrayList();
         try
        {
        
        ResultSet rs = null;
        Connection con;
        Statement st;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "");
        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        rs = st.executeQuery("Select * from bieresfoufoufou.commande");
        while(bValide==true)
            {
               bValide = rs.next();
               if(bValide==true)
                    {
                        tCommandes.add(new packageBiere.Commandes(rs.getInt("IDMembre"), 
                                    rs.getInt("IDCommande"), rs.getDouble("CoutTotal"), 
                                    rs.getDouble("TVQ"), rs.getDouble("TPS")));
                    }
             }
        }
        catch(Exception ex)
        {
            //out.print(ex.toString());
        }
        return tCommandes;
    }

    /**
     * @param tCommandes the tCommandes to set
     */
    public void settCommandes(ArrayList tCommandes) {
        this.tCommandes = tCommandes;
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
