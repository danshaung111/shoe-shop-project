package com.mmit.shop.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity
@NamedQuery(name = "Product.findAll",query="SELECT p FROM Product p ORDER BY p.created_at DESC")
@NamedQuery(name = "Product.findPhotoById",query="SELECT p.photo FROM Product p WHERE p.id = :pId")
@NamedQuery(name = "Product.findByNameBrandCategory",query="SELECT p FROM Product p WHERE p.brand.id = :bId AND p.category.id = :cId AND p.name = :pname")
public class Product implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	private int price;
	@Column(columnDefinition = "TEXT")
	private String productDetails;
	private String photo;
	private Status status;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	@ManyToOne
	@JoinColumn(name = "created_by")
	private Users created_by;
	@ManyToOne
	@JoinColumn(name = "update_by")
	private Users update_by;
	@CreationTimestamp
	private LocalDate created_at;
	@UpdateTimestamp
	private LocalDate update_at;
	
	public enum Status{
		Active,InActive
	}
	
	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Product() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
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

	public Users getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Users created_by) {
		this.created_by = created_by;
	}

	public Users getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(Users update_by) {
		this.update_by = update_by;
	}

	public LocalDate getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDate created_at) {
		this.created_at = created_at;
	}

	public LocalDate getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(LocalDate update_at) {
		this.update_at = update_at;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
   
}
