package org.greenpeace.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.greenpeace.dao.ProductDAO;
import org.greenpeace.dao.RestaurantDAO;
import org.greenpeace.model.Product;
import org.greenpeace.model.Restaurant;

@ManagedBean(name = "ordermeal")
@RequestScoped
public class OrderMealCtrl {

	private Restaurant restaurant;
	RestaurantDAO restaurantDao = new RestaurantDAO();
	ProductDAO productDao = new ProductDAO();

	@PostConstruct
	public void showRes() {

		// Request 取得rId
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String rId = request.getParameter("rId");

		if (rId != null) {
			// 用rId取餐廳資料
			restaurant = restaurantDao.getRestaurantById(Integer.parseInt(rId));

			// 用餐廳id取該餐廳產品資料
			List<Product> products = productDao.getProductByRestaurantId(restaurant.getId());
			restaurant.setProducts(products);
		} else {
			return;
		}
	}

	public Restaurant getRestaurant() {

		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

}