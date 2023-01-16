package com.example.form;

import javax.validation.constraints.NotBlank;

public class ItemForm {

	@NotBlank(message = "error:may not be empty")
	private String name;
	@NotBlank(message = "error:may not be empty")
	private String price;
	@NotBlank(message = "error:may not be empty")
	private String Category;
	@NotBlank(message = "error:may not be empty")
	private String brand;
	@NotBlank(message = "error:may not be empty")
	private String condition;
	@NotBlank(message = "error:may not be empty")
	private String description;

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

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ItemForm [name=" + name + ", price=" + price + ", Category=" + Category + ", brand=" + brand
				+ ", condition=" + condition + ", description=" + description + "]";
	}

}
