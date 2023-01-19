package com.example.form;

public class SearchItemForm {

	private String name;
	private String largeCategory;
	private String mediumCategory;
	private String smallCategory;
	private String brand;

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLargeCategory() {
		return largeCategory;
	}


	public void setLargeCategory(String largeCategory) {
		this.largeCategory = largeCategory;
	}


	public String getMediumCategory() {
		return mediumCategory;
	}


	public void setMediumCategory(String mediumCategory) {
		this.mediumCategory = mediumCategory;
	}


	public String getSmallCategory() {
		return smallCategory;
	}


	public void setSmallCategory(String smallCategory) {
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
