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

import org.greenpeace.dao.RestaurantDAO;
import org.greenpeace.dao.UserDAO;
import org.greenpeace.dao.UserDAOImpl;
import org.greenpeace.model.Member;
import org.greenpeace.model.Product;
import org.greenpeace.model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class AddServlet
 */

@ManagedBean(name = "addstores")




public class AddstoresBean {
	
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
	        request.setCharacterEncoding("UTF-8");
	        chain.doFilter(request, response);
}
	private Restaurant restaurant = new Restaurant();
	
	private String type ;

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

	//
	// String Name = ("Name");
	// String Brief = ("Brief");
	// String Addr = ("Addr");
	// String Phone = ("Phone");
	// String RType = ("Rtype");
	//
	// RestaurantDAO dao = new RestaurantDAO();
	//
	// if(dao.checkR(Phone)){
	// String[] productNames = request.getParameterValues("productName");
	// String[] prices = request.getParameterValues("price");
	//
	// List<Product> productList = new ArrayList<Product>();
	// for(int i = 0 ; i < productNames.length ; i++){
	//
	// String productName = productNames[i];
	// String price = prices[i];
	//
	// Product product = new Product();
	//
	// if(productName == null || "".equals(productName.trim())){
	// continue;
	// }
	//
	// if(price == null || "".equals(price.trim())){
	// continue;
	// }
	//
	// product.setName(productName);
	// product.setPrice(Integer.parseInt(price));
	// productList.add(product);
	// }
	//
	//
	// Restaurant r = new Restaurant();
	// r.setName(Name);
	// r.setBrief(Brief);
	// r.setAddr(Addr);
	// r.setPhone(Phone);
	// r.setRType(RType);
	// r.setProducts(productList);
	//
	// dao.addStore(r);
	// response.sendRedirect("order.jsp");
	//
	//
	// }
	//
	//
	//
	// }

}
