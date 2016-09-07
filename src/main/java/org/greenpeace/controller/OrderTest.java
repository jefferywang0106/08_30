package org.greenpeace.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.greenpeace.dao.ProductDAO;
import org.greenpeace.dao.RestaurantDAO;
import org.greenpeace.model.Product;
import org.greenpeace.model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ManagedBean(name="test")
@RequestScoped


public class OrderTest{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderTest.class);
	
	private Date date10;
	
	public Date getDate10() {
		return date10;
	}


	public void setDate10(Date date10) {
		this.date10 = date10;
	}






	public String text(){
		
		
		LOGGER.debug(new SimpleDateFormat("yyyy-MM-dd").format(date10));
		
		
		return null;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}