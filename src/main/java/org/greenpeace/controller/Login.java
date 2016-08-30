package org.greenpeace.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.greenpeace.controller.RandomPassword;
import org.greenpeace.dao.UserDAO;
import org.greenpeace.dao.UserDAOImpl;
import org.greenpeace.model.Member;
import org.greenpeace.model.User;
import org.greenpeace.service.Email02;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class LoginController.
 */
@ManagedBean(name = "loginText")



public class Login{


	
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Login.class);

	/** The user. */
	private User user = new User();
	private String checkout;
	/** The message. */
	private String message;

	public void forgottenPW(ActionEvent actionEvent) {
		LOGGER.debug("username: {}", user.getAccount());
		LOGGER.debug("email: {}", user.getEmail());

		String pw = new RandomPassword().generate();
		LOGGER.debug("random : {}", pw);

		// send email
		new Email02(user.getEmail(), user.getAccount(), pw);

		// reset user data
		this.user = new User();
	}

	/**
	 * Login.
	 *
	 * @return the string
	 */
	public String login() {
		LOGGER.debug("login!!");
		LOGGER.debug("username: {}", user.getAccount());
		LOGGER.debug("password: {}", user.getPassword());
		LOGGER.debug("username: {}", user.getCheckcode());

		boolean userExist = userExist(user);
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		String checkCode = (String) session.getAttribute("check_code");
		session.setAttribute("accountExist",user.getAccount());

		LOGGER.debug("checkCode : {}", checkCode);

		boolean ok = StringUtils.equals(checkCode, user.getCheckcode());
		if (userExist && ok) {
			this.message = "";
			return "service.xhtml";
		} else {
			LOGGER.debug("帳號密碼錯誤，請重新登入");
			this.message = "帳號密碼錯誤，請重新登入";
			return null;
		}
	}

	/**
	 * User exist. 若使用者存在資料庫則驗證成功
	 * 
	 * @param user
	 *            the user
	 * @return true, if successful
	 */
	protected boolean userExist(User user) {
		UserDAO dao = new UserDAOImpl();

		Member member = dao.getUserByAccount(user.getAccount(), user.getPassword());
		if (member != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Logout.
	 *
	 * @return the string
	 */
	public String logout() {
		LOGGER.debug("login!!");
		LOGGER.debug("username: {}", user.getAccount());
		LOGGER.debug("password: {}", user.getPassword());

		return null;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message
	 *            the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

}
