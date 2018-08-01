package com.mpersd.spring.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mpersd.spring.datos.IDetailDAO;
import com.mpersd.spring.datos.ISaleDAO;
import com.mpersd.spring.dominio.Detail;
import com.mpersd.spring.dominio.Sale;
import com.mpersd.spring.dominio.User;

@Service @Transactional
public class DetailBO implements IDetailBO {
	
	@Autowired
	private IDetailDAO ddao;
	
	@Autowired
	private ISaleDAO sdao;

	@Override
	public List<Detail> findAll() {
		return ddao.findAll();
	}

	@Override
	public Detail findOne(int id) {
		return ddao.findOne(id);
	}

	@Override
	public void create(Detail detail) {
		ddao.create(detail);
	}

	@Override
	public Detail update(Detail detail) {
		return ddao.update(detail);
	}

	@Override
	public void delete(Detail detail) {
		ddao.delete(detail);
	}

	@Override
	public void deleteById(int id) {
		ddao.deleteById(id);
	}

	@Override
	public void crearDetalle(Detail detail, User usuario) {
		Sale salepers = sdao.obtenerPorUsuario(usuario);
		detail.setSale(salepers);
		ddao.create(detail);
	}

}
