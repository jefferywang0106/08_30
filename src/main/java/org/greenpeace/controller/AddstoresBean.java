package org.greenpeace.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.faces.*;

import org.greenpeace.dao.ProductDAO;
import org.greenpeace.dao.RestaurantDAO;
import org.greenpeace.dao.UserDAO;
import org.greenpeace.dao.UserDAOImpl;
import org.greenpeace.model.Member;
import org.greenpeace.model.Product;
import org.greenpeace.model.Restaurant;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class AddServlet
 */

@ManagedBean(name = "addstores")

public class AddstoresBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddstoresBean.class);

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	private Restaurant restaurant = new Restaurant();

	private String type;
	
	private String[]product = new String[5] ; 

	
	

	public String[] getProduct() {
		return product;
	}

	public void setProduct(String[] product) {
		this.product = product;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	
	
	
	public void addproduct(){
		
		
for(int i=0;i<product.length;i++){
			
			LOGGER.debug(product[i]);
			
			
		}
		
	
		
	ProductDAO dao1 =new ProductDAO();
	
		dao1.insertProduct(product);
		
	}
	
	
	
	public void stores() {
      
			
	
		
		
		RestaurantDAO dao = new RestaurantDAO();

		System.out.println(type);
		
		 if(type.equals("a")){
		 restaurant.setRType("便當");
		 }else if(type.equals("b")){
		 restaurant.setRType("速食");
		 }else if(type.equals("c")){
		 restaurant.setRType("日式");
		 }else{
		 restaurant.setRType("點心");
		 }

		 
		
		 
		dao.addStore(restaurant);

		System.out.println(restaurant.getName());

	}

	
}
