package com.mmit.shop.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.OrderDetail;
import com.mmit.shop.model.entity.Orders;
import com.mmit.shop.model.entity.Product;
import com.mmit.shop.model.service.ProductService;


@Named
@SessionScoped
public class CartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private Orders order;
	@Inject
	private ProductService pservice;
	@PostConstruct
	private void init() {
		order = new Orders();
		
	}
	public void addToCart(int pId) {
//		verify product already exist in cart
		for(OrderDetail od:order.getDetails()) {
			if(od.getProduct().getId() == pId) {
				od.setSubQty(od.getSubQty()+1);
				return;
			}
		}
//		add new product to cart
		Product p = pservice.findById(pId);
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProduct(p);
		orderDetail.setSubQty(1);
		order.addOrderItem(orderDetail);
	}
	public String removeFromCart(OrderDetail od) {
		order.getDetails().remove(od);
		return "/frontend/cart?faces-redirect=true";
	}
	public void updateQty() {
		order.getDetails().forEach(od->System.out.println(String.format("%s,%d", od.getProduct().getName(),od.getSubQty())));
	}
	public void increaseQty(int id) {
		for(OrderDetail od:order.getDetails()) {
			if(od.getProduct().getId() == id) {
				od.setSubQty(od.getSubQty()+1);
				return;
			}
		}
	}
	public void decreaseQty(int id) {
		for(OrderDetail od:order.getDetails()) {
			if(od.getProduct().getId() == id) {
				
				od.setSubQty(od.getSubQty()-1);
				if(od.getSubQty() == 0) {
					order.getDetails().remove(od);
				}
				return;
			}
		}
	}
	
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public int getItemCount() {
		return order.getDetails().size();
	}
	
}
