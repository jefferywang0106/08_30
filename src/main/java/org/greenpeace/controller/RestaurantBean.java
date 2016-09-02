package org.greenpeace.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.greenpeace.dao.RestaurantDAO;
import org.greenpeace.model.Restaurant;

@ManagedBean(name = "restaurantBean", eager = true)
@RequestScoped
public class RestaurantBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4474126832280398966L;
	private List<Restaurant> rlist;
	public List<Restaurant> allRestaurant() {
		rlist = new RestaurantDAO().getAllRestaurant();
		for(int i=0;i<rlist.size();i++){
		System.out.println(rlist.get(i).getName());
		}
		return null;
	}
	
	
	
	
	
	
	public List<Restaurant> getRlist() {
		return rlist;
	}
	public void setRlist(List<Restaurant> rlist) {
		this.rlist = rlist;
	}



}