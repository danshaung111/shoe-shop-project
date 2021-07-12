package com.mmit.shop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Brand;
import com.mmit.shop.model.service.BrandService;

@Named
@ViewScoped
public class BrandBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private BrandService service;
	private Brand brand;
	private List<Brand> brands;
	
	@PostConstruct
	private void init() {
		brands=service.findAll();
		brand=new Brand();
	}
	// ajax method update 
	public void getBrandInfo(int id) {
		if(id == 0)
			brand=new Brand();
		else
			brand=service.findById(id);
	}
	
	public String save() {
		service.save(brand);
		return "/admin/brands?faces-redirect=true";
	}
	
	
	public Brand getBrand() {
		return brand;
	}
	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
}
