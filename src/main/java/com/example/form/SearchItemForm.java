package com.example.form;

public class SearchItemForm {

	private String name;
	private Integer largeCategory;
	private Integer mediumCategory;
	private Integer smallCategory;
	private String brand;

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getLargeCategory() {
		return largeCategory;
	}


	public void setLargeCategory(Integer largeCategory) {
		this.largeCategory = largeCategory;
	}


	public Integer getMediumCategory() {
		return mediumCategory;
	}


	public void setMediumCategory(Integer mediumCategory) {
		this.mediumCategory = mediumCategory;
	}


	public Integer getSmallCategory() {
		return smallCategory;
	}


	public void setSmallCategory(Integer smallCategory) {
		this.smallCategory = smallCategory;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	@Override
	public String toString() {
		return "SearchItemForm [name=" + name + ", largeCategory=" + largeCategory + ", mediumCategory="
				+ mediumCategory + ", smallCategory=" + smallCategory + ", brand=" + brand + "]";
	}

}
