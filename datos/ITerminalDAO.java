package com.mpersd.spring.datos;

import java.util.List;

import com.mpersd.spring.dominio.Terminal;

public interface ITerminalDAO {
	List<Terminal> findAll();
	Terminal findOne(int id);
	void create(Terminal terminal);
	Terminal update(Terminal terminal);
	void delete(Terminal terminal);
	void deleteById(int id);
	
	Terminal buscarPorNombre(String nombre);
	Terminal esTerminalLlegada(int id);
	Terminal esTerminalSalida(int id);
}
