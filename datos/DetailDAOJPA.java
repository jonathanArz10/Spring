package com.mpersd.spring.datos;

import org.springframework.stereotype.Repository;

import com.mpersd.spring.dominio.Detail;

@Repository
public class DetailDAOJPA extends AbstractDAO<Detail> implements IDetailDAO {

	public DetailDAOJPA() {
		super();
		super.setClazz(Detail.class);
	}
	
}
