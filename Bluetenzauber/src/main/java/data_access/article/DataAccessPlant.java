package data_access.article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import data_access.h2.DAOFactoryH2;
import transferobjects.Plant;

//TODO implements DataAccessArticle
public class DataAccessPlant extends DataAccessArticle_Imple {
	
	//Get Connection 
	Connection connection;
	public DataAccessPlant()
	{
		connection = DAOFactoryH2.getConnection();
	}
	
	
	/**
	 * Methode addArticle()
	 * @param newPlant
	 * @throws Exception
	 */
	public void addArticle(Plant newPlant) throws Exception {
		// SQL-Statement definieren
        String sql1= "INSERT INTO artikel (id, bezeichnung, bild, farbe, beschreibung, preis, menge, imAngebot, kategorie) VALUES (?,?,?,?,?,?,?,?,?)"; 
        String sql2= "INSERT INTO stauden (id, standort, wuchshoehe, bluetezeitVon, bluetezeitBis) VALUES (?,?,?,?,?)";   
        
        try {        
                PreparedStatement stmt1= connection.prepareStatement(sql1);
                
                //setzte Id
                stmt1.setInt(1, newPlant.getId());
                //setzte Artikelname
                stmt1.setString(2, newPlant.getName());
                //setzte Preis
                stmt1.setString(3, newPlant.getPicture());
                //setzte Farbe
                stmt1.setString(4, newPlant.getColor());
                //setzte Beschreibung
                stmt1.setString(5, newPlant.getDescription());
                //setzte Preis
                stmt1.setFloat(6, newPlant.getPrice());
                //setzte Menge
                stmt1.setInt(7, newPlant.getAmount());
                //setzte imAngebot
                stmt1.setInt(8, newPlant.getInOffer());
                //setzte Kategorie
                stmt1.setString(9, newPlant.getCategory());
               
                //statement ausfuehren
                stmt1.executeUpdate();
                
                
                PreparedStatement stmt2= connection.prepareStatement(sql2);
                
                //setzte Id
                stmt2.setInt(1, newPlant.getId());
                //setzte Standort
                stmt2.setString(2, newPlant.getLocation());
                //setzte Wuchshöhe
                stmt2.setInt(3, newPlant.getIncreasedHeight());
                //setzte Blütezeit von
                stmt2.setString(4, newPlant.getFloweringOf());
                //setzte Blütezeit bis
                stmt2.setString(5, newPlant.getFloweringTo());
               
                //statement ausfuehren
                stmt2.executeUpdate();
               
                
            } catch (SQLException ex) {
                Logger.getLogger(DataAccessArticle_Imple.class.getName()).log(Level.SEVERE, null, ex);
            }
		
		
	}
	
	
	/**
	 * Methode getArticle()
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Plant getArticle(int id) throws SQLException {
		
		System.out.println("*************************************");
		System.out.println("Methodde getArticle in Plant");
		
		String name = null;
		String picture = null;
		String color = null;
		String description = null;
		float price = 0;
		int amount = 0;
		//1 true, 0 false
		int inOffer = 0;
		String category = null;
		
		String location = null;
		int increasedHeight = 0;
		String floweringOf = null;
		String floweringTo = null;
	      
	    Plant plant;
	    
	    String sql1= "SELECT * FROM artikel WHERE id = ?"; 
	    String sql2= "SELECT * FROM stauden WHERE id = ?"; 
	      
	    try{
	    	
	    	
	      PreparedStatement stmt1=connection.prepareStatement(sql1);
	      //ID von Artikel im SQL setzen
	      stmt1.setInt(1,id);
	      
	      ResultSet rs1=stmt1.executeQuery();
	      
	      while(rs1.next()){
	      
		      //Inhalte aus von Tabelle artikel aus Query lesen  
	          id           = rs1.getInt("id");
	          name         = rs1.getString("bezeichnung");
	  		  picture      = rs1.getString("bild");
			  color        = rs1.getString("farbe");
			  description  = rs1.getString("beschreibung");
			  price        = rs1.getFloat("preis");
			  amount       = rs1.getInt("menge");
			  inOffer      = rs1.getInt("imAngebot");
			  category     = rs1.getString("kategorie");
	      }
			  
	     
	      PreparedStatement stmt2=connection.prepareStatement(sql2);
	      //ID von Artikel im SQL setzen
	      stmt2.setInt(1,id);
	      
	      ResultSet rs2=stmt2.executeQuery();
	      
	      while(rs2.next()){
	      
		     //Inhalte aus von Tabelle artikel aus Query lesen    
	          id              = rs2.getInt("id");
	  		  location        = rs2.getString("standort");
			  increasedHeight = rs2.getInt("wuchshoehe");
			  floweringOf     = rs2.getString("bluetezeitVon");
			  floweringTo     = rs2.getString("bluetezeitBis");
	      }
			  
		  plant = new Plant(id, name, picture, color, description, price, amount, inOffer, category,
				  				location, increasedHeight, floweringOf, floweringTo);
  
	      return plant;
	      
	     }
	      catch(SQLException ex){
	          Logger.getLogger(DataAccessArticle_Imple.class.getName()).log(Level.SEVERE, null, ex);
	     }
	      
		return null;
	}



}
