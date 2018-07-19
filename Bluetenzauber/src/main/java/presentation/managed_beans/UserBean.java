package presentation.managed_beans;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
	private UIComponent loginButton;

	
	public UserBean() {
		userManager = new UserManager(); 
		user = new User();
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
	 * Login
	 * TODO Navigation
	 */
	public String validateUsernamePassword() {
		boolean valid = userManager.validate(user.getUsername(), user.getPassword());
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			//Nur Anmeldename wird in der Session gespeichert
			session.setAttribute("username", user.getUsername());
			return "/pages/home.xhtml?faces-redirect=true";
		} else {
			//Anmeldename oder Passwort ist falsch
			/*TODO Bug
            FacesMessage message = new FacesMessage("Passwort oder Anmeldename ist falsch");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginbutton", message);*/
			return  "/pages/login.xhtml";
		}
	}

	/**
	 * Logout
	 * TODO Navigation
	 * @return
	 */
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
	
	
	
	//Getter und Setter ************************************
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
    public void setMybutton(UIComponent mybutton) {
        this.loginButton = mybutton;
    }

    public UIComponent getMybutton() {
        return loginButton;
    }
	


}
