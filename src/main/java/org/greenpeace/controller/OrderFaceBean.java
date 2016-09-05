package org.greenpeace.controller;


import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.annotation.WebServlet;

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


@ManagedBean(name="orders")
public class OrderFaceBean{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderFaceBean.class);
	
	
	
	
	FacesContext context = FacesContext.getCurrentInstance();
	HttpSession se = (HttpSession) context.getExternalContext().getSession(true);
	
	
	String minDate = "";
	String maxDate = "";
	String timeStr = "";
	
	
	
	
	public String getMinDate() {
		return minDate;
	}




	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}




	public String getMaxDate() {
		return maxDate;
	}




	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}




	public String getTimeStr() {
		return timeStr;
	}




	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}




	public void face() throws IOException{
		LOGGER.debug("123");
		
		
		long h = 3600*1000*1;
		Date date = new Date(System.currentTimeMillis()+h);
	
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		DateFormat tf = new SimpleDateFormat("HH:mm"); 
		
		minDate = df.format(date);
		timeStr = tf.format(date);
		
		String maxStr = "2592000000";
		long max = Long.parseLong(maxStr);
		date = new Date(System.currentTimeMillis()+max);
		maxDate = df.format(date);
		
		Order o = null;
		String oDate = null;
		OrderDAO oDao = new OrderDAO();
		
		Integer rId = 0;
		RestaurantDAO rDao = new RestaurantDAO();
		Restaurant r = null;
		
		ProductDAO pDao = new ProductDAO();
		List<Product> pList = null;
		
		if(se.getAttribute("rId")!=null){
			rId = Integer.valueOf(se.getId());
			r = rDao.getRestaurantById(rId);
			se.setAttribute("rBean", r);
		}else{
			r = (Restaurant)se.getAttribute("rBean");
		}
		
		
		
		
		
		

		
		
		if(se.getAttribute("oId")!=null || se.getAttribute("oSaved")!=null){
			int oId = 0;
			if(se.getAttribute("check") != null){
				o = (Order)se.getAttribute("oBean");
			}else if(se.getAttribute("oId")!=null){
				oId = Integer.parseInt((String)(se.getAttribute("oId")));
				o = oDao.getOrderById(oId);
				ItemDAO iDao = new ItemDAO();
				List<Item> iList =  (ArrayList<Item>)iDao.getItemListByOrderId(oId);
				pList = pDao.createListByItem(iList);
			}else{
				o = (Order)se.getAttribute("oSaved");
				oId = o.getId();
				pList = (ArrayList<Product>)se.getAttribute("newPList");
			}
			oDate = df.format(o.getEndTime());
			timeStr = tf.format(o.getEndTime());
			//判斷修改權限
			if(o.getMemberAccount().equals((String)se.getAttribute("account"))){
				se.setAttribute("oBean", o);
			}else{
				se.setAttribute("oBean", null);
				((HttpServletResponse) se).sendRedirect("menu.jsp");
			}
		}else{
			pList = pDao.getProductByRestaurantId(rId);
			se.setAttribute("oBean", null);
		}
		
		
		if(se.getAttribute("newPList")!=null){
			pList = (ArrayList<Product>)se.getAttribute("newPList");
		}
		if(se.getAttribute("check")!=null){
			pList = (ArrayList<Product>)se.getAttribute("pList");
		}
		
		se.setAttribute("pList", pList);
	
		
		
		
		
	}
	
	
	
	
}