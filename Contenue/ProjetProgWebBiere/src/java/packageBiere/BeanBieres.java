package packageBiere;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.Date;
import javax.faces.context.FacesContext;

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
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "");
            PreparedStatement pst=null;
            ResultSet rs = null;
            String Requete = "Select * from bieresfoufoufou.biere";
            pst = con.prepareStatement(Requete, 1005, 1008);
            pst.clearParameters();
            rs = pst.executeQuery();
            
        
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
        String erreur="";
         try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "");
            PreparedStatement pst=null;
            ResultSet rs = null;
            String Requete = "Select * from bieresfoufoufou.commande";
            pst = con.prepareStatement(Requete, 1005, 1008);
            pst.clearParameters();
            rs = pst.executeQuery();
           
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
            erreur = ex.toString();
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   //get current date time with Date()
	   Date date = new Date();
	   
           
               try
            {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "");
                PreparedStatement pst=null;
                ResultSet rs = null;
                String Requete = "INSERT INTO bieresfoufoufou.commande(IDMembre, CoutTotal, TPS, TVQ, datecom) values (?,?,?,?,?)";
                String Params[] = new String[5];// parce que tout les parameters sont des String...
                    Params[0] = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idmem").toString();
                    Params[1] = Double.toString(getCout()) ;
                    Params[2] = Double.toString(getTps());
                    Params[3] = Double.toString(getTvq());
                    Params[4] = dateFormat.format(date);

                pst = con.prepareStatement(Requete, 1005, 1008);
                pst.clearParameters();
                
                for (int i=0; i < Params.length;i++)
                {
                    pst.setString(i+1, Params[i]);
                }
                
                pst.executeUpdate();
                rs = pst.getGeneratedKeys();
                
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
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "");
                PreparedStatement pst2=null;
                String Requete2 = "INSERT INTO bieresfoufoufou.ligne(IDCommande, IDBiere, NbCaisse) values (?,?,?)";
                String Params2[] = new String[3];// parce que tout les parameters sont des String...
                    Params2[0] = Integer.toString(insertedKeyValue);
                    Params2[1] = Integer.toString(ligneTemp.getIdbiere()) ;
                    Params2[2] = Integer.toString(ligneTemp.getIdbiere());
                    

                pst2 = con2.prepareStatement(Requete2, 1005, 1008);
                pst2.clearParameters();
                
                for (int j=0; j < Params2.length;j++)
                {
                    pst2.setString(j+1, Params2[j]);
                }
                
                pst2.executeUpdate();
                con2.close();
                }
                catch(Exception ex)
                {
                    Erreur = ex.toString();
                }
                try
                {
                  Class.forName("com.mysql.jdbc.Driver").newInstance();
                    Connection con3 = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "");
                    PreparedStatement pst3=null;
                    ResultSet rs3 = null;
                    String Requete3 = "Select NombreCaisse from bieresfoufoufou.biere where IDBiere=?";
                    String Params3 = Integer.toString(ligneTemp.getIdbiere());
                    
                    

                pst3 = con3.prepareStatement(Requete3, 1005, 1008);
                pst3.clearParameters();
                pst3.setString(1, Params3);
                rs3 = pst3.executeQuery();               
                rs3.next();
                nbCaisse = rs3.getInt("NombreCaisse") - ligneTemp.getNbcaisse();
                con3.close();
                
                
               Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con4 = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "");
                PreparedStatement pst4=null;
                String Requete4 = "UPDATE bieresfoufoufou.biere SET NombreCaisse=? where IDBiere=?";
                String Params4[] = new String[2];// parce que tout les parameters sont des String...
                    Params4[0] = Integer.toString(nbCaisse);
                    Params4[1] = Integer.toString(ligneTemp.getIdbiere()) ;
                    

                pst4 = con4.prepareStatement(Requete4, 1005, 1008);
                pst4.clearParameters();
                
                for (int k=0; k < Params4.length;k++)
                {
                    pst4.setString(k+1, Params4[k]);
                }
                
                pst4.executeUpdate();
                
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
        Class.forName("com.mysql.jdbc.Driver").newInstance();
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bieresfoufoufou", "root", "");
                    PreparedStatement pst=null;
                    ResultSet rs = null;
                    String Requete = "Select * from bieresfoufoufou.commande where IDMembre=?";
                    String Params3 = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idmem").toString();
                    
                    

                pst = con.prepareStatement(Requete, 1005, 1008);
                pst.clearParameters();
                pst.setString(1, Params3);
                rs = pst.executeQuery();
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
