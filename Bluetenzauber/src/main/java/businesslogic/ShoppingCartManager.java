package businesslogic;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import data_access.DAOFactory;
import data_access.article.DataAccessArticle_Imple;
import transferobjects.Article;
import transferobjects.ShoppingCart;

public class ShoppingCartManager {
	
	List <Article> articleInCart = null;
	float total;
	
	
	/**
	 * Methode calculate()
	 * die Gesamtsumme aller Artikel eines Warenkorbs berechnen und
	 * diese dann im Warenkorb-Objekt aktualisieren.
	 */
	public float calculate(ShoppingCart cart) {
		
		total = 0;
	
		//Hole mir Korb
		articleInCart = cart.getShoppingCart();
		
		//Bereche Gesamtwert des Warenkorbs
		float priceArticle = 0;
		for (Article currentArticle : articleInCart) {
	
			priceArticle = currentArticle.getPrice() * currentArticle.getAmountInCart();
		
			total = total + priceArticle;
			
		}
		
		//Gesamtsumme im Warenkorb setzten
		cart.setTotal(total);
		
		return total;
		
	}
	
	
	
	/**
	 * Methode checkout()
	 * die Anzahl von Artikeln im Warenkorb in der Datenbank reduzieren und den Warenkorb zurücksetzen.
	 */
	public void checkout (ShoppingCart cart) {
		
		DataAccessArticle_Imple articleDAO = DAOFactory.getDAOFactory(1).getArticleDAO();
		
		//Hole mir Korb
		articleInCart = cart.getShoppingCart();
		
		
		//Gekaufte Artikel in der Datenbank reduzieren
		try {
			articleDAO.removeBoughtArticle(articleInCart);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Warenkorb zurücksetzten
		
		List <Article> emptyList = new LinkedList <Article> ();
		cart.setShoppingCart(emptyList);
		cart.setTotal(0);
		
	}
	
	/**
	 * Methode addArticleCart
	 * fügt einen Artikel dem Warenkorb hinzu
	 */
	public boolean addArtikelCart(ShoppingCart cart, int id) {
		
		try {
			
			boolean inCart = false;
			boolean articleAvailable = false;
			
			//Hole mir Korb
			articleInCart = cart.getShoppingCart();
			
			
			//Verbindung Datenbank
			DataAccessArticle_Imple articleDAO = DAOFactory.getDAOFactory(1).getArticleDAO();
			
			
			//Artikel aus Datenbank
			Article newArticle = articleDAO.getArticle(id);
			
			//Schaue, ob Artikel schon im Warenkorb ist
			for (Article currentArticle : articleInCart) {
				
				//Artikel ist schon im Warenkorb
				if(currentArticle.getId()==id) {
					
					inCart = true;
					newArticle = currentArticle;
					
				}
			}
			
			
			//Prüfe ob Artikel noch verfügbar ist
			int amountInCart = newArticle.getAmountInCart();
			int amount = newArticle.getAmount();
			
			if(amount >= amountInCart+1) {
				articleAvailable = true;
			}
		
			//Artikel ist verfügbar
			if (articleAvailable) {
				
				//Artikel ist im Warenkorb
				if(inCart) {
					
					//erhöhe Anzahl der Artikel im Warenkorb
					
					newArticle.setAmountInCart(amountInCart + 1); 
					
				}else {
					
					//Artikel ist noch nicht im Warenkorb
					
					//Artikel in Liste packen
					articleInCart.add(newArticle);
				}
				
				//Update Warenkorb in Object
				cart.setShoppingCart(articleInCart);
				
			}
			
			return articleAvailable;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	 
	
	/**
	 * Methode removeArticleCart
	 * löscht einen Artikel aus dem Warenkorb
	 */
	public void removeArtikelCart(ShoppingCart cart, int id) {
		
		//Hole mir Korb
		articleInCart = cart.getShoppingCart();
		
		//Liste durchlaufen
		for (Article currentArticle : articleInCart) {
			
			//Artikel in Warenkorb finden
			if(currentArticle.getId()== id) {
				
				//Anzahl im Warenkorb
				int amountInCart = currentArticle.getAmountInCart();
	
				//Wenn ein Artikel im Warenkorb
				if(amountInCart == 1) {
			
					//lösche Artikel
					articleInCart.remove(currentArticle);
				}else {
					//veringere Anzahl im Korb
					currentArticle.setAmountInCart(amountInCart-1);
				}
			}
		}
		
		//Update Warenkorb in Object
		cart.setShoppingCart(articleInCart);
		
		
	}
	
	

}
