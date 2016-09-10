package org.greenpeace.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.greenpeace.dao.OrderDAO;
import org.greenpeace.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "list")
public class OrderTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderTest.class);

	List<Order> allOrder = new ArrayList<Order>();
	
	
	public List<Order> getAllOrder() {
		return allOrder;
	}


	public void setAllOrder(List<Order> allOrder) {
		this.allOrder = allOrder;
	}

@PostConstruct 
	public void showList() {

		OrderDAO oDao = new OrderDAO();

		
		allOrder = oDao.getOnGoingOrder();

		
		for(Order all:allOrder){
		
		LOGGER.debug(ToStringBuilder.reflectionToString(all));//allOrder資料全部印出
		}
		

	}
	
	
	
	
	

}