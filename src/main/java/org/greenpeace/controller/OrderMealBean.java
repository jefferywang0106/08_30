package org.greenpeace.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "ordermeal")
@RequestScoped
public class OrderMealBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderMealBean.class);

	private Restaurant restaurant;
	RestaurantDAO restaurantDao = new RestaurantDAO();
	ProductDAO productDao = new ProductDAO();

	private String info;
	private String endTime;
	private String endDate;
	private Date date10;

	public Date getDate10() {
		return date10;
	}

	public void setDate10(Date date10) {
		this.date10 = date10;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@PostConstruct

	public void test() {

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

		// Request 取得rId

		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	// public String getStoreName() {
	//
	// return storeName;
	// }
	//
	// public void setStoreName(String storeName) {
	// this.storeName = storeName;
	// }

}
