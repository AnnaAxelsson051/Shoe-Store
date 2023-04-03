package com.shopme.common.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@Column(unique = true, length = 256, nullable = false )
private String name;

@Column(unique = true, length = 256, nullable = false )
private String alias;

@Column(name = "short_desctription", length = 512, nullable = false )
private String shortDescription;

@Column(name = "full_desctription",length = 4096, nullable = false )
private String fullDescription;

/*
@Column(name = "created_time")
private Date createdTime;

@Column(name = "updated_time")
private Date updatedTime;*/

@Column(name = "created_time", nullable = false, updatable = false)
private Date createdTime;

@Column(name = "updated_time")
private Date updatedTime;

private boolean enabled;

@Column(name = "in_stock")
private boolean inStock;

private float cost;

private float price;

@Column(name = "discount_percent")
private float discountPercent;

private float length;
private float width;
private float height;
private float weight;

//Category_id = foreign key referring to pri key of cat table
@ManyToOne
@JoinColumn(name = "category_id")
private Category category;

//Brand_id is foreign key referring to pri key of brands table
//brand can have one or more products
@ManyToOne
@JoinColumn(name = "brand_id")
private Brand brand;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getAlias() {
	return alias;
}

public void setAlias(String alias) {
	this.alias = alias;
}

public String getShortDescription() {
	return shortDescription;
}

public void setShortDescription(String shortDescription) {
	this.shortDescription = shortDescription;
}

public String getFullDescription() {
	return fullDescription;
}

public void setFullDescription(String fullDescription) {
	this.fullDescription = fullDescription;
}

public Date getCreatedTime() {
	return createdTime;
}

public void setCreatedTime(Date createdTime) {
	this.createdTime = createdTime;
}

public Date getUpdatedTime() {
	return updatedTime;
}

public void setUpdatedTime(Date updatedTime) {
	this.updatedTime = updatedTime;
}

public boolean isEnabled() {
	return enabled;
}

public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}

public boolean isInStock() {
	return inStock;
}

public void setInStock(boolean inStock) {
	this.inStock = inStock;
}

public float getCost() {
	return cost;
}

public void setCost(float cost) {
	this.cost = cost;
}

public float getPrice() {
	return price;
}

public void setPrice(float price) {
	this.price = price;
}

public float getDiscountPercent() {
	return discountPercent;
}

public void setDiscountPercent(float discountPercent) {
	this.discountPercent = discountPercent;
}

public float getLength() {
	return length;
}

public void setLength(float length) {
	this.length = length;
}

public float getWidth() {
	return width;
}

public void setWidth(float width) {
	this.width = width;
}

public float getHeight() {
	return height;
}

public void setHeight(float height) {
	this.height = height;
}

public float getWeight() {
	return weight;
}

public void setWeight(float weight) {
	this.weight = weight;
}

public Category getCategory() {
	return category;
}

public void setCategory(Category category) {
	this.category = category;
}

public Brand getBrand() {
	return brand;
}

public void setBrand(Brand brand) {
	this.brand = brand;
}



}