package data_access.article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import data_access.h2.DAOFactoryH2;
import transferobjects.Article;


/*TODO
 * Tabelle Category mit name und picture als Tupel
 * TransferObjekt Category
 * getAllCategories()
 */
//implements DataAccessArticle
public class DataAccessArticle_Imple {
	
	//TODO sollte diese Klasse DataAccessH2 heißen und im H2-Package sein?
	Connection connection;
	public DataAccessArticle_Imple()
	{
		connection = DAOFactoryH2.getConnection();
	}

	
	/**
	 * Methode createArticleTables ()
	 * erstellt 3 Tabellen: Artikel, Stauden und Blumensträuße
	 */
	public void createArticleTables() throws Exception {
        String sql1= "CREATE TABLE artikel (    \r\n" + 
        		"    id INT,                    \r\n" + 
        		"    bezeichnung VARCHAR(30),   \r\n" + 
        		"    bild VARCHAR(30),          \r\n" + 
        		"    farbe VARCHAR(30),         \r\n" + 
        		"    beschreibung VARCHAR(255), \r\n" + 
        		"    preis FLOAT,               \r\n" + 
        		"    menge INT,                 \r\n" + 
        		"    imAngebot INT,             \r\n" + 
        		"    kategorie VARCHAR(30),     \r\n" + 
        		")"; 
        
        String sql2= "CREATE TABLE stauden (    \r\n" + 
        		"    id INT,                    \r\n" + 
        		"    standort VARCHAR(30),      \r\n" + 
        		"    wuchshoehe INT,            \r\n" + 
        		"    bluetezeitVon VARCHAR(30), \r\n" + 
        		"    bluetezeitBis VARCHAR(30), \r\n" + 
        		")"; 
        
        String sql3= "CREATE TABLE blumenstraeusse (  \r\n" + 
        		"    id INT,                          \r\n" + 
        		"    blumensorte VARCHAR(30),         \r\n" + 
        		"    anlass VARCHAR(30),              \r\n" + 
        		")"; 
        try {   
        	
            PreparedStatement stmt1= connection.prepareStatement(sql1);
            PreparedStatement stmt2= connection.prepareStatement(sql2);
            PreparedStatement stmt3= connection.prepareStatement(sql3);
    
            //statement ausfuehren
            stmt1.executeUpdate();
            stmt2.executeUpdate();
            stmt3.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessArticle_Imple.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}
	
	/**
	 * Methode deleteArticleTables()
	 * löscht alle 3 Tabellen. 
	 */
	public void deleteArticleTables() throws Exception {
		
        String sql1= "DROP TABLE artikel"; 
        String sql2= "DROP TABLE stauden"; 
        String sql3= "DROP TABLE blumenstraeusse"; 
        
        try {     
        	
            PreparedStatement stmt1= connection.prepareStatement(sql1);
            PreparedStatement stmt2= connection.prepareStatement(sql2);
            PreparedStatement stmt3= connection.prepareStatement(sql3);
    
            //statement ausfuehren
            stmt1.executeUpdate();
            stmt2.executeUpdate();
            stmt3.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessArticle_Imple.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

	/**
	 * Methode getArticles()
	 * @return Liste von Artikeln 
	 * @throws SQLException
	 */
	public List<Article> getArticles() throws SQLException {
		
		int id;
		String name;
		String picture;
		String color;
		String description;
		float price;
		int amount;
		//1 true, 0 false
		int inOffer;
		String category;
	      
	    Article article;
	      
	      //Liste zum speichern aller Article
	      List<Article> allArticles = new LinkedList<Article>();
	      int index=0;
	      
	      String sql= "SELECT * FROM artikel"; 
	      
	      try{
		      PreparedStatement stmt=connection.prepareStatement(sql);
		      
		      ResultSet rs=stmt.executeQuery();
		      
		      //inhalte auslesen
		      while(rs.next()){
		          
		          id           = rs.getInt("id");
		          name         = rs.getString("bezeichnung");
		  		  picture      = rs.getString("bild");
				  color        = rs.getString("farbe");
				  description  = rs.getString("beschreibung");
				  price        = rs.getFloat("preis");
				  amount       = rs.getInt("menge");
				  inOffer      = rs.getInt("imAngebot");
				  category     = rs.getString("kategorie");
				  
		          article = new Article(id, name, picture, color, description, price, amount, inOffer, category);
		          
		          
		          //In Liste einfügen
		          allArticles.add(article);
		       
		      }
		      
		      return allArticles;
	      
	      }
	      catch(SQLException ex){
	          Logger.getLogger(DataAccessArticle_Imple.class.getName()).log(Level.SEVERE, null, ex);
	      }
	      return null;
	}
	
	/**
	 * Methode getArticles()
	 * @return Liste von Artikeln einer Kategorie
	 * @throws SQLException
	 */
	public List<Article> getArticlesFromCategory(String category) throws SQLException {
		
		int id;
		String name;
		String picture;
		String color;
		String description;
		float price;
		int amount;
		//1 true, 0 false
		int inOffer;
	      
	    Article article;
	      
	      //Liste zum speichern aller Article
	      List<Article> allArticles = new LinkedList<Article>();
	      int index=0;
	      
	      String sql= "SELECT * FROM artikel WHERE kategorie = ?"; 
	      
	      try{
		      PreparedStatement stmt=connection.prepareStatement(sql);
		      stmt.setString(1, category);
		      ResultSet rs=stmt.executeQuery();
		      
		      //inhalte auslesen
		      while(rs.next()){
		          
		          id           = rs.getInt("id");
		          name         = rs.getString("bezeichnung");
		  		  picture      = rs.getString("bild");
				  color        = rs.getString("farbe");
				  description  = rs.getString("beschreibung");
				  price        = rs.getFloat("preis");
				  amount       = rs.getInt("menge");
				  inOffer      = rs.getInt("imAngebot");
				  category     = rs.getString("kategorie");
				  
		          article = new Article(id, name, picture, color, description, price, amount, inOffer, category);
		          
		          
		          //In Liste einfügen
		          allArticles.add(article);
		       
		      }
		      
		      return allArticles;
	      
	      }
	      catch(SQLException ex){
	          Logger.getLogger(DataAccessArticle_Imple.class.getName()).log(Level.SEVERE, null, ex);
	      }
	      return null;
	}

	
	/**
	 * Methode boughtArticle
	 * entfernt gekaufte Artikel aus der Datenbank,
	 * indem ihre Menge reduziert wird.
	 * @param boughtArticle
	 * @throws Exception
	 */
	public void removeBoughtArticle(List<Article> boughtArticle) throws Exception {
		
		//Warenkorb durchlaufen
		for (Article currentArticle : boughtArticle) {
			
			//Update Menge in Datenbank für jeden Artikel
			String updateTableSQL = "UPDATE artikel SET menge = ? WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(updateTableSQL);
			preparedStatement.setInt(1,currentArticle.getAmount()-currentArticle.getAmountInCart());
			preparedStatement.setInt(2, currentArticle.getId());
			
			preparedStatement .executeUpdate();
		}
		
	}
	

	/**Search-Funktion
	 * 
	 * @param searchArticle
	 * @return
	 * @throws SQLException
	 */
	public List<Article> searchForArticles(Article searchArticle) throws SQLException {
		
		/*select *
		from table
		where keyword like '%a%'*/
		
		int id;
		String name;
		String picture;
		String color;
		String description;
		float price;
		int amount;
		//1 true, 0 false
		int inOffer;
		String category;
	      
	    Article article;
	      
	      //Liste zum speichern aller Article
	      List<Article> allArticles = new LinkedList<Article>();
	      
	      String sql= "SELECT * FROM artikel WHERE bezeichnung LIKE ? "; 
	      
	      try{
		      PreparedStatement stmt=connection.prepareStatement(sql);
		      stmt.setString(1,"%" + searchArticle.getName() + "%");
		      
		      ResultSet rs=stmt.executeQuery();
		      
		      //inhalte auslesen
		      while(rs.next()){
		          
		          id           = rs.getInt("id");
		          name         = rs.getString("bezeichnung");
		  		  picture      = rs.getString("bild");
				  color        = rs.getString("farbe");
				  description  = rs.getString("beschreibung");
				  price        = rs.getFloat("preis");
				  amount       = rs.getInt("menge");
				  inOffer      = rs.getInt("imAngebot");
				  category     = rs.getString("kategorie");
				  
		          article = new Article(id, name, picture, color, description, price, amount, inOffer, category);
		          
		          
		          //In Liste einfügen
		          allArticles.add(article);
		       
		      }
		      
		      return allArticles;
	      
	      }
	      catch(SQLException ex){
	          Logger.getLogger(DataAccessArticle_Imple.class.getName()).log(Level.SEVERE, null, ex);
	      }
	      return null;
		
		
	
	}
	
	/**
	 * Methode getForOfferArticles()
	 * imAngebot = 1 
	 * @return Liste mit Artikeln im Angebot
	 * @throws SQLException
	 */
	public List<Article> getForOfferArticles() throws SQLException {
		int id;
		String name;
		String picture;
		String color;
		String description;
		float price;
		int amount;
		//1 true, 0 false
		int inOffer;
		String category;
	      
	    Article article;
	      
	      //Liste zum speichern aller Article
	      List<Article> allArticles = new LinkedList<Article>();
	      int index=0;
	      
	      String sql= "SELECT * FROM artikel WHERE imAngebot = 1"; 
	      
	      try{
		      PreparedStatement stmt=connection.prepareStatement(sql);
		      
		      ResultSet rs=stmt.executeQuery();
		      
		      //inhalte auslesen
		      while(rs.next()){
		          
		          id           = rs.getInt("id");
		          name         = rs.getString("bezeichnung");
		  		  picture      = rs.getString("bild");
				  color        = rs.getString("farbe");
				  description  = rs.getString("beschreibung");
				  price        = rs.getFloat("preis");
				  amount       = rs.getInt("menge");
				  inOffer      = rs.getInt("imAngebot");
				  category     = rs.getString("kategorie");
				  
		          article = new Article(id, name, picture, color, description, price, amount, inOffer, category);
		          
		          
		          //In Liste einfügen
		          allArticles.add(article);
		       
		      }
		      
		      return allArticles;
	      
	      }
	      catch(SQLException ex){
	          Logger.getLogger(DataAccessArticle_Imple.class.getName()).log(Level.SEVERE, null, ex);
	      }
	      return null;
	}

}
