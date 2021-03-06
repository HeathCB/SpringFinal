package com.promineotech.restaurantManagement.util;

public enum Level {

	SILVER(.05),
	GOLD(.10),
	DIAMOND(.15),
	PLATINUM(.20);
	
	private double discount;
	
	Level(double discount) {
		this.discount = discount;
	}
	
	public double getDiscount() {
		return discount;
	}
}
