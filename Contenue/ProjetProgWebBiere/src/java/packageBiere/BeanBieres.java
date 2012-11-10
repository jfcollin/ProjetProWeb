package packageBiere;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.Date;

/**
 *
 * @author Administrateur
 */
@ManagedBean
@SessionScoped
public class BeanBieres {
    
    private ArrayList tBieres;
    private ArrayList tCommandes;
    private ArrayList m_Lignes;
    private double cout=0;
    private double tps=0;
    private double tvq=0;
    private double couttotal=0;
    private String m_Erreur="";
    private ArrayList m_Commandemem;

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
        con = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "toor");
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
        con = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "toor");
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
        for (int i=0; i<tBieres.size(); i++)
        {
            Bieres beer = (Bieres)tBieres.get(i);
            if (beer.getNbcommande()>0)
            {
                if (beer.getNbcommande() > beer.getNombrecaisses())
                {
                    m_Lignes.clear();
                    i= tBieres.size();
                    m_Erreur = "*Vous ne pouvez pas commander plus de caisses que le nombre disponible";
                }
                else
                {
                    m_Lignes.add(new packageBiere.Ligne(beer.getIdbiere(),beer.getNbcommande(), beer.getNom(), beer.getPrix()));
                    setCout(getCout() + beer.getPrix() * beer.getNbcommande());
                    m_Erreur = "";
                }
            }
        }
        
        if (m_Lignes.size()>0)
        {
            setTps(getCout()*0.05);
            setTvq((getCout()+getTps())*0.095);
     
            retour = "Commander.xhtml";
            
            
        }
        couttotal = cout + tps + tvq;
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
    
    public String confirmercommande ()
    {
        String Retour ="";
        int insertedKeyValue=0;
        String Erreur="";
        int nbCaisse=0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   //get current date time with Date()
	   Date date = new Date();
	   
           
               try
            {
                
                Connection con;
                Statement st;
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "toor");
                st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                String RequeteSQL= "INSERT INTO bieresfoufoufou.commande(IDMembre, CoutTotal, TPS, TVQ, datecom) values ('1','"+getCout()+"','"+getTps()+"','"+getTvq()+"','"+ dateFormat.format(date) +"')";
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
                con2 = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "toor");
                st2 = con2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                String RequeteSQL= "INSERT INTO bieresfoufoufou.ligne(IDCommande, IDBiere, NbCaisse) values ('"+ insertedKeyValue +"','"+ligneTemp.getIdbiere()+"','"+ligneTemp.getNbcaisse()+"')";
                st2.executeUpdate(RequeteSQL, Statement.RETURN_GENERATED_KEYS);            
                con2.close();
                }
                catch(Exception ex)
                {
                    Erreur = ex.toString();
                }
                try
                {
                Connection con3;
                Statement st3;
                ResultSet rs = null;
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con3 = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "toor");
                st3 = con3.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                rs = st3.executeQuery("Select NombreCaisse from bieresfoufoufou.biere where IDBiere='" + ligneTemp.getIdbiere() + "';");            
                rs.next();
                nbCaisse = rs.getInt("NombreCaisse") - ligneTemp.getNbcaisse();
                con3.close();
                
                Connection con4;
                Statement st4;
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con4 = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "toor");
                st4 = con4.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                String RequeteSQL;
                RequeteSQL = "UPDATE bieresfoufoufou.biere SET NombreCaisse=" + Integer.toString(nbCaisse) + " where IDBiere='" + Integer.toString(ligneTemp.getIdbiere()) + "';";
                st4.executeUpdate(RequeteSQL);            
                con4.close();
                Retour ="Historique.xhtml";
                tBieres.clear();
                m_Lignes.clear();
                setCout(0);
                setTps(0);
                setTvq(0);
                }
                catch(Exception ex)
                {
                    Erreur = ex.toString();
                }
            }
        
        return  Retour;
        
    }
    public String Annule ()
    {
        tBieres.clear();
        m_Lignes.clear();
        setCout(0);
        setTps(0);
        setTvq(0);
        couttotal=0;
        
        return "ListeBeer.xhtml";
    }

    /**
     * @return the couttotal
     */
    public double getCouttotal() {
        return couttotal;
    }

    /**
     * @param couttotal the couttotal to set
     */
    public void setCouttotal(double couttotal) {
        this.couttotal = couttotal;
    }

    /**
     * @return the cout
     */
    public double getCout() {
        return cout;
    }

    /**
     * @param cout the cout to set
     */
    public void setCout(double cout) {
        this.cout = cout;
    }

    /**
     * @return the tps
     */
    public double getTps() {
        return tps;
    }

    /**
     * @param tps the tps to set
     */
    public void setTps(double tps) {
        this.tps = tps;
    }

    /**
     * @return the tvq
     */
    public double getTvq() {
        return tvq;
    }

    /**
     * @param tvq the tvq to set
     */
    public void setTvq(double tvq) {
        this.tvq = tvq;
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
     * @return the m_Commandemem
     */
    public ArrayList getM_Commandemem() 
    {
        String erreur="";
        Boolean bValide=true;
        m_Commandemem = new ArrayList();
         try
        {
        
        ResultSet rs = null;
        Connection con;
        Statement st;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "toor");
        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        rs = st.executeQuery("Select * from bieresfoufoufou.commande where IDMembre='1'");
        while(bValide==true)
            {
               bValide = rs.next();
               if(bValide==true)
                    {
                        m_Commandemem.add(new packageBiere.Commandes(rs.getInt("IDMembre"), 
                                    rs.getInt("IDCommande"), rs.getDouble("CoutTotal"), 
                                    rs.getDouble("TVQ"), rs.getDouble("TPS"), rs.getDate("datecom")));
                    }
             }
        }
        catch(Exception ex)
        {
            erreur = ex.toString();
        }
        
        return m_Commandemem;
    }

    /**
     * @param m_Commandemem the m_Commandemem to set
     */
    public void setM_Commandemem(ArrayList m_Commandemem) {
        this.m_Commandemem = m_Commandemem;
    }


}
