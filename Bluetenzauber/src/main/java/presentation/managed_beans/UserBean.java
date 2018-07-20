package presentation.managed_beans;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import businesslogic.UserManager;
import presentation.servlets.SessionUtils;
import transferobjects.User;


@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	UserManager userManager;
	private User user;
	
	
	//Login und Logout Header
	private String outputTextUser;
	private String buttonLoginLogoutValue;
	//wenn true, User ist angemeldet
	boolean userStatus;
	String errorLogin;
	
	
	//@ManagedProperty(value = "#{shoppingcartBean}")
	//boolean shoppingMode;
	
	public UserBean() {
		
		userManager = new UserManager(); 
		user = new User();
		
		//Login/Logout
		outputTextUser = "Bitte Anmelden";
		buttonLoginLogoutValue = "Login";
		userStatus = false;
		errorLogin = " ";
	}
	
	
	public String saveUser() {
		
		//user = new User(user.getId(),user.getFirstname(),user.getLastname(),user.geteMail(), user.getPassword());
		userManager.addUser(user);
		
		return "registration_finish.xhtml";
		
	}
	
	
	
	// TODO ???
	public void resetRegistrationForm() {
		
	}
	
	/**
	 * Logik für Login/Logout-Button in Header
	 */
	public String buttonLoginLogoutAction() {
		
		if(userStatus) {
			
			//User ist angemeldet
			logout();
			
			//Refresh Header
			return null;
			
		}else {
			
			//User ist NICHT angemeldet
			return  "/pages/login.xhtml?faces-redirect=true";
		
		}
	}
	
	/**
	 * Login
	 * TODO Navigation
	 */
	public String validateUsernamePassword() {
		boolean valid = userManager.validate(user.getUsername(), user.getPassword());
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			//Nur Anmeldename wird in der Session gespeichert
			session.setAttribute("username", user.getUsername());
			//Für Header
			userStatus = true;
			outputTextUser = "Hallo "+ user.getUsername();
			buttonLoginLogoutValue = "Logout";
			//User wollte etwas kaufen und ist jetzt angemeldet
			return "/pages/home.xhtml?faces-redirect=true";
		} else {
			//Anmeldename oder Passwort ist falsch
			
            FacesMessage message = new FacesMessage("Passwort oder Anmeldename ist falsch");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, message);
			return "/pages/login.xhtml";
		}
	}

	/**
	 * Logout
	 * TODO Navigation
	 * @return
	 */
	public void logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		//Für Header
		userStatus = false;
		outputTextUser = "Bitte Anmelden";
		buttonLoginLogoutValue = "Login";
		//Refresh Header
		//return "/pages/header.xhtml?faces-redirect=true";
	}

	
	
	
	//Getter und Setter ************************************
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public String getOutputTextUser() {
		return outputTextUser;
	}


	public void setOutputTextUser(String outputTextUser) {
		this.outputTextUser = outputTextUser;
	}


	public String getButtonLoginLogoutValue() {
		return buttonLoginLogoutValue;
	}


	public void setButtonLoginLogoutValue(String buttonLoginLogoutValue) {
		this.buttonLoginLogoutValue = buttonLoginLogoutValue;
	}


	public String getErrorLogin() {
		return errorLogin;
	}


	public void setErrorLogin(String errorLogin) {
		this.errorLogin = errorLogin;
	}


	public boolean isUserStatus() {
		return userStatus;
	}


	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}
	
	
	
	




}
