package org.greenpeace.model;

import java.io.Serializable;

public class OrderListDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5159292487673237258L;
	
	
	private Order data;
	String name;
	
	public OrderListDto(Order data , String name){
		super ();
		this.data = data;
		this.name =name;
		
	}
	public OrderListDto(){
		super ();
		
	}
	

	public Order getData() {
		return data;
	}

	public void setData(Order data) {
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	
	
	

}