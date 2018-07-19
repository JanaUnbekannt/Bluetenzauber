package presentation.managed_beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import businesslogic.ShoppingCartManager;
import presentation.servlets.SessionUtils;
import transferobjects.Article;
import transferobjects.ShoppingCart;


@ManagedBean(name = "shoppingcartBean")
@SessionScoped
public class ShoppingCartBean implements Serializable {
	
	private ShoppingCartManager cartManager;
 	private ShoppingCart cart;
 	private List<Article>articleList;
 	private String total;
 	
 	//User angemeldet?
 	private boolean userStatus = false;
 	//HttpSession session;
 	
	public ShoppingCartBean() {

	   	cartManager  = new ShoppingCartManager();
	   	cart = new ShoppingCart();
	   	articleList = cart.getShoppingCart();
	   	total = "0";
	   	//session = SessionUtils.getSession();
	   
	}
	
	/**
	 * Entfernt einen Artikel vom Warenkorb
	 * @param id
	 * @return
	 */
	public String removeArticleFromCart(int id) {
		cartManager.removeArtikelCart(cart, id);
		articleList = cart.getShoppingCart();
		calculate();
		
		return null;
	}
	
	/**
	 * Füge einen Artikel dem Warenkorb hinzu
	 * @param id
	 */
	public void addArticleCart(int id) {
		//TODO Benachrichtigung ->Hinzugefügt oder ausverkauft
		cartManager.addArtikelCart(cart, id);
		articleList = cart.getShoppingCart();
		calculate();
	}
	
	public String buyArticles() {
		
		cartManager.checkout(cart);
		calculate();
		//reset List
		articleList.clear();   
		
		

		return "/pages/finish_shopping.xhtml?faces-redirect=true";

	}
	
	/**
	 * Berechnet die Gesamtsumme
	 */
	public void calculate(){
		
		total = String.valueOf(cartManager.calculate(cart));
		
		
	}


	//Getter and Setter *****************************************
	public ShoppingCart getCart() {
		return cart;
	}


	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}


	public List<Article> getArticleList() {
		return articleList;
	}


	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}


}
