package businesslogic;

import data_access.DAOFactory;
import data_access.user.DataAccessUser_Imple;
import presentation.managed_beans.UserBean;
import transferobjects.User;

public class UserManager {

	DataAccessUser_Imple userDAO;

	
	//Konstruktor
	public UserManager() {
		super();
		userDAO = DAOFactory.getDAOFactory(1).getUserDAO();

	}
	
	/** Kunden-Tabelle erstellen*/
	public void createUserTable() {
		
		try {
			userDAO.createUserTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/** Löscht Kunden Tabelle */
	public void deleteUserTable() {
		
		try {
			userDAO.deleteUserTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/** Einen Kunden hinzufügen*/
	public void addUser(User newUser) {
		
		try {
			userDAO.addUser(newUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public User getUser(int id) {
		
		User user = null;
		try {
			user = userDAO.getUser(id);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
		
	}
	
	public boolean validate(String user, String password) {
		
		boolean validateUser = false;
		try {
			validateUser = userDAO.validate(user, password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return validateUser;
		
	}
}
