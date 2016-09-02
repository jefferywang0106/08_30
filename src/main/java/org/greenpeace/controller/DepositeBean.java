package org.greenpeace.controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.greenpeace.dao.DepositDAO;
import org.greenpeace.dao.RestaurantDAO;
import org.greenpeace.model.Deposit;
import org.greenpeace.model.Restaurant;
import org.greenpeace.model.User;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean(name="Deposites" , eager =true)
public class DepositeBean implements Serializable{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DepositeBean.class);
	
	
	FacesContext context = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	
	
	public void test(){
		
		session.getAttribute("accountExist");
		LOGGER.debug("accountExist");
		
		
		
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2781095145764732028L;
	
	

	
	private List<Deposit> list;
	public List<Deposit> queryByMemberAccount() {
		list = new DepositDAO().queryByMemberAccount(account1);
		for(Deposit Dep: list){
			
			
			LOGGER.debug(Integer.toString(Dep.getDepositCash()));
			
		}
		return null;
	}
	
	
	
	
	public List<Deposit> getdlist() {
		return list;
	}
	public void setdlist(List<Deposit> dlist) {
		this.list = dlist;
	}
	
	
	
	

	
			
}