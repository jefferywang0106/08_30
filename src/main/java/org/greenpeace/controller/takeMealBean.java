package org.greenpeace.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.greenpeace.dao.CustomerDAO;

import org.greenpeace.dao.OrderDAO;
import org.greenpeace.model.Customer;

import org.greenpeace.model.Order;
import org.greenpeace.model.OrderListDto;
import org.greenpeace.model.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SessionScoped
@ManagedBean(name = "takemeal")
public class takeMealBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(takeMealBean.class);

	private List<Customer> newList = new ArrayList<Customer>();

	OrderDAO oDao = new OrderDAO();
	List<Order> oList = oDao.getAllOrderByStatus();

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
					+ ord.getRestaurantPhone() + "-" + ord.getStatus() + "-" + Integer.toString(ord.getId())));

		}

		for (OrderListDto dto : orderDto) {
			LOGGER.debug(dto.getName());
		}

		return "take-meal.xhtml";
	}

	public void hahaha(ValueChangeEvent e) {

		CustomerDAO cDao = new CustomerDAO();
		List<Customer> cList = cDao.queryAllCustomer();

		LOGGER.debug("new Value:{}" ,e.getNewValue() );
		 if(e.getNewValue()  ==null ||e.getNewValue()  ==e.getOldValue()){
			 return ;
		 }
		String id = e.getNewValue().toString();
		int lid = Integer.parseInt(id);

		for (Customer cus : cList) {

			LOGGER.debug(Integer.toString(cus.getOrderId()));
			if (lid == cus.getOrderId()) {
				newList.add(cus);
				LOGGER.debug(ToStringBuilder.reflectionToString(cus));
				break;
			}

		}

	}

	public List<Customer> getNewList() {
		return newList;
	}

	public void setNewList(List<Customer> newList) {
		this.newList = newList;
	}

}
