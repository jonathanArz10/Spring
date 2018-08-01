package com.mpersd.spring.negocio;

import java.util.List;

import com.mpersd.spring.dominio.Sale;
import com.mpersd.spring.dominio.Travel;
import com.mpersd.spring.dominio.User;

public interface ISaleBO {
	List<Sale> findAll();
	Sale findOne(int id);
	void create(Sale sale);
	Sale update(Sale sale);
	void delete(Sale sale);
	void deleteById(int id);
	
	void crearVenta(Sale sale, int travel);
	Sale obtenerPorUsuario(User user);
	Sale obtenerPorUsuarioConDetalle(User user);
}
