package data_access.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import data_access.article.DataAccessArticle_Imple;
import data_access.h2.DAOFactoryH2;
import presentation.managed_beans.UserBean;
import transferobjects.Plant;
import transferobjects.User;

public class DataAccessUser_Imple {
	

	Connection connection;
	public DataAccessUser_Imple()
	{
		connection = DAOFactoryH2.getConnection();
	}
	
	

	/**
	 * Methode createUserTable()
	 * @throws Exception
	 */
	public void createUserTable() throws Exception {
        String sql1= "CREATE TABLE kunden (            \r\n" + 
        		"    id INT NOT NULL AUTO_INCREMENT,   \r\n" + 
        		"    username VARCHAR(30),             \r\n" + 
        		"    vorname VARCHAR(30),              \r\n" + 
        		"    nachname VARCHAR(30),             \r\n" + 
        		"    email VARCHAR(30),                \r\n" + 
        		"    passwort VARCHAR(30),             \r\n" + 
        		")"; 

        try {   
        	
            PreparedStatement stmt1= connection.prepareStatement(sql1);

            //statement ausfuehren
            stmt1.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessArticle_Imple.class.getName()).log(Level.SEVERE, null, ex);
        }
        
	}
	
	/**
	 * Methode deleteUserTable()
	 * löscht kunden-Tabelle
	 */
	public void deleteUserTable() throws Exception {
		
        String sql1= "DROP TABLE kunden"; 
        
        try {     
        	
            PreparedStatement stmt1= connection.prepareStatement(sql1);
    
            //statement ausfuehren
            stmt1.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessArticle_Imple.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}
	
	/**
	 * Methode addUser()
	 * @param newUser
	 * @throws Exception
	 */
	public void addUser(User newUser) throws Exception {
		// SQL-Statement definieren
        String sql1= "INSERT INTO kunden (username, vorname, nachname, email, passwort) VALUES (?,?,?,?,?)"; 
  
        try {        
                PreparedStatement stmt1= connection.prepareStatement(sql1);
                
                //setzte Username
                stmt1.setString(1, newUser.getUsername());
                //setzte Artikelname
                stmt1.setString(2, newUser.getFirstname());
                //setzte Preis
                stmt1.setString(3, newUser.getLastname());
                //setzte Farbe
                stmt1.setString(4, newUser.geteMail());
                //setzte Beschreibung
                stmt1.setString(5, newUser.getPassword());
               
                //statement ausfuehren
                stmt1.executeUpdate();
           
                
            } catch (SQLException ex) {
                Logger.getLogger(DataAccessArticle_Imple.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	
	/**
	 * Methode getUser()
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public User getUser(int id) throws SQLException {
		
		String username;
		String firstname;
		String lastname;
		String eMail;
		String password;
	      
	    User user;
	    
	    String sql1= "SELECT * FROM kunden WHERE id = ?"; 
	      
	    try{
	    	
	      PreparedStatement stmt1=connection.prepareStatement(sql1);
	      //ID von Artikel im SQL setzen
	      stmt1.setInt(1,id);
	      
	      ResultSet rs1=stmt1.executeQuery();
	      
	      //Inhalte aus von Tabelle artikel aus Query lesen  
          id           = rs1.getInt("id");
          username     = rs1.getString("username");
          firstname    = rs1.getString("vorname");
  		  lastname     = rs1.getString("nachname");
		  eMail        = rs1.getString("email");
		  password     = rs1.getString("passwort");

		
			  
		  user = new User(id, username, firstname, lastname, eMail, password);
	          
	      return user;
	      
	     }
	      catch(SQLException ex){
	          Logger.getLogger(DataAccessArticle_Imple.class.getName()).log(Level.SEVERE, null, ex);
	     }
	      
		return null;
	}
	
	/**
	 * Methode validate()
	 * prüft ob sich ein User mit dem Passwort und dem Anmeldenamen
	 * in der Datenbank befindet.
	 * @param user
	 * @param password
	 * @return
	 */
	public boolean validate(String user, String password) {
		
		 String sql1= "Select username, passwort from kunden where username = ? and passwort = ?"; 

		try {
			PreparedStatement stmt1=connection.prepareStatement(sql1);
	
			stmt1.setString(1, user);
			stmt1.setString(2, password);

			ResultSet rs = stmt1.executeQuery();

			if (rs.next()) {
				//Der User existiert in der Datenbank
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} 
		return false;
	}

}
