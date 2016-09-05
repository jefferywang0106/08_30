package org.greenpeace.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.annotation.WebServlet;

import org.greenpeace.dao.ItemDAO;
import org.greenpeace.dao.OrderDAO;
import org.greenpeace.model.Item;
import org.greenpeace.model.Order;
import org.greenpeace.model.Product;
import org.greenpeace.model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class OrderBean{
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderBean.class);

FacesContext context = FacesContext.getCurrentInstance();
HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

private String info;
private String endTime;
private String endDate;



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



public void order() throws IOException{
	
		Order o = null;
		OrderDAO oDao = new OrderDAO();
		if(session.getAttribute("oBean")!=null)
			o =(Order)session.getAttribute("oBean");
		else
			o = new Order();
		
		Item item = null;
		ItemDAO iDao = new ItemDAO();
		List<Item> iList = new ArrayList<Item>();
		
		
		
		
		Restaurant r = (Restaurant)session.getAttribute("rBean"); 
		int endMoney = 0;
		
		//判斷格式是否正確
		String url = "order-meal.jsp?";
		boolean mCheck = false;
		session.setAttribute("rRemain", r.getId());
		session.setAttribute("infoRemain", info);
		try{
			endMoney = Integer.parseInt((String)session.getAttribute("endMoney"));
			if(endMoney<=0)throw new Exception();
		}catch(Exception e){
			mCheck = true;
			session.setAttribute("endMoneyCheck",session.getAttribute("endMoney") );
			url = url + "mCheck=1&";
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String dateAndTime = endDate+" "+endTime+":00";
		Date date = null;
		try {
			date = df.parse(dateAndTime);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Time parsed Error");
		}
		
		long endTimeLong = date.getTime();
		boolean tCheck = false;
		if((endTimeLong-System.currentTimeMillis())<0){
			tCheck = true;
			url = url + "tCheck=1&";
		}
		if(tCheck || mCheck){
				session.setAttribute("endTimeRemain",endTime);
				session.setAttribute("endDateRemain", endDate);
				session.setAttribute("endMoneyRemain", endMoney);
			if(session.getAttribute("oBean")!=null)
				((HttpServletResponse) session).sendRedirect(url+"check=1&oId="+String.valueOf(o.getId()));
			else
				((HttpServletResponse) session).sendRedirect(url+"check=1");
		}else{
			session.setAttribute("rRemain",null);
			//int rId = (int)se.getAttribute("rId"); 
			o.setEndTime(Timestamp.valueOf(dateAndTime));
			o.setEndMoney(endMoney);
			o.setMemberAccount((String)session.getAttribute("account"));
			o.setInfo(info);
			o.setRestaurant(r);	
		
			//o.setRestaurantId(rId);
			
			if(session.getAttribute("oBean")!=null){
				oDao.updateOrder(o);
				List<Product> pList = (ArrayList<Product>)session.getAttribute("pList");
				for(Product p:pList){
					item = new Item();
					item.setProduct(p);
					item.setOrderId(o.getId());
					iList.add(item);
				}
				iDao.updateItemByOrderId(iList, o.getId());
				session.setAttribute("oBean", null);
				session.setAttribute("rBean", null);
				session.setAttribute("pList",null);
			    System.out.println("已修改訂單");
			    ((HttpServletResponse) session).sendRedirect("menu.jsp");
			}else{
				o.setBeginTime();
				oDao.insertOrder(o);
				List<Product> pList = (ArrayList<Product>)session.getAttribute("pList");
				for(Product p:pList){
					item = new Item();
					item.setProduct(p);
					item.setOrderId(oDao.getMaxId());
					iList.add(item);
				}
				iDao.insertItemList(iList);	
				session.setAttribute("oBean", null);
				session.setAttribute("rBean", null);
				session.setAttribute("pList",null);
			    System.out.println("已發起訂單");
		
			    ((HttpServletResponse) session).sendRedirect("order.jsp");
			}
			
		}
		
}
	}

