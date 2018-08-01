package com.mpersd.spring.datos;

import java.util.List;

import com.mpersd.spring.dominio.Bus;
import com.mpersd.spring.dominio.Terminal;

public interface IBusDAO {
	List<Bus> findAll();
	Bus findOne(int id);
	void create(Bus bus);
	Bus update(Bus bus);
	void delete(Bus bus);
	void deleteById(int id);
	
	Bus buscarPorNombre(String nombre);
}
