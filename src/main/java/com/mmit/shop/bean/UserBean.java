package com.mmit.shop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Users;
import com.mmit.shop.model.service.UserService;

@Named
@ViewScoped
public class UserBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Users user;
	private List<Users> users;
	@Inject
	private UserService service;
	@Inject
	private FacesContext cxt;
	
	@PostConstruct
	private void init() {
		users = service.findAllExceptMe();
		String uId = cxt.getExternalContext().getRequestParameterMap().get("userId");
		if(uId != null)
			user = service.findById(Integer.parseInt(uId));
		else
			user = new Users();
		
	}
	
	public String save() {
		
		try {
			service.save(user);
			return "/admin/users.xhtml?faces-redirect=true";
		} catch (EJBException e) {
			FacesContext cxt=FacesContext.getCurrentInstance();
			cxt.addMessage("editForm:LoginId", new FacesMessage("LoginId is aleady exist"));
		}
		return null;
	}
	
	public String remove(int id) {
		service.remove(id);
		return "/admin/users.xhtml?faces-redirect=true";
	}

	public List<Users> getUsers() {
		return users;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}
	

}
