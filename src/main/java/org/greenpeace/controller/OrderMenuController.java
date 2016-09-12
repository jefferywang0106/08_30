package org.greenpeace.controller;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.greenpeace.model.Order;
import org.greenpeace.model.Product;
import org.greenpeace.model.ProductDto;
import org.greenpeace.model.Restaurant;
import org.greenpeace.dao.OrderDAO;
import org.greenpeace.dao.ProductDAO;
import org.greenpeace.dao.RestaurantDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "omenu")
@SessionScoped
public class OrderMenuController implements Serializable {

	private static final long serialVersionUID = -5583905550715921385L;
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderMenuController.class);
	private List<Order> allOrder = null;
    private List<Restaurant>allRest =null;
    private List<ProductDto> pro =null;
    private int odcount;
    private String id ;
	

	public String odermenu() {
		FacesContext context = FacesContext.getCurrentInstance();// session用
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

		String account = (String) session.getAttribute("account");
		OrderDAO oDao = new OrderDAO();
		RestaurantDAO rDao = new RestaurantDAO();
		ProductDAO pDao =new ProductDAO();
		
		
		
		 FacesContext fc = FacesContext.getCurrentInstance();
	      Map<String,String> params = 
	      fc.getExternalContext().getRequestParameterMap();
	      id =  params.get("id"); 
	     
	      session.setAttribute("id" , id);
	     
	     
	     LOGGER.debug(id);
		
		allOrder = oDao.getOrderByAccount(account);
		allRest = rDao.getAllRestaurant();
		
		 
		long now = System.currentTimeMillis();
		
		for (Order odmenu : allOrder) {
			if(odmenu.getStatus().equals("進行中")){
				
				
				if(odmenu.getId()==Integer.parseInt(id)){
	
					
					
				
			LOGGER.debug(Integer.toString(odmenu.getId()));
			LOGGER.debug(Integer.toString(odmenu.getRestaurantId()));
			LOGGER.debug(odmenu.getBeginTimeStr());
			LOGGER.debug(odmenu.getEndTimeStr());
			LOGGER.debug(Integer.toString(odmenu.getEndMoney()));
			LOGGER.debug(Integer.toString(odmenu.getMoney()));
		
			for(Restaurant rest: allRest){
				
				if(rest.getId()==odmenu.getRestaurantId()){
					LOGGER.debug(rest.getRType());
					LOGGER.debug(rest.getName());
					 List<Product> proOriginal = pDao.getProductByRestaurantId(odmenu.getRestaurantId());
					 pro = new LinkedList <ProductDto> ();
					 for(Product product : proOriginal){
							LOGGER.debug(product.getName());
							LOGGER.debug(Integer.toString(product.getPrice()));
							pro.add(new ProductDto(product ,0 ));	
							
					}
				}
			
			}
			
			
			
			
			}
			}
		}
		return "addmenu.xhtml";

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Restaurant> getAllRest() {
		return allRest;
	}

	public void setAllRest(List<Restaurant> allRest) {
		this.allRest = allRest;
	}

	public List<ProductDto> getPro() {
		return pro;
	}

	public void setPro(List<ProductDto> pro) {
		this.pro = pro;
	}

	public List<Order> getAllOrder() {
		return allOrder;
	}

	public void setAllOrder(List<Order> allOrder) {
		this.allOrder = allOrder;
	}
	public int getOdcount() {
		return odcount;
	}

	public void setOdcount(int odcount) {
		this.odcount = odcount;
	}
}