package org.greenpeace.controller;

import org.greenpeace.dao.RestaurantDAO;
import org.greenpeace.model.Product;
import org.greenpeace.model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@ManagedBean(name="update")

public class updatestores {
	
	
	private List<Product>  plist;
	
	private String newpro;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(updatestores.class);
	
	
	
	@PostConstruct
	
	public String updateproduct(){

		
		
		
		
//		FacesContext fc = FacesContext.getCurrentInstance();
//	      Map<String, String> params = 
//	      fc.getExternalContext().getRequestParameterMap();
//	      newpro =  params.get("productname");
		  
	
		
		
		
		
		
	      
	      
	      
	      
	      
		
		
		return "order.xhtml";
		
	}
	
	
	
	
	
	
	
	
			}
	


