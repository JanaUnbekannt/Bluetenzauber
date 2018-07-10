package businesslogic;

import java.util.List;

import data_access.DAOFactory;
import data_access.article.DataAccessArticle;
import data_access.article.DataAccessArticle_Imple;
import data_access.article.DataAccessBunchofFlowers;
import data_access.article.DataAccessPlant;
import transferobjects.Article;
import transferobjects.BunchOfFlowers;
import transferobjects.Plant;


//TODO Artikel Such-Funktion
//TODO Code duplication von add/getPlant/BunchOfFlowers()
public class ArticleManager {
	
	DataAccessArticle_Imple articleDAO;
	DataAccessPlant plantDAO;
	DataAccessBunchofFlowers bunchOfFlowersDAO;
	
	//Konstruktor
	public ArticleManager() {
		super();
		articleDAO = DAOFactory.getDAOFactory(1).getArticleDAO();
		plantDAO = DAOFactory.getDAOFactory(1).getPlantDAO();
		bunchOfFlowersDAO = DAOFactory.getDAOFactory(1).getBunchOfFlowersDAO();
	}
	
	/**Staude hinzuf�gen*/
	public void addPlant(Plant plant) {
		
		try {
			plantDAO.addArticle(plant);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**Blumenstrau� hinzuf�gen*/
	public void addBunchOfFlowers(BunchOfFlowers bunchOfFlowers ) {
		
		try {
			bunchOfFlowersDAO.addArticle(bunchOfFlowers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/** Tabelle erstellen */
	public void createArticleTables() {
		
		try {
			articleDAO.createArticleTables();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/** Tabelle l�schen */
	public void deleteArticleTables() {

		try {
			articleDAO.deleteArticleTables();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** Liste mit allen Artikeln aus der Datenbank holen */
	public List <Article> allArticles() {
		
		List <Article> list = null;
		
		try {
			list = articleDAO.getArticles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	

	/** Staude aus Datenbank holen */
	public Plant getPlant(int id) {
		
		Plant plant = null;
		
		try {
			plant = plantDAO.getArticle(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return plant;
	}
	
	/** Blumenstrau� aus Datenbank holen */
	public BunchOfFlowers getBunchOfFlowers(int id) {
		
		BunchOfFlowers bunchOfFlowers = null;
		
		try {
			bunchOfFlowers = bunchOfFlowersDAO.getArticle(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bunchOfFlowers;
	}
	
	/** Liste mit Angeboten */
	public List<Article> getForOfferArticles(){
		
		List <Article> list = null;
		
		try {
			list = articleDAO.getForOfferArticles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	/**
	 * Suche Artikel in der Datenbank
	 * @param thisArticle
	 * @return
	 */
	public List<Article> searchArticles(Article thisArticle) {
		List <Article> list = null;
		
		try {
			list = articleDAO.searchForArticles(thisArticle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
