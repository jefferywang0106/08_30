package org.greenpeace.controller;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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


@ManagedBean(name="session")
public session{
	


FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		String checkCode = (String) session.getAttribute("check_code");
		session.setAttribute("accountExist",user.getAccount());
		


}
