package com.mmit.shop.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Users;
import com.mmit.shop.model.entity.Users.Role;
import com.mmit.shop.model.service.UserService;

@Named
@ViewScoped
public class RegisterBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users user;
	@Inject
	private UserService service;
	@Inject
	private FacesContext cxt;
	@Inject
	private LoginBean loginBean;
	private String tmp_msg=null;
	@PostConstruct
	private void init() {
		String id = cxt.getExternalContext().getRequestParameterMap().get("userId");
		if(id!=null)
		{
			user = service.findById(Integer.parseInt(id));
			tmp_msg="edit";
		}
		else{
			user = new Users();
			user.setRole(Role.Customer);
		}
		
	}
	public String register() {
		try {
			service.save(user);
			if(loginBean.getLoginUser().getId() != 0 && tmp_msg != null)
				loginBean.setLoginUser(user);
			return "/index.xhtml?faces-redirect=true";
		} catch (EJBException e) {
			FacesContext cxt = FacesContext.getCurrentInstance();
			cxt.addMessage("editForm:loginId", new FacesMessage("Login Already Exist"));
		}
		return null;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	
	
}
