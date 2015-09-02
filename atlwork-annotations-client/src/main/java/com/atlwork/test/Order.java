package com.atlwork.test;

import com.atlwork.GeneratedTypeAdapter;

@GeneratedTypeAdapter
final class Order {
	private final String itemName;
	private final int price;

	public Order(String itemName, int price) {
		this.itemName = itemName;
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public int getAmount() {
		return price;
	}
}
