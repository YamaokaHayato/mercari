package com.example.form;

/**
 * 商品の編集内容を受け取るフォーム.
 * 
 * @author yamaokahayato
 *
 */
public class EditItemForm {

	/** 商品名 */
	private String name;
	/** 金額 */
	private String price;
	/** カテゴリー */
	private String Category;
	/** ブランド */
	private String brand;
	/** コンディション */
	private String condition;
	/** 商品説明 */
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
