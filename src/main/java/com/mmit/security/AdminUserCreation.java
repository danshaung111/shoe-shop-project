package com.mmit.security;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.mmit.shop.model.entity.Users;
import com.mmit.shop.model.entity.Users.Role;
import com.mmit.shop.model.service.UserService;


@ApplicationScoped
@Singleton
@Startup
public class AdminUserCreation {
	@Inject
	private UserService service;
	@PostConstruct
	private void init() {
		long userCount=service.getCount();
		if(userCount == 0) {
		Users admin=new Users();
		admin.setLoginId("danshaung@gmail.com");
		admin.setPassword("12345678");
		admin.setRole(Role.Admin);
		admin.setUserName("danshaung");
		service.save(admin);
		}
	}
}
