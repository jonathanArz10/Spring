package com.mpersd.spring.datos;

import org.springframework.stereotype.Repository;

import com.mpersd.spring.dominio.Travel;

@Repository
public class TravelDAOJPA extends AbstractDAO<Travel> implements ITravelDAO {

	public TravelDAOJPA() {
		super();
		super.setClazz(Travel.class);
	}
}
