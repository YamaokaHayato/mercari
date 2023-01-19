package com.example.domain;

public class SearchItem {

	private String name;
	private Integer category;
	private String brand;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "SearchItem [name=" + name + ", category=" + category + ", brand=" + brand + "]";
	}

}
