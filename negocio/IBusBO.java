package com.mpersd.spring.negocio;

import java.util.List;

import com.mpersd.spring.dominio.Bus;

public interface IBusBO {
	List<Bus> findAll();
	Bus findOne(int id);
	void create(Bus bus);
	Bus update(Bus bus);
	void delete(Bus bus);
	void deleteById(int id);
	
	Bus buscarPorNombre(String nombre);
}
