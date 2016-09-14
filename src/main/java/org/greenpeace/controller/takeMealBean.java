package org.greenpeace.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.annotation.PostConstruct;
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
import org.greenpeace.model.OrderListDto;
import org.greenpeace.model.Product;
import org.greenpeace.model.Restaurant;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SessionScoped
@ManagedBean(name = "takemeal")
public class takeMealBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(takeMealBean.class);

	CustomerDAO cDao = new CustomerDAO();
	List<Customer> cList = cDao.queryAllCustomer();

	private Order oid = new Order();

	private List<OrderListDto> orderDto = null;

	private List<Order> order;

	public Order getOid() {
		return oid;
	}

	public void setOid(Order oid) {
		this.oid = oid;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public List<OrderListDto> getOrderDto() {
		return orderDto;
	}

	public void setOrderDto(List<OrderListDto> orderDto) {
		this.orderDto = orderDto;
	}

	
	public String query() {

		OrderDAO orderdao = new OrderDAO();

		int id;
		id = oid.getId();

		LOGGER.debug(Integer.toString(id));

		order = orderdao.getAllOrderByStatus();

		orderDto = new LinkedList<OrderListDto>();
		for (Order ord : order) {
			SimpleDateFormat test = new SimpleDateFormat("yyyy/MM/dd ");

			orderDto.add(new OrderListDto(ord, ord.getMemberAccount() + "-" + ord.getRestaurantName() + "-"
					+ ord.getRestaurantPhone() + "-" + ord.getStatus()));

		}

		for (OrderListDto dto : orderDto) {
			LOGGER.debug(dto.getName());
		}
		
		return "take-meal.xhtml";
	}

	public void hahaha(ValueChangeEvent e){

		LOGGER.debug("123");
		LOGGER.debug(e.getNewValue().toString());
		
	
	}

}
