package presentation.managed_beans;

import java.io.Serializable;
import java.util.List;

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
	
	String search;
	
	
	public ArticleBean() {
		
		articleManager = new ArticleManager();
		articles = articleManager.allArticles();
		//TODO Set Plant & BunchOfFlower Artikel
		articlesForSale = articleManager.getForOfferArticles();
	}
	
	//Test
	public String test() {
		return "test";
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
	
	
	
	
	
	
	
	




}
