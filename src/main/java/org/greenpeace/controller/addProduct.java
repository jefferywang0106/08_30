package org.greenpeace.controller;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang.StringUtils;
import org.greenpeace.controller.RandomPassword;
import org.greenpeace.dao.ProductDAO;
import org.greenpeace.model.Product;
import org.primefaces.context.RequestContext;
import com.lowagie.text.List;

@ManagedBean(name = "product")

public class addProduct {

	

	private Product product =new Product() ;
	
	
	
	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	public void prducts(){
		
		ProductDAO dao = new ProductDAO();
		
		dao.insertproduct(product);
		
	}
	
	
	
	

}