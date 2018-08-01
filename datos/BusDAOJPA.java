/**
 * 
 */
package com.mpersd.spring.datos;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.mpersd.spring.dominio.Bus;

@Repository
public class BusDAOJPA extends AbstractDAO<Bus> implements IBusDAO {

	public BusDAOJPA() {
		super();
		super.setClazz(Bus.class);
	}

	@Override
	public Bus buscarPorNombre(String nombre) {
		Query query = super.entityManager
				.createQuery("SELECT b from Bus b WHERE b.enrollment = :nombre")
				.setParameter("nombre", nombre);
		List<Bus> lista = query.getResultList();
		if (!lista.isEmpty()) {
			return lista.get(0);
		}
		return null;
	}
	
}
