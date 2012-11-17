/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package packageBiere;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import java.util.Properties;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
import javax.mail.internet.*;
/**
 *
 * @author Administrateur
 */
@ManagedBean
@SessionScoped
public class BeanMessage {

    String m_Erreur = "";
    private String m_Confirmation="";
    private String m_Sujet="";
    private String m_Message="";
    
    private String stringconnection = "jdbc:mysql://localhost/bieresfoufoufou";
    private String userbd = "root";
    private Connection m_con;
    private String motpassebd="toor";
    /**
     * Creates a new instance of BeanMessage
     */
    public BeanMessage() {
       
    }
     /**
     *
     * @return
     */
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
    
    public String envoyercommunique () throws AddressException, MessagingException
    {

            boolean bValide = true;
            final String username = "bieresfoufoufou@gmail.com";
            final String password = "biere12345";
            Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("bieresfoufoufou@gmail.com"));
                        
                        try
                        {
                            Class.forName("com.mysql.jdbc.Driver").newInstance();
                            Connection con = sconnection();

                            PreparedStatement pst=null;
                            ResultSet rs = null;
                            String Requete = "Select Courriel from bieresfoufoufou.membre";
                            pst = con.prepareStatement(Requete, 1005, 1008);
                            pst.clearParameters();
                            rs = pst.executeQuery();


                            while(bValide==true)
                                {
                                   bValide = rs.next();
                                   if(bValide==true)
                                        {
                                            InternetAddress toAddress = new InternetAddress(rs.getString("Courriel"));
                                            message.addRecipient(Message.RecipientType.TO, toAddress);
                                        }
                                 }
                            }
                        catch(Exception ex)
                        {
                            //out.print(ex.toString());
                        }
                        
//			message.setRecipients(Message.RecipientType.TO,
//				InternetAddress.parse("bieresfoufoufou@gmail.com"));
			message.setSubject(m_Sujet);
			message.setText(m_Message);
 
			Transport.send(message);
                        m_Sujet="";
                        m_Message="";
			
 
		} catch (Exception ex) {
			m_Confirmation = ex.toString();
		}
            return "Message";
    }
    
    public String envoyermessage ()
    {
        boolean bValide = true;
            final String username = "bieresfoufoufou@gmail.com";
            final String password = "biere12345";
            String email="";
            Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
                        InternetAddress toAddress = new InternetAddress("bieresfoufoufou@gmail.com");
                                            message.addRecipient(Message.RecipientType.TO, toAddress);
			message.setFrom(new InternetAddress("bieresfoufoufou@gmail.com"));
                        
                        try
                        {
                            Class.forName("com.mysql.jdbc.Driver").newInstance();
                            Connection con = sconnection();

                            PreparedStatement pst=null;
                            ResultSet rs = null;
                            String Requete = "Select Courriel from bieresfoufoufou.membre where idmembre=?";
                            String Param = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idmem").toString();
                            pst = con.prepareStatement(Requete, 1005, 1008);
                            pst.clearParameters();
                            pst.setString(1, Param);
                            rs = pst.executeQuery();


                            while(bValide==true)
                                {
                                   bValide = rs.next();
                                   if(bValide==true)
                                        {
                                            email = rs.getString("Courriel");
                                            message.setFrom(new InternetAddress(email));
                                        }
                                 }
                            }
                        catch(Exception ex)
                        {
                            //out.print(ex.toString());
                        }
                        
//			message.setRecipients(Message.RecipientType.TO,
//				InternetAddress.parse("bieresfoufoufou@gmail.com"));
			message.setSubject(m_Sujet);
			message.setText(m_Message);
 
			Transport.send(message);
                        m_Sujet="";
                        m_Message="";
			
 
		} catch (Exception ex) {
			m_Confirmation = ex.toString();
		}
            return "Message";
    }

    /**
     * @return the m_Confirmation
     */
    public String getM_Confirmation() 
    {
        return m_Confirmation;
    }

    /**
     * @param m_Confirmation the m_Confirmation to set
     */
    public void setM_Confirmation(String m_Confirmation) 
    {
        this.m_Confirmation = m_Confirmation;
    }

    /**
     * @return the m_Sujet
     */
    public String getM_Sujet() {
        return m_Sujet;
    }

    /**
     * @param m_Sujet the m_Sujet to set
     */
    public void setM_Sujet(String m_Sujet) {
        this.m_Sujet = m_Sujet;
    }

    /**
     * @return the m_Message
     */
    public String getM_Message() {
        return m_Message;
    }

    /**
     * @param m_Message the m_Message to set
     */
    public void setM_Message(String m_Message) {
        this.m_Message = m_Message;
    }
        
}
