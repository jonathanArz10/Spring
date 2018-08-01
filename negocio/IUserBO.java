package com.mpersd.spring.negocio;

import java.util.List;

import com.mpersd.spring.dominio.User;


public interface IUserBO {
	void crearUser(User u);
	void actualizarUser(User u);
	void borrarUser(User u);
	User getUser(int id);
	List<User> getUsers();
	void registerUser(User user);
	User login(User user);
	User getUserWithRoles(int id);
	User getUser(String email);
	void createWithRoles(User user,String role);
	List<User> buscarUsuarios();
}
