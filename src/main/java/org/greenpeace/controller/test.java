package org.greenpeace.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.greenpeace.dao.ProductDAO;
import org.greenpeace.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ManagedBean(name = "pra")
@SessionScoped
public class test {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(test.class);
	ProductDAO productdao = new ProductDAO();
	
	private List<Product> pro;
	
	private Product id;
	private String name ;
	private String price;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public Product getId() {
		return id;
	}


	public void setId(Product id) {
		this.id = id;
	}


	public List<Product> getPro() {
		return pro;
	}


	public void setPro(List<Product> pro) {
		this.pro = pro;
	}




	public String text(){
	
	
	String t  ;  
	
	
	FacesContext fc = FacesContext.getCurrentInstance();
    Map<String,String> params = 
    fc.getExternalContext().getRequestParameterMap();
     t=  params.get("rId"); 
	
     pro = productdao.getProductByRestaurantId(Integer.parseInt(t));
   //for(List的model 宣告for迴圈裡的變數 ： List)
     
     
     
//     name = params.get("name");
//     price = params.get("price");
//     
//     int x = Integer.parseInt(price);
     
     
     
     
     for(Product p : pro){  
     
    	   	 
    	 
    	 LOGGER.debug(p.getName());
     }
     
    System.out.println(t);
    
	return "updaestores.xhtml";
	}
	

	
	
	

}
