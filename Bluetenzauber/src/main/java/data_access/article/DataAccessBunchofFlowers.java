package data_access.article;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import transferobjects.BunchOfFlowers;
import transferobjects.Plant;

public class DataAccessBunchofFlowers extends DataAccessArticle_Imple{

	/**
	 * Methode addArtikel
	 * @param newBunchOfFlowers
	 * @throws Exception
	 */
	public void addArticle(BunchOfFlowers newBunchOfFlowers) throws Exception {

		// SQL-Statement definieren
        String sql1= "INSERT INTO artikel (id, bezeichnung, bild, farbe, beschreibung, preis, menge, imAngebot, kategorie) VALUES (?,?,?,?,?,?,?,?,?)"; 
        String sql2= "INSERT INTO blumenstraeusse (id, blumensorte, anlass) VALUES (?,?,?)";   
        
        try {        
                PreparedStatement stmt1= connection.prepareStatement(sql1);
                
                //setzte Id
                stmt1.setInt(1, newBunchOfFlowers.getId());
                //setzte Artikelname
                stmt1.setString(2, newBunchOfFlowers.getName());
                //setzte Preis
                stmt1.setString(3, newBunchOfFlowers.getPicture());
                //setzte Farbe
                stmt1.setString(4, newBunchOfFlowers.getColor());
                //setzte Beschreibung
                stmt1.setString(5, newBunchOfFlowers.getDescription());
                //setzte Preis
                stmt1.setFloat(6, newBunchOfFlowers.getPrice());
                //setzte Menge
                stmt1.setInt(7, newBunchOfFlowers.getAmount());
                //setzte imAngebot
                stmt1.setInt(8, newBunchOfFlowers.getInOffer());
                //setzte Kategorie
                stmt1.setString(9, newBunchOfFlowers.getCategory());
               
                //statement ausfuehren
                stmt1.executeUpdate();
                
                
                PreparedStatement stmt2= connection.prepareStatement(sql2);
                
                //setzte Id
                stmt2.setInt(1, newBunchOfFlowers.getId());
                //setzte Blumensorten
                stmt2.setString(2, newBunchOfFlowers.getKindOfFlower());
                //setzte Anlass
                stmt2.setString(3, newBunchOfFlowers.getEvent());

               
                //statement ausfuehren
                stmt2.executeUpdate();
                
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessArticle_Imple.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

	
	/**
	 * Methode getArtikel
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public BunchOfFlowers getArticle(int id) throws SQLException {
		
		String name = null;
		String picture = null;
		String color = null;
		String description = null;
		float price = 0;
		int amount = 0;
		//1 true, 0 false
		int inOffer = 0;
		String category = null;
		
		String kindOfFlower = null;
		String event = null;
	      
	    BunchOfFlowers bunchOfFlowers;
	    
	    String sql1= "SELECT * FROM artikel WHERE id = ?"; 
	    String sql2= "SELECT * FROM blumenstraeusse WHERE id = ?"; 
	      
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
		  	 kindOfFlower = rs2.getString("blumensorte");
			 event        = rs2.getString("anlass");
	      }
			  
		 bunchOfFlowers = new BunchOfFlowers(id, name, picture, color, description, price, amount, inOffer, category,
				  				kindOfFlower, event);
	          
	      return bunchOfFlowers;
	      
	     }
	      catch(SQLException ex){
	          Logger.getLogger(DataAccessArticle_Imple.class.getName()).log(Level.SEVERE, null, ex);
	     }
	      
		return null;
	}

}
