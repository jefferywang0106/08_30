package org.greenpeace.controller;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.greenpeace.dao.ItemDAO;
import org.greenpeace.dao.OrderDAO;
import org.greenpeace.dao.ProductDAO;
import org.greenpeace.dao.RestaurantDAO;
import org.greenpeace.model.Item;
import org.greenpeace.model.Order;
import org.greenpeace.model.Product;
import org.greenpeace.model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "orderMealOrgCtrl")
@RequestScoped
public class OrderMealOriginate {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderMealOriginate.class);

	FacesContext context = FacesContext.getCurrentInstance();// session用
	HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	String account = (String) session.getAttribute("account");// 找要用的session

	private Restaurant restaurant;
	RestaurantDAO restaurantDao = new RestaurantDAO();

	private Order order = new Order();
	OrderDAO oDao = new OrderDAO();
	private Date date10;
	private String rId;

	@PostConstruct
	public void postConstruct() {
		// Request 取得rId
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		// Set<Entry<String, String[]>> keyset =
		// request.getParameterMap().entrySet();
		// for (Entry<String, String[]> unit : keyset) {
		// // 將所有的request的query的變數名稱與值 全部顯示
		// LOGGER.debug("name:{} ,value: {}", unit.getKey(), unit.getValue());
		//
		// }

		String Id = request.getParameter("rId");

		restaurant = restaurantDao.getRestaurantById(Integer.parseInt(Id));

		LOGGER.debug(" Id:{} ", Id);

		LOGGER.debug(" restaurant:{} ", restaurant.getName());

		this.rId = Id;
	}

	public String originate() {

		
	
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date10); 
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		ts = Timestamp.valueOf(dateStr);
		
		
		order.setMemberAccount(account);
		order.setRestaurantId(Integer.valueOf(rId));
		order.setEndTime(ts);
		order.setRestaurantName(restaurant.getName());
		
		oDao.insertOrder(order);
		
		
		LOGGER.debug(rId);
		LOGGER.debug(" endtime :{}", date10);
		return null;
	}

	public String showId() {

		return rId;
	}

	public Date getDate10() {
		return date10;
	}

	public void setDate10(Date date10) {
		this.date10 = date10;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}