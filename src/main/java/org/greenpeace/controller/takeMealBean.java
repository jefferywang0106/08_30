package org.greenpeace.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.faces.*;

import org.greenpeace.dao.CustomerDAO;
import org.greenpeace.dao.ProductDAO;
import org.greenpeace.dao.RestaurantDAO;
import org.greenpeace.dao.UserDAO;
import org.greenpeace.dao.UserDAOImpl;
import org.greenpeace.dao.ItemDAO;
import org.greenpeace.dao.OrderDAO;
import org.greenpeace.model.Customer;
import org.greenpeace.model.Item;
import org.greenpeace.model.Member;
import org.greenpeace.model.Order;
import org.greenpeace.model.Product;
import org.greenpeace.model.Restaurant;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SessionScoped
@ManagedBean(name="takemeal")
public class takeMealBean{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(takeMealBean.class);

	private List<Order> order ;
	
	public List<Order> getOrder() {
		return order;
	}



	public void setOrder(List<Order> order) {
		this.order = order;
	}




	public void query(){
		
		OrderDAO orderdao = new OrderDAO();
		 
		
		order = orderdao.getAllOrderByStatus();
		
		
		for(Order ord : order){
			SimpleDateFormat test = new SimpleDateFormat("yyyy/MM/dd ");
		
			ord.getId();
			ord.getMemberAccount();
			ord.getRestaurantName();
			ord.getRestaurantPhone();
			ord.getStatus();
			
			LOGGER.debug("null {} :" ,order );

		}
		

		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
