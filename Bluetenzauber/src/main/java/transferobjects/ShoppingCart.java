package transferobjects;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class ShoppingCart implements Serializable {
	
	private float total;
	//private int[] amountInCart;
	private List<Article> shoppingCart;
	
	//Konstruktor
	public ShoppingCart() {
		
		total = 0;
		shoppingCart = new LinkedList<Article>();
		
	}
	

	//Setter und Getter
	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public List<Article> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(List<Article> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}


}
