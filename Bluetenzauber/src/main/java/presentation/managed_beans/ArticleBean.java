package presentation.managed_beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import businesslogic.ArticleManager;
import transferobjects.Article;
import transferobjects.BunchOfFlowers;
import transferobjects.Plant;

@ManagedBean(name = "articleBean")
//Wegen Suche, dass Artikel von Suche nicht verloren gehen
@SessionScoped
//TODO Unterscheiden, welche Kategorie in overview.xhtml dargestellt wird.???
public class ArticleBean implements Serializable {
	
	ArticleManager articleManager;
	
	List <Article> articles;
	List <Article> plantArticles;
	List <Article> bunchOfFlowersArticles;
	List <Article> articlesForSale;
	List <Article> articlesFromSearch;
	
	Plant plant;
	BunchOfFlowers flowers;
	String search;

	
	
	public ArticleBean() {
		
		articleManager = new ArticleManager();
		//articles = articleManager.allArticles();
		//TODO Set Plant & BunchOfFlower Artikel
		articlesForSale = articleManager.getForOfferArticles();
	}
	
	/**
	 * Methode getAllArticlesFromCategory()
	 * Packt alle Artikel einer Category in eine Liste.
	 * Und leitet zur Übersicht weiter.
	 * @param category
	 */
	public String getAllArticlesFromCategory(String category) {
		
		articles = articleManager.allArticlesFromCategory(category);
		return "/pages/overview.xhtml?faces-redirect=true";
	
	}
	
	/**
	 * Methode redirectToDetails()
	 * leitet auf die Detailseite eines Artikels
	 * @param e
	 */
	public String redirectToDetails(int id, String category) {
		
		//Artikel ist eine Staude
		if(category.equals("Stauden")) {
			
			
			plant = articleManager.getPlant(id);
			return "/pages/details_plant.xhtml?faces-redirect=true";
			
		}else {
			//Artikel ist ein Blumenstrauß
			flowers = articleManager.getBunchOfFlowers(id);
			return "/pages/details_bunch_of_flowers.xhtml?faces-redirect=true";
			
		}
		
	
	}
	
	
	// AJAX Listener
	public void searchListener(AjaxBehaviorEvent e){
	
		Article thisArticle = new Article();
		thisArticle.setName(search);
		articlesFromSearch = articleManager.searchArticles(thisArticle);
	}
	
	/*
	 * searchByName()
	 * Führt auf Seite mit Ergebnissen oder
	 * auf No-Result Seite
	 */
	public String searchByName(){
		
		if(articlesFromSearch != null) {
	
			if(articlesFromSearch.isEmpty()) {
				
				//Artikel konnten nicht gefunden werden
				//return "search_no_result.xhtml";
				return "noResult";
				
			}
			
			//Zeige gefundene Artikel an
			//return "search_result.xhtml";
			return "showResult";
		}
		
		return "noResult";

	}

	
	
	
	//Getter und Setter **************************************************
	public List<Article> getArticles() {
		return articles;
	}


	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}


	public List<Article> getPlantArticles() {
		return plantArticles;
	}


	public void setPlantArticles(List<Article> plantArticles) {
		this.plantArticles = plantArticles;
	}


	public List<Article> getBunchOfFlowersArticles() {
		return bunchOfFlowersArticles;
	}


	public void setBunchOfFlowersArticles(List<Article> bunchOfFlowersArticles) {
		this.bunchOfFlowersArticles = bunchOfFlowersArticles;
	}


	public List<Article> getArticlesForSale() {
		return articlesForSale;
	}


	public void setArticlesForSale(List<Article> articlesForSale) {
		this.articlesForSale = articlesForSale;
	}

	public List<Article> getArticlesFromSearch() {
		return articlesFromSearch;
	}

	public void setArticlesFromSearch(List<Article> articlesFromSearch) {
		this.articlesFromSearch = articlesFromSearch;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public BunchOfFlowers getFlowers() {
		return flowers;
	}

	public void setFlowers(BunchOfFlowers flowers) {
		this.flowers = flowers;
	}
	
	
	
	
	
	
	
	
	
	




}
