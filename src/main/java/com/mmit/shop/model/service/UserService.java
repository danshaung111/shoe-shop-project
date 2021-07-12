package com.mmit.shop.model.service;

import java.security.Principal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.mmit.shop.CommonException;
import com.mmit.shop.model.entity.Users;



@Stateless
public class UserService {

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private Pbkdf2PasswordHash encoder;
	
	@Inject
	Principal principal;

//	public List<Users> findAll() {
//		
//		return em.createNamedQuery("Users.findAll",Users.class).getResultList();
//	}

	public void save(Users user) {
		if(user.getId()==0) {
			user.setPassword(encoder.generate(user.getPassword().toCharArray()));
			em.persist(user);
		} else {
			em.merge(user);
		}
	}

	public Users findByLogId(String LoginId) {
	
		TypedQuery<Users> query=em.createNamedQuery("Users.findLoginId", Users.class);
		query.setParameter("LoginId", LoginId);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		return null;
	}

	public long getCount() {
		TypedQuery<Long> query=em.createNamedQuery("Users.getCount", Long.class);
		return query.getSingleResult();
	}

	public List<Users> findAllExceptMe() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Users.findAllExceptMe",Users.class)
				.setParameter("LoginId", principal.getName())
				.getResultList();
	}

	public void changePassword(String oldPassword, String newPassword, String confirmPassword) {
		Users loginUser=findByLogId(principal.getName());
		
		if(!encoder.verify(oldPassword.toCharArray(), loginUser.getPassword())) {
			//not match old password
			throw new CommonException("Incorrect old password!");
		}
		if(!newPassword.equals(confirmPassword)) {
			throw new CommonException("Confirm password do not match!");
			
		}
		loginUser.setPassword(encoder.generate(newPassword.toCharArray()));
		
	}

	public Users findById(int id) {
		
		return em.find(Users.class, id);
	}

	

	public void remove(int id) {
		
		Users u = em.find(Users.class, id);
		em.remove(u);
		
	}
	
}
