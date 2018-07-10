package businesslogic;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import data_access.DAOFactory;
import data_access.DataAccess;
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
	public void calculate(HttpSession httpSession) {
		
		total = 0;
		
		//Hole mir Warenkorb �ber Session
		ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("cart");
		
		//Hole mir Korb
		articleInCart = cart.getShoppingCart();
		
		//Bereche Gesamtwert des Warenkorbs
		float priceArticle = 0;
		for (Article currentArticle : articleInCart) {
			//TODO int vs float
			priceArticle = currentArticle.getPrice() * currentArticle.getAmountInCart();
			//System.out.println("priceArticle "+ priceArticle);
			
			total = total + priceArticle;
			//System.out.println("total "+ total);
		}
		
		//Gesamtsumme im Warenkorb setzten
		cart.setTotal(total);
		
		//Session update
		httpSession.setAttribute("cart", cart);
		
		
	}
	
	
	
	/**
	 * Methode checkout()
	 * die Anzahl von Artikeln im Warenkorb in der Datenbank reduzieren und den Warenkorb zur�cksetzen.
	 */
	public void checkout (HttpSession httpSession) {
		
		DataAccess articleDAO = DAOFactory.getDAOFactory(1).getArticleDAO();
		
		//Hole mir Warenkorb �ber Session
		ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("cart");
		
		//Hole mir Korb
		articleInCart = cart.getShoppingCart();
		
		
		//Gekaufte Artikel in der Datenbank reduzieren
		try {
			articleDAO.removeBoughtArticle(articleInCart);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Warenkorb zur�cksetzten
		
		List <Article> emptyList = new LinkedList <Article> ();
		cart.setShoppingCart(emptyList);
		cart.setTotal(0);
		
		//Session update
		httpSession.setAttribute("cart", cart);
		
	}
	
	/**
	 * Methode addArticleCart
	 * f�gt einen Artikel dem Warenkorb hinzu
	 */
	public boolean addArtikelCart(HttpSession httpSession, int id) {
		
		try {
			
			boolean inCart = false;
			boolean articleAvailable = false;
			
			//Hole mir Warenkorb �ber Session
			ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("cart");
			
			//Hole mir Korb
			articleInCart = cart.getShoppingCart();
			
			//Article article2= null;
			
			//Verbindung Datenbank
			DataAccess articleDAO = DAOFactory.getDAOFactory(1).getArticleDAO();
			
			
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
			
			
			//Pr�fe ob Artikel noch verf�gbar ist
			int amountInCart = newArticle.getAmountInCart();
			int amount = newArticle.getAmount();
			
			if(amount >= amountInCart+1) {
				articleAvailable = true;
			}
		
			//Artikel ist verf�gbar
			if (articleAvailable) {
				
				//Artikel ist im Warenkorb
				if(inCart) {
					
					//erh�he Anzahl der Artikel im Warenkorb
					
					System.out.println("amountInCart "+amountInCart);
					newArticle.setAmountInCart(amountInCart + 1); 
					
				}else {
					
					//Artikel ist noch nicht im Warenkorb
					
					//Artikel in Liste packen
					articleInCart.add(newArticle);
				}
				
				//Update Warenkorb in Object
				cart.setShoppingCart(articleInCart);
				
				//Session update
				httpSession.setAttribute("cart", cart);
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
	 * l�scht einen Artikel aus dem Warenkorb
	 */
	public void removeArtikelCart(HttpSession httpSession, int id) {
		System.out.println("remove********************************************");
		//Hole mir Warenkorb �ber Session
		ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("cart");
		
		//Hole mir Korb
		articleInCart = cart.getShoppingCart();
		
		//Liste durchlaufen
		for (Article currentArticle : articleInCart) {
			
			//Artikel in Warenkorb finden
			if(currentArticle.getId()== id) {
				
				System.out.println("Artikel im Warenkorb gefunden");
				
				//Anzahl im Warenkorb
				int amountInCart = currentArticle.getAmountInCart();
				System.out.println("amountInCart "+amountInCart);
				//Wenn ein Artikel im Warenkorb
				if(amountInCart == 1) {
					System.out.println("l�sche Artikel");
					//l�sche Artikel
					articleInCart.remove(currentArticle);
				}else {
					//veringere Anzahl im Korb
					System.out.println("veringere Anzahl von Artikel");
					currentArticle.setAmountInCart(amountInCart-1);
				}
			}
		}
		
		//Update Warenkorb in Object
		cart.setShoppingCart(articleInCart);
		
		//Session update
		httpSession.setAttribute("cart", cart);
		
	}
	
	


}
