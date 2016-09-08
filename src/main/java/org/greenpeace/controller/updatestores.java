package org.greenpeace.controller;

import org.greenpeace.dao.ProductDAO;
import org.greenpeace.dao.RestaurantDAO;
import org.greenpeace.model.Item;
import org.greenpeace.model.Product;
import org.greenpeace.model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scala.annotation.meta.setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.greenpeace.dao.ProductDAO;
import org.greenpeace.model.Product;



@ManagedBean(name="update")
@SessionScoped

public class updatestores {


	private List<Product>  plist ;
	private static final Logger LOGGER = LoggerFactory.getLogger(updatestores.class);


	
	ProductDAO productdao = new ProductDAO();
	

	

	private String name ;
	private String price;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}



	
	
	public String updateproduct(){

	LOGGER.debug("null: {}",plist);
		    
			//get all existing value but set "editable" to false 
			for (Product order : plist){
				order.setEditable(false);
			}
			
			//return to order.xhtml page
			return "order.xhtml";
			
		}
		
		public String editAction(Product order) {
		    
			LOGGER.debug("123");
			
			order.setEditable(true);
			return null;
		}
	 
		
			}
	


