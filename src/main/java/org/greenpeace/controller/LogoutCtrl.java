package org.greenpeace.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "logoutCtrl")
public class LogoutCtrl {

	/**
	 * Logout.
	 *
	 * @return the string
	 */
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "index.xhtml";

	}

}