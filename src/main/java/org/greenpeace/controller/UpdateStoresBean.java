package org.greenpeace.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import javax.faces.bean.ManagedBean;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.faces.*;

import org.greenpeace.dao.ProductDAO;
import org.greenpeace.dao.RestaurantDAO;
import org.greenpeace.model.Order;
import org.greenpeace.model.Product;
import org.greenpeace.model.Restaurant;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@ManagedBean(name = "updatestores")
public class UpdateStoresBean implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 8330051692518948759L;
	private static final Logger LOGGER = LoggerFactory.getLogger(UpdateStoresBean.class);

	
	
	
	public String updatestore(){

	FacesContext context = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	session.setAttribute("rBean", "r");
	Restaurant r = (Restaurant) session.getAttribute("rBean");

	ProductDAO pDao = new ProductDAO();	
	
	
	
	List<Product> alterPList = new ArrayList<Product>();
	List<Product> newList = new ArrayList<Product>();
	List<Product> oldList = pDao.getProductByRestaurantId(r.getId());

	String[] name = new String[5];
	String[] price = new String[5];

	Product p = null;
	int count=0;

	for(int i = 0; i<name.length ; i++){

		int f = 1;
		p = new Product();

//		name[i] = new String(name[i].getBytes("ISO-8859-1"), "UTF-8");
		p.setName(name[i]);

		// String price =Integer.parseInt([String]);

		int x = Integer.valueOf(price[i]).intValue();

		p.setPrice(x);
		p.setrId(r.getId());
		for (Product rp : oldList) {
			if (p.getName().equals(rp.getName())) {
				p.setId(rp.getId());
				oldList.remove(rp);
				f = 0;
				break;
			}
		}
		if (f == 1) {
			p.setId(pDao.getMaxIdPlus() + count);

			String n = Integer.toString(p.getId());
			LOGGER.debug(n);
			newList.add(p);
			count++;
		}
		alterPList.add(p);
	}

	if(session.getAttribute("oBean")!=null)
	{
		
		Order o = (Order) session.getAttribute("oBean");
		session.setAttribute("oSaved", o);
	}
	
	
	pDao.insertProduct(newList);
	session.setAttribute("newPList",alterPList);
	
	return "order-meal.xhtml";
	

}
	
}



