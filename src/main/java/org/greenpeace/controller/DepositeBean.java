package org.greenpeace.controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Deposit;

public class DepositeBean{
	
	FacesContext context = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	String checkCode = (String) session.getAttribute("accountExist");

	System.out.println()
			
}