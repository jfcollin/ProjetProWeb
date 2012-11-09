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
                        tBieres.add(new packageBiere.Bieres(rs.getString("NomBiere"), 
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
}
