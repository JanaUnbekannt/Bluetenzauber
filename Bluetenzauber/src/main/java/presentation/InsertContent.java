package presentation;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businesslogic.ArticleManager;
import businesslogic.UserManager;
import presentation.managed_beans.UserBean;
import transferobjects.BunchOfFlowers;
import transferobjects.Plant;
import transferobjects.User;

/**
 * Servlet implementation class Bootstrapping
 */
@WebServlet(urlPatterns ={ "/InsertContent" })
public class InsertContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertContent() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Tabelle mit Artikeln erstellen
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        System.out.println("Tabellen erstellt, Content drin");
        
        ArticleManager articleManager = new ArticleManager();
        UserManager userManager = new UserManager();

       
        //Erstelle Stauden
        Plant plant1 = new Plant (1, "Lavendel",    "Bild","lila","Lavendel passt zu Rosen",    2.00f, 5,0,"Stauden","lockerer Boden",      20, "Juni","Juli");
        Plant plant2 = new Plant (2, "Rose", "       Bild","rot ","Königin der Blumen",        10.00f,10,0,"Stauden","tiefgründiger Boden",150, "Mai","Oktober");
        Plant plant3 = new Plant (3, "Rittersporn", "Bild","blau","Eine wunderschöne Staude",   5.00f,20,0,"Stauden","humoser Boden",      180, "Mai","Juli");
        Plant plant4 = new Plant (4, "Malve",       "Bild","pink","Ideal für die Blumenwiese",  1.00f,50,0,"Stauden","lockerer Boden",     200, "Juni","September");
        Plant plant5 = new Plant (5, "Dahlie",      "Bild","gelb","Ist nicht Winterhart",      20.00f,15,1,"Stauden","humoser Boden",      150, "Juli","September");
        
        //Erstelle Blumensträuße
        BunchOfFlowers bunchOfFlowers1 = new BunchOfFlowers (6, "Blütentraum",     "Bild","bunt","Machen Sie einem geliebten Menschen eine Freude",  2.00f, 5,0,"Blumenstrauss", "Mischung", "Für jeden Anlass");
        BunchOfFlowers bunchOfFlowers2 = new BunchOfFlowers (7, "Happy Birthday",  "Bild","blau","Machen Sie einem geliebten Menschen eine Freude", 10.00f,10,0,"Blumenstrauss", "Tulpen",   "Geburtstag");
        BunchOfFlowers bunchOfFlowers3 = new BunchOfFlowers (8, "Für die Liebste", "Bild","rot", "Machen Sie einem geliebten Menschen eine Freude",  5.00f,20,0,"Blumenstrauss", "Rosen", "Liebe");
        BunchOfFlowers bunchOfFlowers4 = new BunchOfFlowers (9, "Sommerglück",     "Bild","gelb","Machen Sie einem geliebten Menschen eine Freude",  1.00f,50,0,"Blumenstrauss", "Sonnenblumen", "Sommerfeste");
        BunchOfFlowers bunchOfFlowers5 = new BunchOfFlowers (10, "Pretty Woman",    "Bild","pink","Machen Sie einem geliebten Menschen eine Freude", 20.00f,15,1,"Blumenstrauss", "Nelken","Liebe");
        
        //Erstelle User
        User user1 = new User(1, "Hans",  "Peter",  "test@web.de", "123");
        User user2 = new User(2, "Maike", "Bauer",  "test@web.de", "123");
        User user3 = new User(3, "Horst", "Müller", "test@web.de", "123");
        User user4 = new User(4, "Anna",  "Knüll",  "test@web.de", "123");
        User user5 = new User(5, "Piet",  "Driesch","test@web.de", "123");
        
        //Tabellen löschen und erstellen
        articleManager.deleteArticleTables();
        articleManager.createArticleTables();
        
        userManager.deleteUserTable();
        userManager.createUserTable();
        
        //Artikel und Kunden hinzufügen
        articleManager.addPlant(plant1);
        articleManager.addPlant(plant2);
        articleManager.addPlant(plant3);
        articleManager.addPlant(plant4);
        articleManager.addPlant(plant5);
        
        articleManager.addBunchOfFlowers( bunchOfFlowers1);
        articleManager.addBunchOfFlowers( bunchOfFlowers2);
        articleManager.addBunchOfFlowers( bunchOfFlowers3);
        articleManager.addBunchOfFlowers( bunchOfFlowers4);
        articleManager.addBunchOfFlowers( bunchOfFlowers5);
        
        userManager.addUser(user1);
        userManager.addUser(user2);
        userManager.addUser(user3);
        userManager.addUser(user4);
        userManager.addUser(user5);

        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
