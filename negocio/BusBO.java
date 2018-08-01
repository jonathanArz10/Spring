package com.mpersd.spring.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mpersd.spring.datos.IBusDAO;
import com.mpersd.spring.dominio.Bus;

@Service @Transactional
public class BusBO implements IBusBO {
	
	@Autowired
	private IBusDAO busdao;

	public BusBO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Bus> findAll() {		
		return busdao.findAll();
	}

	@Override
	public Bus findOne(int id) {
		return busdao.findOne(id);
	}

	@Override
	public void create(Bus bus) {
		busdao.create(bus);
	}

	@Override
	public Bus update(Bus bus) {
		return busdao.update(bus);
	}

	@Override
	public void delete(Bus bus) {
		busdao.delete(bus);
	}

	@Override
	public void deleteById(int id) {
		busdao.deleteById(id);
	}

	@Override
	public Bus buscarPorNombre(String nombre) {
		return busdao.buscarPorNombre(nombre);
	}

}
