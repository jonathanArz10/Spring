package com.mpersd.spring.datos;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.mpersd.spring.dominio.Role;
import com.mpersd.spring.dominio.User;

@Repository
public class UsersDAOJPA extends AbstractDAO<User> implements IUserDAO {

	public UsersDAOJPA() {
		super.setClazz(User.class);
	}

	@Override
	public void registerUser(User user) {
		Role role = new Role(0, "ROLE_MEMBER", user);
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		user.setRoles(roles);
		user.setActive(1);
		super.entityManager.persist(user);
	}

	@Override
	public User login(User user) {
		Query query = super.entityManager
				.createQuery("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = :email "
						+ "AND u.password = :password");
		query.setParameter("email", user.getEmail());
		query.setParameter("password", user.getPassword());
		List<User> lista = query.getResultList();
		if(lista.isEmpty()) {
			return null;
		}
		return lista.get(0);
	}

	@Override
	public User getUserWithRoles(int id) {
		Query query = super.entityManager
				.createQuery("SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = :id");
		query.setParameter("id", id);
		List<User> lista = query.getResultList();
		if (lista.isEmpty()) {
			return null;
		}
		return lista.get(0);
	}

	@Override
	public User getUser(String email) {
		Query query = super.entityManager
				.createQuery("SELECT u FROM User u WHERE u.email = :email")
				.setParameter("email", email);
		List<User> lista = query.getResultList();
		if (!lista.isEmpty()) {
			return lista.get(0);
		}
		return null;
	}

	@Override
	public void createWithRoles(User user, String role) {
		Role roleUser = new Role(0, role, user);
		List<Role> roles = new ArrayList<>();
		roles.add(roleUser);
		user.setRoles(roles);
		user.setActive(1);
		super.entityManager.persist(user);
	}

	@Override
	public List<User> buscarUsuarios() {
		Query query = super.entityManager
				.createQuery("SELECT u FROM User u JOIN FETCH u.roles");
		List<User> lista = query.getResultList();
		return lista;
	}
	
}
