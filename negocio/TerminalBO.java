package com.mpersd.spring.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mpersd.spring.datos.ITerminalDAO;
import com.mpersd.spring.dominio.Terminal;

@Service @Transactional
public class TerminalBO implements ITerminalBO {
	
	@Autowired
	private ITerminalDAO terminaldao;
	
	@Override
	public List<Terminal> findAll() {
		return terminaldao.findAll();
	}

	@Override
	public Terminal findOne(int id) {
		return terminaldao.findOne(id);
	}

	@Override
	public void create(Terminal terminal) {
		terminaldao.create(terminal);
	}

	@Override
	public Terminal update(Terminal terminal) {
		return terminaldao.update(terminal);
	}

	@Override
	public void delete(Terminal terminal) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(int id) {
		terminaldao.deleteById(id);
	}

	@Override
	public Terminal buscarPorNombre(String nombre) {
		return terminaldao.buscarPorNombre(nombre);
	}

	@Override
	public Terminal esTerminalLlegada(int id) {
		return terminaldao.esTerminalLlegada(id);
	}

	@Override
	public Terminal esTerminalSalida(int id) {
		return terminaldao.esTerminalSalida(id);
	}
	
}
