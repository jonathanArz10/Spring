package com.mpersd.spring.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mpersd.spring.datos.IUserDAO;
import com.mpersd.spring.dominio.User;


@Service @Transactional
public class UsersBO implements IUserBO {
	@Autowired
	IUserDAO udao;
	
	public UsersBO() {
		// TODO Auto-generated constructor stub
	}

	@Override // @Transactional
	public void crearUser(User u) {
		udao.create(u);
	}

	@Override //@Transactional
	public void actualizarUser(User u) {
		udao.update(u);
	}

	@Override //@Transactional
	public void borrarUser(User u) {
		u.setActive(0);
		udao.update(u);
	}

	@Override // @Transactional
	public User getUser(int id) {
		return udao.findOne(id);
	}

	@Override //@Transactional
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return udao.findAll();
	}

	@Override
	public void registerUser(User user) {
		udao.registerUser(user);
	}

	@Override
	public User login(User user) {
		return udao.login(user);
	}

	@Override
	public User getUserWithRoles(int id) {
		return udao.getUserWithRoles(id);
	}

	@Override
	public User getUser(String email) {
		return udao.getUser(email);
	}

	@Override
	public void createWithRoles(User user, String role) {
		udao.createWithRoles(user, role);
	}

	@Override
	public List<User> buscarUsuarios() {
		return udao.buscarUsuarios();
	}

}
