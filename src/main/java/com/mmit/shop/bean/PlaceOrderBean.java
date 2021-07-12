package com.mmit.shop.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.OrderDetail;
import com.mmit.shop.model.entity.Orders;
import com.mmit.shop.model.entity.Users;
import com.mmit.shop.model.service.OrderService;


@Named
@ViewScoped
public class PlaceOrderBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String receiverName;
	private String receiverAddress;
	private String receiverPhone;
	
	@Inject
	private LoginBean loginBean;
	@Inject
	private CartBean cartBean;
	@Inject
	private OrderService service;
	
//	private List<Orders> orders;
	private List<OrderDetail> orderDetails;
	@PostConstruct
	private void init() {
		Users u = loginBean.getLoginUser();
		receiverName = u.getUserName();
		receiverAddress = u.getAddress();
		receiverPhone = u.getPhone();
//		orders = new ArrayList<Orders>();
		orderDetails = new ArrayList<OrderDetail>();
	}
	public void getOrderDetails(int orderId) {
		Orders o = service.findById(orderId);
		orderDetails = o.getDetails();
	}
	public String placeOrder() {
		
		service.saveOrder(cartBean.getOrder(),receiverName,receiverPhone,receiverAddress);
		cartBean.setOrder(new Orders());
		return "/index?faces-redirect=true";
	}
	
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public LoginBean getLoginBean() {
		return loginBean;
	}
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public List<Orders> getOrders() {
		return service.findByLoginId(loginBean.getLoginUser().getId());
	}
	

}
