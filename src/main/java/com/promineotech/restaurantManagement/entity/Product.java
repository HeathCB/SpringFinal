package com.promineotech.restaurantManagement.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product { //renamed to product without the "s" at the end (?)

	private Long id;
	private String product_name;
	private double price;
	private String description;
	
	@JsonIgnore
	private Restaurant restaurant;
	
	@JsonIgnore
	private Set<Order> orders;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@ManyToOne
	@JoinColumn(name = "restaurantId")
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "product_order",
			joinColumns = @JoinColumn(name="productId", referencedColumnName ="id" ),
			inverseJoinColumns = @JoinColumn(name="orderId", referencedColumnName ="id"))
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}
