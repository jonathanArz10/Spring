package com.mpersd.spring.negocio;

import java.util.List;

import com.mpersd.spring.dominio.Detail;
import com.mpersd.spring.dominio.User;

public interface IDetailBO {
	List<Detail> findAll();
	Detail findOne(int id);
	void create(Detail detail);
	Detail update(Detail detail);
	void delete(Detail detail);
	void deleteById(int id);
	
	void crearDetalle(Detail detail, User usuario);
}
