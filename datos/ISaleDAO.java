package com.mpersd.spring.datos;

import java.util.List;

import com.mpersd.spring.dominio.Sale;
import com.mpersd.spring.dominio.User;

public interface ISaleDAO {
	List<Sale> findAll();
	Sale findOne(int id);
	void create(Sale sale);
	Sale update(Sale sale);
	void delete(Sale sale);
	void deleteById(int id);
	
	Sale obtenerPorUsuario(User user);
	Sale obtenerPorUsuarioConDetalle(User user);
}
