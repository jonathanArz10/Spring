package com.mpersd.spring.negocio;

import java.util.List;

import com.mpersd.spring.dominio.Terminal;

public interface ITerminalBO {
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
