package com.mpersd.spring.datos;

import java.util.List;

import com.mpersd.spring.dominio.Detail;

public interface IDetailDAO {
	List<Detail> findAll();
	Detail findOne(int id);
	void create(Detail detail);
	Detail update(Detail detail);
	void delete(Detail detail);
	void deleteById(int id);
}
