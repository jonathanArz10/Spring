package com.mpersd.spring.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mpersd.spring.datos.ISaleDAO;
import com.mpersd.spring.datos.IUserDAO;
import com.mpersd.spring.dominio.Sale;
import com.mpersd.spring.dominio.User;

@Service @Transactional
public class SaleBO implements ISaleBO {

	@Autowired
	private ISaleDAO sdao;
	
	@Autowired
	private IUserDAO udao;
	
	@Override
	public List<Sale> findAll() {
		return sdao.findAll();
	}

	@Override
	public Sale findOne(int id) {
		return sdao.findOne(id);
	}

	@Override
	public void create(Sale sale) {
		sdao.create(sale);
	}

	@Override
	public Sale update(Sale sale) {
		return sdao.update(sale);
	}

	@Override
	public void delete(Sale sale) {
		sdao.delete(sale);
	}

	@Override
	public void deleteById(int id) {
		sdao.deleteById(id);
	}

	@Override
	public void crearVenta(Sale sale, int user) {
		User userpers = udao.findOne(user);
		sale.setUser(userpers);
		sdao.create(sale);
	}

	@Override
	public Sale obtenerPorUsuario(User user) {
		return sdao.obtenerPorUsuario(user);
	}

	@Override
	public Sale obtenerPorUsuarioConDetalle(User user) {
		return sdao.obtenerPorUsuarioConDetalle(user);
	}

}
