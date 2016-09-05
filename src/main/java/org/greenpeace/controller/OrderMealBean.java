package org.greenpeace.controller;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.greenpeace.dao.ProductDAO;
import org.greenpeace.dao.RestaurantDAO;
import org.greenpeace.model.Product;
import org.greenpeace.model.Restaurant;




@ManagedBean(name="ordermeal")
@RequestScoped
public class OrderMealBean {

	
	private String storeName;

	private Restaurant restaurant ;
	RestaurantDAO restaurantDao = new RestaurantDAO();
	ProductDAO productDao = new ProductDAO();
	
	public Restaurant getRestaurant() {
		
		//Request 取得rId
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String rId = request.getParameter("rId");
		
		//用rId取餐廳資料
		restaurant = restaurantDao.getRestaurantById(Integer.parseInt(rId));
		
		//用餐廳id取該餐廳產品資料
		List<Product> products = productDao.getProductByRestaurantId(restaurant.getId());
		restaurant.setProducts(products);
		
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getStoreName() {
		
		
		
		
		System.out.println("123");
		setStoreName("Test");
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	
}
