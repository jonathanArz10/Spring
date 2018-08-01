package com.mpersd.spring.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mpersd.spring.datos.ITravelDAO;
import com.mpersd.spring.dominio.Travel;

@Service @Transactional
public class TravelBO implements ITravelBO {
	
	@Autowired
	private ITravelDAO traveldao;

	public TravelBO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Travel> findAll() {
		return traveldao.findAll();
	}

	@Override
	public Travel findOne(int id) {
		return traveldao.findOne(id);
	}

	@Override
	public void create(Travel travel) {
		// TODO Auto-generated method stub

	}

	@Override
	public Travel update(Travel travel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Travel travel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

}
