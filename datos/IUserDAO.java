package com.mpersd.spring.datos;

import java.util.List;

import com.mpersd.spring.dominio.User;


public interface IUserDAO {
	
	List<User> findAll();
	User findOne(int id);
	void create(User user);
	User update(User user);
	void delete(User user);
	void deleteById(int id);
	
	void registerUser(User user);
	User login(User user);
	User getUserWithRoles(int id);
	User getUser(String email);
	void createWithRoles(User user,String role);
	List<User> buscarUsuarios();
}
