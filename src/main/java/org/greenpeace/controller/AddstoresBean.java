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
import org.greenpeace.dao.ItemDAO;
import org.greenpeace.model.Item;
import org.greenpeace.model.Member;
import org.greenpeace.model.Product;
import org.greenpeace.model.Restaurant;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@ManagedBean(name = "addstores")

public class AddstoresBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddstoresBean.class);

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	private Restaurant restaurant = new Restaurant();
	
	private Product product = new Product();
	
	

	private String type;

	private String[] name = new String[5];
	private String[] price = new String[5];



	
	
	
	public String[] getName() {
		return name;
	}

	public void setName(String[] name) {
		this.name = name;
	}

	public String[] getPrice() {
		return price;
	}

	public void setPrice(String[] price) {
		this.price = price;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
	
		
		
		
		
		
		
		if (type.equals("a")) {
			restaurant.setRType("便當");
		} else if (type.equals("b")) {
			restaurant.setRType("速食");
		} else if (type.equals("c")) {
			restaurant.setRType("日式");
		} else {
			restaurant.setRType("點心");
		}

		
		for(int i = 0;i <name.length;i++ ){
			
			LOGGER.debug(name[i]);
			
			
		}
		
		
		
		if (dao.checkR(restaurant.getPhone())) {
			
		
		List<Product> productlist =new ArrayList<Product>();
			for(int i = 0; i < name.length ; i++){
				
				
				Product newproduct = new Product();
				
				
				
				LOGGER.debug("12134");
				
				String productname = name[i];
				String productprice = price[i];
				
				LOGGER.debug(productprice);
				
				
				
				
				if(productname == null || "".equals(productname.trim())){
					continue;
				}
				
				if(productprice == null || "".equals(productprice.trim())){
					continue;
				}
				
				
				newproduct.setName(productname);
				newproduct.setPrice(Integer.parseInt(productprice));
				productlist.add(newproduct);
				
				
		
				
			}
			
			for(Product pro : productlist){
			
			LOGGER.debug(pro.getName());
			}
			
			
			
			Restaurant r = new Restaurant();
			r.setName(restaurant.getName());
			r.setBrief(restaurant.getBrief());
			r.setAddr(restaurant.getAddr());
			r.setPhone(restaurant.getPhone());
			r.setRType(restaurant.getRType());
			r.setProducts(productlist);
			
			dao.addStore(r);
			
			
			

				
			
//				dao.addStore(restaurant);

				System.out.println(restaurant.getName());

		
		} else {
			
			LOGGER.debug("無法新增");

		}

	}
}
