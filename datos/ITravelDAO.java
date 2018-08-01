package com.mpersd.spring.datos;

import java.util.List;

import com.mpersd.spring.dominio.Travel;

public interface ITravelDAO {
	List<Travel> findAll();
	Travel findOne(int id);
	void create(Travel travel);
	Travel update(Travel travel);
	void delete(Travel travel);
	void deleteById(int id);
}
